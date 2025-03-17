package com.hr.hrsystem.service;

import com.hr.hrsystem.dto.UserDto;
import com.hr.hrsystem.entity.User;
import com.hr.hrsystem.projection.UserProjection;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    boolean saveUser(UserDto user);

    List<UserDto> getAllUser();

    boolean getUserByid(int id);

    void updateUser(UserDto userDto);

    List<UserProjection> findAllUsers();



}
