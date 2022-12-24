package com.example.officeservice.entities;

import com.example.officeservice.model.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Office {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String phone;
    private String Address;
    @OneToMany(mappedBy = "office",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees ;
    //@OneToMany(mappedBy = "office")
    @Transient
    private List<Vehicle> vehicles;
}
