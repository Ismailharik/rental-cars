package com.example.orderservice.exceptions;

public class VehicleNotFoundException extends Exception {
    public VehicleNotFoundException(Long vehicleId){
        super("vehicle with id :"+vehicleId+"isn't found");
    }
}
