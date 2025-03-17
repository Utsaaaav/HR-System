package com.hr.hrsystem.repo.checkout;

import com.hr.hrsystem.dto.CheckoutDto;
import com.hr.hrsystem.entity.Checkout;
import com.hr.hrsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface CheckoutRepo extends JpaRepository<Checkout, Integer> {

}
