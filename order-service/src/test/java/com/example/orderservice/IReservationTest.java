package com.example.orderservice;

import com.example.orderservice.dto.ReservationDTO;
import com.example.orderservice.mappers.ReservationMapper;
import com.example.orderservice.repositories.ReservationRepository;
import com.example.orderservice.services.IReservation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IReservationTest {
                /*
                * INTEGRATION TEST*/
    @Autowired
    IReservation iReservation;
    @MockBean
    ReservationRepository reservationRepository;

    @Autowired
    ReservationMapper mapper;

    @Test
    public void vehicleReservationsTest(){
        ReservationDTO r1 = new ReservationDTO();
        r1.setId(3L); r1.setVehicleId(2L); r1.setTotalPrice(233);
        ReservationDTO r2 = new ReservationDTO();
        r2.setId(8L); r2.setVehicleId(2L); r2.setTotalPrice(23455.22f);

        List<ReservationDTO> reservations =  List.of(r1,r2);
        // any vehicle Id will get this reservations List in this tests
        Mockito.when(reservationRepository.getReservationsByVehicleId(Mockito.anyLong())).
                thenReturn(reservations.stream().map(
                        reservationDTO->mapper.fromReservationDTOToReservation(reservationDTO)
                ).toList());

        System.out.println(reservations);

        assertEquals(3,iReservation.vehicleReservations(1000L).get(0).getId());
        assertEquals(8,iReservation.vehicleReservations(3355L).get(1).getId());
    }

}
