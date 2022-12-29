package com.example.carsservice.repositories;

import com.example.carsservice.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository

public interface  VehiculeRepository  extends JpaRepository<Vehicle,Long> {
    List<Vehicle> getVehicleByDailyPriceBetween(float min    ,float max);
    List<Vehicle> findAll();
    List<Vehicle> findByOfficeId(int officeId);

}
