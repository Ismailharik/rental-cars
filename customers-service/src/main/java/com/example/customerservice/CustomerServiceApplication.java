package com.example.customerservice;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.repository.CustomerRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition
@EnableMongoRepositories(basePackageClasses = CustomerRepository.class)
@EnableDiscoveryClient
public class CustomerServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository) {
		List<Customer> customers = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			customers.add(new Customer(i+1L, "053543" + i, "ismail "+i+1,"ismail" + i + "gmail.com","1234", new Date(),true));
		}
		return args -> {
			customers.get(4).setStatus(false);
			customers.get(7).setStatus(false);

			customerRepository.saveAll(customers);

//			System.out.println(customerRepository.findAll());
		};
	}


}
