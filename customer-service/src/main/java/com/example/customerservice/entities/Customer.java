package com.example.customerservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    private String fullName;
    private String email;
    private String password;
    private Date birthdate;
    private boolean status;
//    @OneToMany(mappedBy = "customer")
//    private List<Reservation> reservations;
}
