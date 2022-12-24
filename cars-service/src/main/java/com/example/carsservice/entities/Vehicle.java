package com.example.carsservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Vehicle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private float dailyPrice;
    private Date dateFirstCirculation;
    private float nbrOfKm;
    private float franchise;
    private boolean available;
    private List<String> images =new ArrayList(); // Should be EAGER

    @ManyToOne
    private Promo promo ;
    @ManyToOne
    private Category category;
    private Long officeId;


}
