package com.example.orderservice.oepnfeign;

import com.example.orderservice.model.Customer;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "customers-service",configuration = FeignClientConfig.class)
@FeignClient(name = "customers-service",fallback = FeignFallback.class)
//@Retryable(value = {feign.RetryableException.class }, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier=2))
public interface CustomerRestClient {

    @Retry(name = "customers-service")
    //@RateLimiter(name ="customers-service")
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id) ;


}