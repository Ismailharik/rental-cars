package com.example.orderservice.oepnfeign;

import com.example.orderservice.model.Customer;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//@FeignClient(name = "customers-service",configuration = FeignClientConfig.class)
@FeignClient(name = "customers-service",fallback = FeignFallback.class)

//@Retryable(value = {feign.RetryableException.class }, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier=2))
public interface CustomerRestClient {

    @Retry(name = "customers-service")
    //@RateLimiter(name ="customers-service")
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id) ;
    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer) ;

    @GetMapping("/customers/totalClient")
    long getTotalClient();
}