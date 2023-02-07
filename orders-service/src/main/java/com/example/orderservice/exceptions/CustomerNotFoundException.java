package com.example.orderservice.exceptions;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String msg){
        super(msg);
    }
}
