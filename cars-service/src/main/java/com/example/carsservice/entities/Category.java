
package com.example.carsservice.entities;

import com.example.carsservice.utilities.TypeVehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private TypeVehicle typeVehicle;
    @OneToMany(mappedBy = "category")
    @JsonIgnore()
    private List<Vehicle> vehicles;
}