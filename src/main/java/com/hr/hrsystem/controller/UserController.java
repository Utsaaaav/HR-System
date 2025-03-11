package com.hr.hrsystem.controller;

import com.hr.hrsystem.dto.GlobalApiResponse;
import com.hr.hrsystem.dto.UserDto;
import com.hr.hrsystem.entity.User;
import com.hr.hrsystem.projection.UserProjection;
import com.hr.hrsystem.service.UserService;
import com.hr.hrsystem.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController extends BaseClass {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> signup(@RequestBody UserDto user) {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());

        userService.saveUser(newUser);
        return new ResponseEntity<>("Created", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Incorrect username and password", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<GlobalApiResponse> listAllUser(){

        List<UserDto> user = userService.getAllUser();
        if(user != null){

            return new ResponseEntity<>(
                    successResponse("User List Fetched Successfully",user),
                    HttpStatus.OK);
        }
        else {

            return new ResponseEntity<>(
                    failureResponse("Failed To Fetch User List",null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

        }
    }

    @PutMapping("/update")
    public ResponseEntity<GlobalApiResponse> updateUser(@RequestBody UserDto userDto){

        userService.updateUser(userDto);

        if(userDto != null){

            return new ResponseEntity<>(
                    successResponse("Updated Successfully",userDto)
                    ,HttpStatus.OK);
        }
        else{

            return new ResponseEntity<>(
                    failureResponse("Failed to update",null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/projection")
    public ResponseEntity<GlobalApiResponse> viewAll(){

       List<UserProjection> user = userService.findAllUsers();
       if(user != null){

           return new ResponseEntity<>(
                   successResponse("Data Fetched Successfully",user),
                   HttpStatus.OK);
       }
       else{

           return new ResponseEntity<>(failureResponse("Failed to Fetch",null)
           ,HttpStatus.OK);
       }
    }

//    @PostMapping("/create")
//    public ResponseEntity<GlobalApiResponse> createUser(@RequestBody UserDto userDto){
//
//        userDto = userService.create(userDto);
//
//        if(userDto != null){
//            return new ResponseEntity<> (
//                    successResponse("User Created Successfully", userDto),
//                    HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>(
//                    failureResponse("User Creation Failed",null),
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }

//
//        @DeleteMapping("/delete/{id}")
//        public ResponseEntity<GlobalApiResponse> delete(@PathVariable int id){
//
//        userService.deleteById(id);
//        boolean flag = userService.getUserByid(id);
//
//        if(flag == true){
//
//            return new ResponseEntity<>(
//                    successResponse("Deleted Succesfully",id),
//                    HttpStatus.OK
//            );
//        }
//        else{
//
//            return new ResponseEntity<>(
//                    failureResponse("Deletion Failed", null),
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//
//        }
//
//        }
//

}




