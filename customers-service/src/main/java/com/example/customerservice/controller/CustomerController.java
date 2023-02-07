package com.example.customerservice.controller;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.repository.CustomerRepository;
import com.example.customerservice.services.CustomerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;
    private static Logger log = LoggerFactory.getLogger(CustomerController.class);
//
//    @GetMapping("/search")
//    public Customer searchByEmail(@Param("email") String email){
//        return this.customerService.getCustomerByEmail(email);
//    }
    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId){
        log.info("get Customer by id "+ customerId);
        return this.customerService.getCustomerById(customerId);
    }
        @GetMapping()
        public List<Customer> getAllCustomers(){
            log.info("get all customers");
            return this.customerService.getAllCustomers();
        }
    @PostMapping()
    public Customer saveCustomer(@RequestBody Customer customer){
        log.info("Save customer : "+String.valueOf(customer));
        return this.customerService.addCustomer(customer);
    }
    @PutMapping()
    public Customer updateCustomer(@RequestBody Customer customer){
        log.info("update customer" +String.valueOf(customer));
        return this.customerService.updateCustomer(customer);
    }

    @GetMapping("/totalClient")
    public long totalClient(){
        return this.customerService.getTotalClient();
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id){
        log.info("delete customer with id "+id);
        this.customerService.deleteCustomer(id);
    }
}
