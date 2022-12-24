package com.example.orderservice.repositories;

import com.example.orderservice.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> getReservationsByVehicleId(Long vehicleID);
}
