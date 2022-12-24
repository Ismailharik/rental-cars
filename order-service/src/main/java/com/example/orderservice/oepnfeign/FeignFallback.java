package com.example.orderservice.oepnfeign;

import com.example.orderservice.exceptions.CustomerNotFoundException;
import com.example.orderservice.model.Customer;
import com.example.orderservice.model.Vehicle;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FeignFallback implements CustomerRestClient,VehicleRestClient {

    // getCustomerById will be return nnu to customer in case fall back from open feign
    @Override
    public Customer getCustomerById(Long id)  {
        System.out.println("Customer not found");
        return null;

    }
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        System.out.println("Customer not found");
        return null;
    }
}
