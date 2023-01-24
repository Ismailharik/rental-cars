package com.example.officeservice.services;

import com.example.officeservice.dto.OfficeDTO;
import com.example.officeservice.exceptions.OfficeNotFoundException;

import java.util.List;

public interface OfficeServices {
    List<OfficeDTO> getAllOffices();
    OfficeDTO getOfficeById(Long officeId)throws OfficeNotFoundException;

}
