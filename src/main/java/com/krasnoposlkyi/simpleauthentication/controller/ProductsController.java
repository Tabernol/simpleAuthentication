package com.krasnoposlkyi.simpleauthentication.controller;

import com.krasnoposlkyi.simpleauthentication.dao.request.SignInRequest;
import com.krasnoposlkyi.simpleauthentication.dao.request.SignUpRequest;
import com.krasnoposlkyi.simpleauthentication.dto.UserPostDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @GetMapping("/all")
    public String getAll(){
        return "ALLLLLLLLL";
    }

    @PostMapping(value = "/all", consumes="application/json")
    public String testPost(@RequestBody SignInRequest signInRequest){
        System.out.println(signInRequest.getUsername());
        return signInRequest.getUsername();
    }
}
