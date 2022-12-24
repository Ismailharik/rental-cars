package com.example.officeservice;

import brave.sampler.Sampler;
import com.example.officeservice.entities.Employee;
import com.example.officeservice.entities.Office;
import com.example.officeservice.repositories.EmployeeRepository;
import com.example.officeservice.repositories.OfficeRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@FeignClient
@OpenAPIDefinition
public class OfficeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfficeServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(EmployeeRepository employeeRepository, OfficeRepository officeRepository){
		return args -> {
			List<Employee> employees = new ArrayList<>();
			List<Office> offices = new ArrayList<>();


			for (int i = 0; i < 10; i++) {
				Office office = new Office(i+1L,"casablanca"+1,"242","casablanca jamila "+i,null,null);
				offices.add(office);

			}
			officeRepository.saveAll(offices);
			for (int i = 0; i < 10; i++) {
				Employee employee=new Employee(i+1L,"casa"+1,"email"+i+"gmail.com","070366245","employee","casa Zn "+i+1,"2019-"+i+1+"-10",offices.get(i)) ;
				employees.add(employee);
			}
			for (int i = 9,j=0; i < 19; i++) {
				Employee employee=new Employee(i+1L,"casa"+1,"email"+i+"gmail.com","070366245","employee","casa Zn "+i+1,"2019-"+i+1+"-10",offices.get(j++)) ;
				employees.add(employee);
			}
			//
			employeeRepository.saveAll(employees);



		};
	}
	// exposing id for Employees
	@Configuration
	public class RestConfiguration implements RepositoryRestConfigurer {

		@Override
		public void configureRepositoryRestConfiguration(
				RepositoryRestConfiguration config, CorsRegistry cors) {
			config.exposeIdsFor(Employee.class,Office.class);
		}
	}
}
