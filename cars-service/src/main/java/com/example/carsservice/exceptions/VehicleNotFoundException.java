package com.example.carsservice.exceptions;

public class VehicleNotFoundException extends Exception {
    public VehicleNotFoundException(Long id){
        super("Vehicle with id : "+id+"is not found");
    }
}
