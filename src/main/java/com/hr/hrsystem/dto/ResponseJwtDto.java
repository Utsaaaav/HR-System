package com.hr.hrsystem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ResponseJwtDto {

    private String status;
    private String token;

}
