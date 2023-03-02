package com.example.orderservice.services;

import com.example.orderservice.dto.ReservationDTO;
import com.example.orderservice.exceptions.CustomerNotFoundException;
import com.example.orderservice.exceptions.ReservationNotFoundException;
import com.example.orderservice.exceptions.VehicleNotFoundException;

import java.util.List;

public interface IReservation {
    List<ReservationDTO> getReservations();
    ReservationDTO getReservationById(Long idReservation) throws ReservationNotFoundException;
    ReservationDTO addReserveVehicle(ReservationDTO reservationDTO) throws CustomerNotFoundException, VehicleNotFoundException;// addReservation
    void deleteReservation(Long idReservation);
    ReservationDTO updateReservation(ReservationDTO reservationDTO);
    List<ReservationDTO> getReservationsWithSort(String field);
    List<ReservationDTO> vehicleReservations(Long vehicleId);

}
