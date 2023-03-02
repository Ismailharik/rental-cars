package com.example.customerservice.services;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

 @Service 
 @AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
     private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {

        return this.customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).get();
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        
        return customerRepository.findByEmail(email);
    }

    @Override
    public Customer addCustomer(Customer customer) {
       Customer registeredCustomer = customerRepository.findByEmail(customer.getEmail());
       long id = customerRepository.count()+1;//
        if(registeredCustomer==null){
            customer.setId(id);
            System.out.println(customer);
            return customerRepository.save(customer);
        }
        return registeredCustomer;
    }

     @Override
     public Customer updateCustomer(Customer customer) {

         return this.customerRepository.save(customer);
     }

     @Override
     public long getTotalClient() {
         return this.customerRepository.count();
     }

     @Override
     public void deleteCustomer(Long id) {
        Customer customer = this.customerRepository.findById(id).get();
         this.customerRepository.delete(customer);
     }
 }
