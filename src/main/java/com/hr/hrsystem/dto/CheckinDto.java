package com.hr.hrsystem.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CheckinDto {

    private int id;
    private int userId;
    private String userName;
    private LocalDateTime checkinTime;

}
