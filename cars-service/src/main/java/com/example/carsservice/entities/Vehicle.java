package com.example.carsservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Vehicle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private float dailyPrice;
    private Date dateFirstCirculation;
    private float nbrOfKm;
    private float franchise;
    private int model;
    private boolean available;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> images =new ArrayList(); // Should allocate space here because I will add images

    @ManyToOne
    private Promo promo ;
    @ManyToOne()
    private Category category;
    private Long officeId;// location


}
