package com.hr.hrsystem.repo.checkin;

import com.hr.hrsystem.entity.Checkin;
import com.hr.hrsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CheckinRepo extends JpaRepository<Checkin, Integer> {

    @Query("SELECT c FROM Checkin c WHERE c.user = :user ORDER BY c.checkinTime DESC")
    Optional<Checkin> findTopByUserOrderByCheckinTimeDesc(@Param("user") User user);

   // Optional<Checkin> findTopByUserOrderByCheckinTimeDesc(@Param("user") User user);

}
