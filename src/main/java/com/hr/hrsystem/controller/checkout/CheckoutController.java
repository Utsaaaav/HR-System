package com.hr.hrsystem.controller.checkout;


import com.hr.hrsystem.dto.CheckoutDto;
import com.hr.hrsystem.service.checkout.CheckoutService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Check-out API")
public class CheckoutController {


    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<?> checkout(@PathVariable int userId){

        try{

            CheckoutDto checkoutDto = checkoutService.checkoutUser(userId);
            return ResponseEntity.ok(checkoutDto);

        }
        catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
