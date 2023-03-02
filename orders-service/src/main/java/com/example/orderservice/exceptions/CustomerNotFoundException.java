package com.example.orderservice.exceptions;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(Long id){
        super("customer with id : "+ id+" isn't found ,maybe customers service isn't responding");
    }
}
