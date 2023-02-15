package com.example.orderservice.oepnfeign;

import com.example.orderservice.exceptions.VehicleNotFoundException;
import com.example.orderservice.model.Customer;
import com.example.orderservice.model.Vehicle;
import feign.Headers;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "cars-service",fallback = FeignFallback.class)
public interface VehicleRestClient{

    @Retry(name = "cars-service")
    @GetMapping("/vehicles/{id}")
    public Vehicle getVehicleById( @PathVariable Long id)  ;
}
