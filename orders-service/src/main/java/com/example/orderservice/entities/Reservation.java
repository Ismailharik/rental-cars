package com.example.orderservice.entities;


import com.example.orderservice.model.Customer;
import com.example.orderservice.model.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date pickUpDate;
    private int duration ;
    private int officeId;
    private Long vehicleId;
    private float totalPrice;
    private boolean isPaid;
    private boolean confirmed;
    //@ManyToOne()
    private Long CustomerId;
    @Transient()
    private Customer customer;// In case user need more details about customer

    // @ManyToOne()
    @Transient
    private Vehicle vehicle;// In case user need more details about Vehicle
}