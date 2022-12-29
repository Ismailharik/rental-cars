package com.example.officeservice.repositories;


import com.example.officeservice.entities.Employee;
import com.example.officeservice.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.List;
@CrossOrigin("*")
@RepositoryRestResource(collectionResourceRel = "offices", path = "offices")
public interface OfficeRepository extends JpaRepository<Office,Long> {

    @RestResource(path = "name")
    List<Office> findByCityContaining( String city);



}
