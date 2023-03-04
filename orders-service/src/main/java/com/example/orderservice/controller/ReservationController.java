package com.example.orderservice.controller;

import com.example.orderservice.dto.ReservationDTO;
import com.example.orderservice.exceptions.CustomerNotFoundException;
import com.example.orderservice.exceptions.ReservationNotFoundException;
import com.example.orderservice.exceptions.VehicleNotFoundException;
import com.example.orderservice.services.IReservation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private static Logger logger = LoggerFactory.getLogger(ReservationController.class);
    private IReservation iReservation;

    @GetMapping("")
    public List<ReservationDTO> getAllReservations(){
        logger.info("get All reservations");

        return iReservation.getReservations();
    }
    @GetMapping("/{reservationId}")
    public ReservationDTO getReservationById(@PathVariable Long reservationId) throws ReservationNotFoundException {
        logger.info("Get reservation by Id");
        return iReservation.getReservationById(reservationId);
    }


    @GetMapping("/vehicleReservations/{vehicleId}")
    public List<ReservationDTO> getVehicleReservationById(@PathVariable Long vehicleId)  {
        logger.info("get vehicle Reservations");
        return iReservation.vehicleReservations(vehicleId);
    }
    @DeleteMapping("/{reservationId}")
    public void deleteReservation(@PathVariable Long reservationId) throws ReservationNotFoundException {
        logger.info("delete reservation");
        iReservation.deleteReservation(reservationId);
    }


    @PutMapping("")
    public  ReservationDTO updateReservation(@RequestBody ReservationDTO reservationDTO){
        logger.info("update reservation");
        return iReservation.updateReservation(reservationDTO);
    }
    @GetMapping("/sort/{field}")
    public List<ReservationDTO> getReservationWithSort(@PathVariable String field){
        logger.info("sort get Reservation With Sort");
        return iReservation.getReservationsWithSort(field);
    }
    @PostMapping("")
    public ReservationDTO addReservation(@RequestBody ReservationDTO reservationDTO) throws Exception {
        logger.info("add Reservations");
        return  iReservation.addVehicleReservation(reservationDTO);
    }
}
