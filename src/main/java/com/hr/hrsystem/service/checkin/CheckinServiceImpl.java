package com.hr.hrsystem.service.checkin;

import com.hr.hrsystem.dto.CheckinDto;
import com.hr.hrsystem.entity.Checkin;
import com.hr.hrsystem.entity.User;
import com.hr.hrsystem.repo.user.UserRepo;
import com.hr.hrsystem.repo.checkin.CheckinRepo;
import com.hr.hrsystem.repo.checkout.CheckoutRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckinServiceImpl implements CheckinService {

    private final CheckinRepo checkinRepo;

    private final UserRepo userRepo;

    private final CheckoutRepo checkoutRepo;

    public CheckinServiceImpl(CheckinRepo checkinRepo, UserRepo userRepo, CheckoutRepo checkoutRepo) {
        this.checkinRepo = checkinRepo;
        this.userRepo = userRepo;
        this.checkoutRepo = checkoutRepo;
    }


    @Override
    public CheckinDto checkinUser(int userID) {

        User user = userRepo.findById(userID).orElseThrow(() ->
                new RuntimeException("User Not Found"));

        Optional<Checkin> lastCheckin = checkinRepo.findTopByUserOrderByCheckinTimeDesc(user);

        if (lastCheckin.isPresent()) {
            throw new RuntimeException("User has already checked in.");
        }

        Checkin checkin = new Checkin();
        checkin.setUser(user);
        Checkin savedCheckin = checkinRepo.save(checkin);

        return new CheckinDto(savedCheckin.getId(), savedCheckin.getUser().getId(), savedCheckin.getUser().getUserName(), savedCheckin.getCheckinTime());
    }
}
