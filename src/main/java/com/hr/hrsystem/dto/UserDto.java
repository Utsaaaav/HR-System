package com.hr.hrsystem.dto;

import com.hr.hrsystem.entity.User;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    private int id;

    private String userName;

    private String password;


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
