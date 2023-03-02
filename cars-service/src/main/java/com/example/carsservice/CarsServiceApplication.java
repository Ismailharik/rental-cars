package com.example.carsservice;


import com.example.carsservice.entities.Category;
import com.example.carsservice.entities.Vehicle;
import com.example.carsservice.repositories.CategoryRepository;
import com.example.carsservice.repositories.VehicleRepository;
import com.example.carsservice.utilities.TypeVehicle;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Cars APIS",description = "Cars Api microservice"))
public class CarsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarsServiceApplication.class, args);
    }

       @Bean
       CommandLineRunner start(VehicleRepository vehicleRepository,
                               CategoryRepository categoryRepository
                            ) throws ParseException {
           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
           String date1 = "22-06-2006";


           List<Category> categories = new ArrayList<>();
           Category category1 = new Category();
           category1.setDescription("Mini");
           category1.setTypeVehicle(TypeVehicle.Standard);

           Category category2 = new Category();
           category2.setDescription("Elite");
           category2.setTypeVehicle(TypeVehicle.LUXURY);

           Category category3 = new Category();
           category3.setDescription("Economy");
           category3.setTypeVehicle(TypeVehicle.COMPACT);

           categories.add(category1);
           categories.add(category3);
           categories.add(category2);
           categoryRepository.saveAll(categories);

           List<Vehicle> vehicles = new ArrayList(10);
           for (int i = 0; i < 3; i++) {
               Vehicle vehicle = new Vehicle(i + 1L, "range" + i + 1, "range rover model 200" + i, (2344 + i * 5), new Date(),
                       294F, 48472 + i * 3, 2000 + i, true, null, null, null, categories.get(i), i + 1L);
               vehicles.add(vehicle);
           }
           for (int i = 3; i < 6; i++) {
               Vehicle vehicle = new Vehicle(i + 1L, "range" + i + 1, "range rover model 200" + i, (2344 + i * 5), new Date(),
                       294F, 48472 + i * 3, 2000, true, null, null, null, categories.get(i - 3), i + 1L);
               vehicles.add(vehicle);
           }
           for (int i = 6; i < 9; i++) {
               Vehicle vehicle = new Vehicle(i + 1L, "range" + i + 1, "range rover model 200" + i, (2344 + i * 5), new Date(),
                       294F, 48472 + i * 3, 2000 + i, true, null, null, null,categories.get(i - 6), i + 1L);
               vehicles.add(vehicle);
           }

           return args -> {
               vehicleRepository.saveAll(vehicles);
           };
       }
    @Configuration
    public class RestConfiguration implements RepositoryRestConfigurer {
        @Override
        public void configureRepositoryRestConfiguration(
                RepositoryRestConfiguration config, CorsRegistry cors) {
            config.exposeIdsFor(Category.class);
        }
    }

}
