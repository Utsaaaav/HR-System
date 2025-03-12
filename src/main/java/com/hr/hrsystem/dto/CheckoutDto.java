package com.hr.hrsystem.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CheckoutDto {

    private int id;

    private int userId;

    private String userName;

    private LocalDateTime checkoutTime;

}
