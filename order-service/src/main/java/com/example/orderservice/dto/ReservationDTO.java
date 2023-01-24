package com.example.orderservice.dto;

import com.example.orderservice.model.Customer;
import com.example.orderservice.model.Vehicle;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
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

    private Vehicle vehicle;// In case user need more details about Vehicle
}
