package com.example.customerservice.repository;

import com.example.customerservice.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface CustomerRepository extends MongoRepository<Customer,Long> {
    Customer findByEmail(String email);
    List<Customer> findAll();
}