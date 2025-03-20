package com.hr.hrsystem.controller.user;

import com.hr.hrsystem.controller.BaseClass;
import com.hr.hrsystem.dto.GlobalApiResponse;
import com.hr.hrsystem.dto.ResponseJwtDto;
import com.hr.hrsystem.dto.UserDto;
import com.hr.hrsystem.projection.UserProjection;
import com.hr.hrsystem.service.user.UserService;
import com.hr.hrsystem.utils.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User APIs")
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

        userService.saveUser(user);
        return new ResponseEntity<>("Created", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseJwtDto> login(@RequestBody UserDto userDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUserName());

            String jwt = jwtUtil.generateToken(userDetails.getUsername());

            ResponseJwtDto responseJwtDto = new ResponseJwtDto("Success", jwt);
            return new ResponseEntity<>(responseJwtDto, HttpStatus.OK);

        } catch (Exception e) {
            ResponseJwtDto responseDto = new ResponseJwtDto("Incorrect Username Or Password", null);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<GlobalApiResponse> listAllUser() {

        List<UserDto> user = userService.getAllUser();
        if (user != null) {

            return new ResponseEntity<>(
                    successResponse("User List Fetched Successfully", user),
                    HttpStatus.OK);
        } else {

            return new ResponseEntity<>(
                    failureResponse("Failed To Fetch User List", null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

        }
    }

    @PutMapping("/update")
    public ResponseEntity<GlobalApiResponse> updateUser(@RequestBody UserDto userDto) {

         userService.updateUser(userDto);

        if (userDto != null) {

            return new ResponseEntity<>(
                    successResponse("Updated Successfully", userDto)
                    , HttpStatus.OK);
        } else {

            return new ResponseEntity<>(
                    failureResponse("Failed to update", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/projection")
    public ResponseEntity<GlobalApiResponse> viewAll() {

        List<UserProjection> user = userService.findAllUsers();
        if (user != null) {

            return new ResponseEntity<>(
                    successResponse("Data Fetched Successfully", user),
                    HttpStatus.OK);
        } else {

            return new ResponseEntity<>(failureResponse("Failed to Fetch", null)
                    , HttpStatus.OK);
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




