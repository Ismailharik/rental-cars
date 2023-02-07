package com.example.orderservice.exceptions;

public class ReservationNotFoundException extends Exception {
    public ReservationNotFoundException(Long id){
        super("Reservation with Id : "+id+" is not found");
    }
}
