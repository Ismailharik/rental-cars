package com.example.orderservice;

import com.example.orderservice.entities.Reservation;
import com.example.orderservice.entities.StockFeedback;
import com.example.orderservice.repositories.ReservationRepository;
import com.example.orderservice.repositories.StockFeedBackRepository;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient

public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ReservationRepository reservationRepository , StockFeedBackRepository sf){

		return args -> {
			List<Reservation> reservations = new ArrayList<>(10);
			for (int i = 0; i <10 ; i++) {
								reservations.add(new Reservation(null,new Date(),3,i+1,i+1L,
										(i+1)*10+i/10,false , false,
										 i+1L,null, null));
			}
			reservationRepository.saveAll(reservations);

			List<StockFeedback> stockFeedbacks = new ArrayList<>(10);
			for (int i = 0; i <10 ; i++) {
				stockFeedbacks.add( new StockFeedback(null, LocalDate.of(2001+i, 1+i, i+1),i*100+90*i/5));
			}
			sf.saveAll(stockFeedbacks);


		};
	}

//	@WebFilter("/*")
//	public class CORSFilter implements Filter {
//
//		public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//			HttpServletResponse response = (HttpServletResponse) res;
//			response.setHeader("Access-Control-Allow-Origin", "*");
//			response.setHeader("Access-Control-Allow-Methods", "POST");
//			chain.doFilter(req, res);
//		}
//
//		public void init(FilterConfig filterConfig) {}
//
//		public void destroy() {}
//	}

}
