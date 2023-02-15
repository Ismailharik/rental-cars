package com.example.orderservice.oepnfeign;

import com.example.orderservice.exceptions.CustomerNotFoundException;
import com.example.orderservice.model.Customer;
import feign.Headers;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//@FeignClient(name = "customers-service",configuration = FeignClientConfig.class)
@FeignClient(name = "customers-service",fallback = FeignFallback.class)

//@Retryable(value = {feign.RetryableException.class }, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier=2))
public interface CustomerRestClient {
    String AUTH_TOKEN = "Authorization";
    @Retry(name = "customers-service")
   // @TimeLimiter(name="customers-service")
    //@RateLimiter(name ="customers-service")
    @GetMapping("/customers/{id}")
    public Customer getCustomerById( @PathVariable Long id) throws CustomerNotFoundException;
    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer) ;

    @GetMapping("/customers/totalClient")
    long getTotalClient();
}