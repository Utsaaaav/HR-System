package com.hr.hrsystem.dto;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GlobalApiResponse {

    private Boolean status;
    private String message;
    private Object data;


}
