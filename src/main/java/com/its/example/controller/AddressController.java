package com.its.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressController {

    @GetMapping("/address")

    public  String address(){
        System.out.println("카카오 Api");

        return "addressPages/address";
    }
}
