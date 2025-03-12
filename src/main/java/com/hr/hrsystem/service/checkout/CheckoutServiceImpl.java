package com.hr.hrsystem.service.checkout;

import com.hr.hrsystem.dto.CheckoutDto;
import com.hr.hrsystem.entity.Checkin;
import com.hr.hrsystem.entity.Checkout;
import com.hr.hrsystem.entity.User;
import com.hr.hrsystem.repo.UserRepo;
import com.hr.hrsystem.repo.checkin.CheckinRepo;
import com.hr.hrsystem.repo.checkout.CheckoutRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepo checkoutRepo;

    private final CheckinRepo checkinRepo;

    private final UserRepo userRepo;

    public CheckoutServiceImpl(CheckoutRepo checkoutRepo, CheckinRepo checkinRepo, UserRepo userRepo) {
        this.checkoutRepo = checkoutRepo;
        this.checkinRepo = checkinRepo;
        this.userRepo = userRepo;
    }

    @Override
    public CheckoutDto checkoutUser(int userID) {

        User user = userRepo.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Checkin> lastCheckin = checkinRepo.findTopByUserOrderByCheckinTimeDesc(user);
        if(lastCheckin.isEmpty()){
            throw new RuntimeException("User has not checked in yet.");
        }

        Checkout checkout = Checkout.builder()
                .user(user)
                .checkoutTime(LocalDateTime.now())
                .build();
        Checkout savedCheckout = checkoutRepo.save(checkout);

        return new CheckoutDto(savedCheckout.getId(), savedCheckout.getUser().getId(), savedCheckout.getUser().getUserName(), savedCheckout.getCheckoutTime());
    }
}
