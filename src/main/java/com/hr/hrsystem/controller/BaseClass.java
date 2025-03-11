package com.hr.hrsystem.controller;

import com.hr.hrsystem.dto.GlobalApiResponse;
import org.springframework.stereotype.Component;

@Component
public class BaseClass {

    public GlobalApiResponse successResponse(String message, Object object ){

        return GlobalApiResponse.builder()
                .message(message)
                .status(true)
                .data(object)
                .build();

    }

    public GlobalApiResponse failureResponse(String message, Object object){

        return GlobalApiResponse.builder()
                .data(object)
                .status(false)
                .message(message)
                .build();

    }
}
