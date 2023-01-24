package com.example.officeservice.mappers;

import com.example.officeservice.dto.OfficeDTO;
import com.example.officeservice.entities.Office;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OfficeMapper {
    public OfficeDTO fromOfficeToOfficeDTO(Office office){
        OfficeDTO officeDTO = new OfficeDTO();
        BeanUtils.copyProperties(office,officeDTO);
        return officeDTO;
    }
    public Office fromOfficeDTOToOffice(OfficeDTO officeDTO){
        Office office = new Office();
        BeanUtils.copyProperties(officeDTO,office);
        return office;
    }
}
