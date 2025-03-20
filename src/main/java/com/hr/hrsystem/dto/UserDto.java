package com.hr.hrsystem.dto;

import lombok.*;

import java.util.Date;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    private int id;

    private String userName;

    private String password;

    private Date createdDate;

    private Date modifiedDate;

    private String createdBy;

    private String modifiedBy;


//    public User toEntity(){
//
//        return User.builder()
//                .id(this.id)
//                .firstName(this.firstName)
//                .lastName(this.lastName)
//                .email(this.email)
//                .password(this.password)
//                .build();

//    }


}
