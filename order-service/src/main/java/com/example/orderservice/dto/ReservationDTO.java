package com.example.orderservice.dto;

import com.example.orderservice.model.Customer;
import com.example.orderservice.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Long id;
    private Date departureDate;
    private Date returnDate ;
    private float totalPrice;
    private boolean isPaid;
    private boolean confirmed;
    private Long CustomerId;
    private Long vehicleId;
    private Customer customer;
    private Vehicle vehicle;
}
