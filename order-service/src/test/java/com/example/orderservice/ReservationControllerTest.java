package com.example.orderservice;

import com.example.orderservice.dto.ReservationDTO;
import com.example.orderservice.services.IReservation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationControllerTest {
    @Autowired
    TestRestTemplate restTemplate;
     @MockBean
    IReservation iReservation;

     @Autowired
    ObjectMapper objectMapper;
    @Test
    public void getReservations() throws JsonProcessingException {
        ReservationDTO r1 = new ReservationDTO();
        r1.setId(7L); r1.setVehicleId(3L); r1.setTotalPrice(95113.3f);
        ReservationDTO r2 = new ReservationDTO();
        r2.setId(9L); r2.setVehicleId(3L); r2.setTotalPrice(5455.22f);

        List<ReservationDTO> reservations =  List.of(r1,r2);
        // any vehicle Id will get this reservations List in this tests

        Mockito.when(iReservation.getReservations()).thenReturn(reservations);

        ResponseEntity<List> respEntity = restTemplate.getForEntity("/reservations", List.class);
        System.out.println(objectMapper.writeValueAsString( respEntity.getBody().get(0)));
        System.out.println(objectMapper.writeValueAsString(r1));


        assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(objectMapper.writeValueAsString( respEntity.getBody().get(0))).isEqualTo(objectMapper.writeValueAsString(r1));

    }
}
