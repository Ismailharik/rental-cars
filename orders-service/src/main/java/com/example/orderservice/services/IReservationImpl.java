package com.example.orderservice.services;

import com.example.orderservice.dto.ReservationDTO;
import com.example.orderservice.entities.Reservation;
import com.example.orderservice.entities.StockFeedback;
import com.example.orderservice.event.OrderPlacedEvent;
import com.example.orderservice.exceptions.CustomerNotFoundException;
import com.example.orderservice.exceptions.ReservationNotFoundException;
import com.example.orderservice.exceptions.VehicleNotFoundException;
import com.example.orderservice.mappers.ReservationMapper;
import com.example.orderservice.model.Customer;
import com.example.orderservice.model.Vehicle;
import com.example.orderservice.oepnfeign.CustomerRestClient;
import com.example.orderservice.oepnfeign.VehicleRestClient;
import com.example.orderservice.repositories.ReservationRepository;
import com.example.orderservice.repositories.StockFeedBackRepository;
import lombok.AllArgsConstructor;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.data.domain.Sort;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@AllArgsConstructor
@Service
public class IReservationImpl implements IReservation {

    ReservationRepository reservationRepository;
    StockFeedBackRepository stockFeedBackRepository;
    ReservationMapper reservationMapper;
    VehicleRestClient vehicleRestClient;
    CustomerRestClient customerRestClient;
//    private final WebClient webClient;

    private KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

    @Override
    public List<ReservationDTO> getReservations() {


        List<Reservation> reservations = reservationRepository.findAll();


         return reservations.stream().map(r ->reservationMapper.fromReservationToReservationDTO(r) ).toList();
    }

    @Override
    public ReservationDTO getReservationById(Long reservationId) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(()->new ReservationNotFoundException(reservationId));

        //call external services

        Customer customer =customerRestClient.getCustomerById(reservation.getCustomerId());
        Vehicle vehicle = vehicleRestClient.getVehicleById(reservation.getVehicleId());

        reservation.setCustomer(customer);
        reservation.setVehicle(vehicle);
        return reservationMapper.fromReservationToReservationDTO(reservation);
    }

    @Override
    public ReservationDTO addVehicleReservation(ReservationDTO reservationDTO) throws Exception {

        Reservation reservation = reservationMapper.fromReservationDTOToReservation(reservationDTO);

        //call external services
        Customer customer = customerRestClient.saveCustomer(reservationDTO.getCustomer());
        Vehicle vehicle = vehicleRestClient.getVehicleById(reservation.getVehicleId());


        reservation.setTotalPrice( reservation.getDuration()* vehicle.getDailyPrice());
        reservation.setVehicle(vehicle);
        reservation.setCustomer(customer);

        if (customer==null){
            throw new CustomerNotFoundException(reservation.getCustomerId());
        }
        if(vehicle==null){
            throw  new VehicleNotFoundException(reservation.getVehicleId());
        }
        if(customer!=null){ // I will accept order while cars service isn't responding


            reservationDTO = reservationMapper.fromReservationToReservationDTO(reservationRepository.save(reservation));
            /*I should update order feed back after admin confirmation but just to be simple I will update
            * It when user add reservation
            * */
            this.orderFeedBack(reservation.getTotalPrice());

            //send orderPlaced event as object to the notificationTopic
             kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(reservationDTO.getId()));
            return reservationDTO;
        }else {
            throw new Exception("Please verify user information's ");
        }
    }
    @Override
    public List<ReservationDTO> vehicleReservations(Long vehicleId){
        List<Reservation> reservations = reservationRepository.getReservationsByVehicleId(vehicleId);
        return reservations.stream().map(v->reservationMapper.fromReservationToReservationDTO(v)).toList();
    }



    public void orderFeedBack( double price) {

        /*
        * I see last reservation in the database , if it added in the same month with the actual month
        * I will increase only  total price of this month
        * Else (it's a new month case , so I will register new record for the new month )
        *
        * */
        StockFeedback stockFeedback=this.stockFeedBackRepository.findLastRegistration();
        if(stockFeedback!=null && stockFeedback.getDate().getMonth()== LocalDate.now().getMonth()){
            /*CASE SAME MONTH */

            double totalPrice = stockFeedback.getTotalPrice()+price;
            stockFeedback.setTotalPrice(totalPrice);
        }else{
            /*REGISTER NEW MONTH */

            stockFeedback = new StockFeedback(null,LocalDate.now(),price);
        }
        stockFeedBackRepository.save(stockFeedback);
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
    public List<ReservationDTO>  getReservationsWithSort(String field){
        List<Reservation> reservations = reservationRepository.findAll(Sort.by(Sort.Direction.DESC,field));
        return reservations.stream().map(r->reservationMapper.fromReservationToReservationDTO(r)).toList();
    }
}
