package com.example.orderservice.mappers;

import com.example.orderservice.dto.ReservationDTO;
import com.example.orderservice.entities.Reservation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ReservationMapper {
    public ReservationDTO fromReservationToReservationDTO(Reservation reservation){
        ReservationDTO reservationDTO = new ReservationDTO();
        BeanUtils.copyProperties(reservation,reservationDTO);
        return reservationDTO;
    }
    public Reservation fromReservationDTOToReservation(ReservationDTO reservationDTO){
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(reservationDTO,reservation);
        return reservation;
    }
}
