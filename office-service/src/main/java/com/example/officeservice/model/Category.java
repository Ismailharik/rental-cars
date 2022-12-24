package com.example.officeservice.model;


import lombok.Data;

import java.util.List;
@Data
public class Category {
    private Long id;
    private String description;
    private String typeVehicle;
    private List<Vehicle> vehicles;
}
