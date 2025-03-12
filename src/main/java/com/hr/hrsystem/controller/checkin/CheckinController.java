package com.hr.hrsystem.controller.checkin;

import com.hr.hrsystem.dto.CheckinDto;
import com.hr.hrsystem.dto.UserDto;
import com.hr.hrsystem.repo.checkin.CheckinRepo;
import com.hr.hrsystem.service.checkin.CheckinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CheckinController {

    private final CheckinService checkinService;

    public CheckinController(CheckinService checkinService) {
        this.checkinService = checkinService;
    }


    @PostMapping("checkin/{userId}")
    public ResponseEntity<?> checkIn(@PathVariable int userId){

        try{
            CheckinDto checkin = checkinService.checkinUser(userId);
            return ResponseEntity.ok(checkin);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }
}
