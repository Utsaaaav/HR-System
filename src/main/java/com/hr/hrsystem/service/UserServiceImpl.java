package com.hr.hrsystem.service;

import com.hr.hrsystem.dto.UserDto;
import com.hr.hrsystem.entity.User;
import com.hr.hrsystem.projection.UserProjection;
import com.hr.hrsystem.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean saveUser(User user) {
        try {
            user.setUserName(user.getUserName());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

//    @Override
//    public UserDto create(UserDto userDto) {
//
//        //DTO to entity
//        User user = User.builder()
//                .id(userDto.getId())
//                .userName(userDto.)
//                .password(userDto.getPassword())
//                .build();
//        //User entity = userDto.toEntity();
//        user = userRepo.save(user);
//
//        return new UserDto(user.getId());
//
//    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> user = userRepository.findAll();
        List<UserDto> userDto = new ArrayList<>();
        for (User u : user) {
            UserDto us = UserDto.builder()
                    .id(u.getId())
                    .userName(u.getUserName())
                    .password(passwordEncoder.encode(u.getPassword()))
                    .build();
            userDto.add(us);
        }
        return userDto;
    }

    //
//    @Override
//    public void deleteById(int id) {
//        userRepo.deleteById(id);
//    }
//
//
    @Override
    public boolean getUserByid(int id) {

        return true;

    }

    @Override
    public void updateUser(UserDto userDto) {

        //change to entity
        Optional<User> optionalUser = userRepo.findById(userDto.getId());

        User existingUser = optionalUser.get();
        existingUser.setUserName(userDto.getUserName());
        existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User user = userRepo.save(existingUser);

        UserDto u = UserDto.builder()
                .userName(user.getUserName())
                .password(user.getPassword())
                .build();
    }

    @Override
    public List<UserProjection> findAllUsers() {

        return userRepository.findAllProjections();

    }
}