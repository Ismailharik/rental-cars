package com.example.orderservice;







import com.example.orderservice.entities.Reservation;
import com.example.orderservice.repositories.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.StatusAggregator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ReservationRepository reservationRepository ){

		return args -> {
			List<Reservation> reservations = new ArrayList<>(10);
			for (int i = 0; i <10 ; i++) {
								reservations.add(new Reservation(null,new Date(),new Date(),345*i,false,false, i+1L, null
										, i+1L, null));
			}
			reservationRepository.saveAll(reservations);
		};
	}

}
