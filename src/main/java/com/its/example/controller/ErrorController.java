package com.its.example.controller;

import com.its.example.errorhandling.exception.ForbiddenException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ErrorController {

    @GetMapping("/internalerror")

    public  void  internalerror(){
        throw  new RuntimeException("500 Internal Error !!");
    }

    @GetMapping("/forbidden")
    public  void  forbidden(){
        throw  new ForbiddenException("403 Forbidden !!");
    }

}
