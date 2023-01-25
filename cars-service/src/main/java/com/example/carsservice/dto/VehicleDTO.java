package com.example.carsservice.dto;


import com.example.carsservice.entities.Category;
import com.example.carsservice.entities.Promo;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
public class VehicleDTO {
    private Long id;
    private String title;
    private String description;
    private float dailyPrice;
    private Date dateFirstCirculation;
    private float nbrOfKm;
    private float franchise;
    private int model;
    private boolean available;
    private List<String> images ; // Should be EAGER
    private  List<String> urls;
    //private ArrayList<Reservation> reservations;
    private Promo promo ;
    private Category category;
    private Long officeId;
}
