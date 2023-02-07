package com.example.officeservice.services;

import com.example.officeservice.dto.OfficeDTO;
import com.example.officeservice.entities.Office;
import com.example.officeservice.exceptions.OfficeNotFoundException;
import com.example.officeservice.mappers.OfficeMapper;
import com.example.officeservice.repositories.OfficeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class OfficeServicesImpl implements OfficeServices {
    private OfficeMapper officeMapper;
    private OfficeRepository officeRepository;
    @Override
    public List<OfficeDTO> getAllOffices() {
        List<Office> offices = this.officeRepository.findAll();
        return offices.stream().map(office -> this.officeMapper.fromOfficeToOfficeDTO(office)).toList();
    }

    @Override
    public OfficeDTO getOfficeById(Long officeId) throws OfficeNotFoundException {
        Office office=this.officeRepository.findById(officeId).orElseThrow(()->new OfficeNotFoundException(officeId));
        return officeMapper.fromOfficeToOfficeDTO(office);
    }
}
