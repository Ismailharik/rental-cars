package com.example.officeservice.dto;

import com.example.officeservice.entities.Employee;
import com.example.officeservice.model.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OfficeDTO {
    private Long id;
    private String city;
    private String phone;
    private String Address;
//    private List<Employee> employees ;
    //@OneToMany(mappedBy = "office")
    @Transient
    private List<Vehicle> vehicles;
}