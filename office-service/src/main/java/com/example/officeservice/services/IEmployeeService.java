package com.example.officeservice.services;

import com.example.officeservice.dto.EmployeeDTO;
import com.example.officeservice.entities.Employee;

import java.util.List;

public interface IEmployeeService {//already made by RestRepository
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
    void deleteEmployee(Long employeeId);
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById();
    EmployeeDTO addRoleToEmployee();

}
