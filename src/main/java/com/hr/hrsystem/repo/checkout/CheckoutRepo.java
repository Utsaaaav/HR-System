package com.hr.hrsystem.repo.checkout;

import com.hr.hrsystem.dto.CheckoutDto;
import com.hr.hrsystem.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepo extends JpaRepository<Checkout, Integer> {


}
