package com.example.gatewayservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/aa")
    public String get(){
        return "AAAAAAAAAAAAAAAAA";
    }
}
