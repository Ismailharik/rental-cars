package com.example.officeservice.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data

public class Vehicle {
    private Long id;
    private String titre;
    private String description;
    private float dailyPrice;
    private Date dateFirstCirculation;
    private float nbrOfKm;
    private float franchise;
    private boolean available;
    private List<String> images =new ArrayList(); // Should be EAGER
    private Category category;


}
