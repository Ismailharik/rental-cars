package com.example.carsservice;


import com.example.carsservice.entities.Category;
import com.example.carsservice.entities.Vehicle;
import com.example.carsservice.repositories.CategoryRepository;
import com.example.carsservice.repositories.VehiculeRepository;
import com.example.carsservice.utilities.TypeVehicle;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Cars API",description = "Cars Api microservice"))
public class CarsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarsServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(VehiculeRepository vehiculeRepository,
                            CategoryRepository categoryRepository
                            ) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date1 = "22-06-2006";



        Category category1 =  new Category();
        category1.setDescription("Economy");
        category1.setTypeVehicle(TypeVehicle.Standard);


        List<Vehicle> vehicles = new ArrayList(10);
        for (int i = 0; i <10 ; i++) {
            Vehicle vehicle = new Vehicle(i+1L,"range1","range rover model 200"+i,  2344+i*5,new Date(),
                    294F, 48472+i*3,true,  null,null,category1,i+1L);
            vehicles.add(vehicle);
        }

        return args -> {
            categoryRepository.save(category1);
            vehiculeRepository.saveAll(vehicles);
        };
    }

}
