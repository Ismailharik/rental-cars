package com.example.officeservice.controller;


import com.example.officeservice.entities.Employee;
import com.example.officeservice.entities.Office;
import com.example.officeservice.repositories.EmployeeRepository;
import com.example.officeservice.repositories.OfficeRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/offices")
@CrossOrigin("*")
public class OfficeController {
    private static Logger logger = LoggerFactory.getLogger(OfficeController.class);

    private OfficeRepository officeRepository;
    private EmployeeRepository employeeRepository;

    @GetMapping("/employeeByOffice/{officeId}")
    public List<Employee> getEmployeesByOffice(@PathVariable Long officeId){
        logger.info("get All employees");
        Office office = officeRepository.findById(officeId).get();
        return employeeRepository.findByOffice(office);
    }
}
