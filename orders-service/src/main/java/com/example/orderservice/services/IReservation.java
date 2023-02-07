package com.example.orderservice.services;

import com.example.orderservice.dto.ReservationDTO;
import com.example.orderservice.exceptions.CustomerNotFoundException;
import com.example.orderservice.exceptions.ReservationNotFoundException;

import java.util.List;

public interface IReservation {
    List<ReservationDTO> getReservations();
    ReservationDTO getReservationById(Long idReservation) throws ReservationNotFoundException, CustomerNotFoundException;
    ReservationDTO reserveVehicle(ReservationDTO reservationDTO) throws CustomerNotFoundException;// addReservation
    void deleteReservation(Long idReservation);
    ReservationDTO updateReservation(ReservationDTO reservationDTO);
    List<ReservationDTO> getReservationWithSort(String field);
    List<ReservationDTO> vehicleReservations(Long vehicleId);

}
