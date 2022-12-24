package com.example.orderservice;

import com.example.orderservice.dto.ReservationDTO;
import com.example.orderservice.exceptions.CustomerNotFoundException;
import com.example.orderservice.exceptions.ReservationNotFoundException;
import com.example.orderservice.mappers.ReservationMapper;
import com.example.orderservice.services.IReservation;
import com.example.orderservice.web.ReservationController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ReservationControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IReservation iReservation; // reservation Services
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getReservationById() throws Exception {
        ReservationDTO r1 = new ReservationDTO();
        r1.setId(7L); r1.setVehicleId(3L); r1.setTotalPrice(95113.3f);
        Mockito.when(iReservation.getReservationById(Mockito.anyLong())).thenReturn(r1);

        mockMvc.perform(
                get("/reservations/{id}",2L)
                .contentType("application/json")
            ).andExpect(status().isOk());

    }
    @Test
    public void addReservation() throws Exception {
        ReservationDTO r1 = new ReservationDTO();
        r1.setId(7L); r1.setVehicleId(3L); r1.setTotalPrice(95113.3f);
        Mockito.when(iReservation.reserveVehicle(Mockito.any(ReservationDTO.class))).thenReturn(r1);

        mockMvc.perform(
                post("/reservations")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(r1))
        ).andExpect(status().isOk());

    }
//    public void
}
