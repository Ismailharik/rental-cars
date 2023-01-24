package com.example.orderservice.model;

import lombok.Data;

import java.util.Date;
@Data
public class Customer {
    private Long id;
    private String phone;
    private String email;
    private String fullName;


}
