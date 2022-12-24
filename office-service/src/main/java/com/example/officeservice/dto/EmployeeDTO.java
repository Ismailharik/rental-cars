package com.example.officeservice.dto;

import com.example.officeservice.entities.Employee;
import com.example.officeservice.entities.Office;
import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String phone;
    private String email;
    private String password;
    private String birthdate;
    private Office office;
//    private Employee employee;
}
