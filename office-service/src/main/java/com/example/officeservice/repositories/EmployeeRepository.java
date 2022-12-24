package com.example.officeservice.repositories;

import com.example.officeservice.entities.Employee;
import com.example.officeservice.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
        List<Employee> findByOffice(Office office);
}
