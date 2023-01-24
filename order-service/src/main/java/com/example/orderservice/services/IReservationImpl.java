package com.example.orderservice.services;

import com.example.orderservice.dto.ReservationDTO;
import com.example.orderservice.entities.Reservation;
import com.example.orderservice.exceptions.ReservationNotFoundException;
import com.example.orderservice.mappers.ReservationMapper;
import com.example.orderservice.model.Customer;
import com.example.orderservice.model.Vehicle;
import com.example.orderservice.oepnfeign.CustomerRestClient;
import com.example.orderservice.oepnfeign.VehicleRestClient;
import com.example.orderservice.repositories.ReservationRepository;
import com.example.orderservice.web.ReservationController;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class IReservationImpl implements IReservation {
    ReservationRepository reservationRepository;
    ReservationMapper reservationMapper;
    VehicleRestClient vehicleRestClient;
    CustomerRestClient customerRestClient;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;
    private static Logger logger = LoggerFactory.getLogger(ReservationController.class);


    @Override
    public List<ReservationDTO> getReservations() {


        List<Reservation> reservations = reservationRepository.findAll();

         return reservations.stream().map(r ->reservationMapper.fromReservationToReservationDTO(r) ).toList();
    }

    @Override
    public ReservationDTO getReservationById(Long reservationId) throws ReservationNotFoundException {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(()->new ReservationNotFoundException(reservationId));

        Customer customer =customerRestClient.getCustomerById(reservation.getCustomerId());
        Vehicle vehicle = vehicleRestClient.getVehicleById(reservation.getVehicleId());
        reservation.setCustomer(customer);
        reservation.setVehicle(vehicle);
        return reservationMapper.fromReservationToReservationDTO(reservation);
    }

    @Override
    public ReservationDTO reserveVehicle(ReservationDTO reservationDTO){

        Reservation reservation = reservationMapper.fromReservationDTOToReservation(reservationDTO);
        Customer customer = customerRestClient.saveCustomer(reservationDTO.getCustomer());
        Vehicle vehicle = vehicleRestClient.getVehicleById(reservation.getVehicleId());
        reservation.setVehicle(vehicle);
        reservation.setCustomer(customer);
        return reservationMapper.fromReservationToReservationDTO(reservationRepository.save(reservation));
    }
    @Override
    public List<ReservationDTO> vehicleReservations(Long vehicleId){
        List<Reservation> reservations = reservationRepository.getReservationsByVehicleId(vehicleId);
        return reservations.stream().map(v->reservationMapper.fromReservationToReservationDTO(v)).toList();
    }
    @Override
    public void deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).get();
        reservationRepository.delete(reservation);
    }

    @Override
    public ReservationDTO updateReservation(ReservationDTO reservationDTO) {
        Reservation reservation = reservationMapper.fromReservationDTOToReservation(reservationDTO);
        reservationRepository.findById(reservation.getId()).get();
        reservation =reservationRepository.save(reservation);
        return reservationMapper.fromReservationToReservationDTO(reservation);
    }

    @Override
    public List<ReservationDTO> getReservationWithSort(String field){
        List<Reservation> reservations = reservationRepository.findAll(Sort.by(Sort.Direction.DESC,field));
        return reservations.stream().map(r->reservationMapper.fromReservationToReservationDTO(r)).toList();
    }
}
