package com.example.customerservice.services;

import com.example.customerservice.entities.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
   List<Customer> getAllCustomers();
    Customer getCustomerById(Long customerId);
    Customer getCustomerByEmail(String email);
    Customer addCustomer(Customer customer);
    Customer updateCustomer(Customer customer);


    long getTotalClient();

    void deleteCustomer(Long id);
}
