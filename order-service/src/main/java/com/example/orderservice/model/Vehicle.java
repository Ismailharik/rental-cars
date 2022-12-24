package com.example.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
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
    private Long officeId;
}
