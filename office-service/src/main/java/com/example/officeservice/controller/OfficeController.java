package com.example.officeservice.controller;


import com.example.officeservice.dto.OfficeDTO;
import com.example.officeservice.entities.Employee;
import com.example.officeservice.entities.Office;
import com.example.officeservice.exceptions.OfficeNotFoundException;
import com.example.officeservice.repositories.EmployeeRepository;
import com.example.officeservice.repositories.OfficeRepository;
import com.example.officeservice.services.OfficeServices;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/offices")

public class OfficeController {
    private static Logger logger = LoggerFactory.getLogger(OfficeController.class);

    private OfficeRepository officeRepository;
    private EmployeeRepository employeeRepository;
    private OfficeServices officeServices;

    @GetMapping("/employeeByOffice/{officeId}")
    public List<Employee> getEmployeesByOffice(@PathVariable Long officeId){
        logger.info("get All employees");
        Office office = officeRepository.findById(officeId).get();
        return employeeRepository.findByOffice(office);
    }
    @GetMapping("")
    public List<OfficeDTO> getAllOffices(){
        logger.info("get All offices");
        return officeServices.getAllOffices();
    }
    @GetMapping("/{id}")
    public OfficeDTO getOfficeById(@PathVariable Long id) throws OfficeNotFoundException {
        logger.info("get Office by Id");
        return officeServices.getOfficeById(id);
    }
}
