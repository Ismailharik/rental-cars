package com.example.orderservice;

import com.example.orderservice.dto.ReservationDTO;
import com.example.orderservice.services.IReservation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AllArgsConstructor
public class ReservationControllerMockMvcTest {


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
