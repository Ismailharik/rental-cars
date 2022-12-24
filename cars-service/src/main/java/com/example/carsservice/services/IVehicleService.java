package com.example.carsservice.services;

import com.example.carsservice.dto.VehicleDTO;
import com.example.carsservice.exceptions.CategoryNotFoundException;
import com.example.carsservice.exceptions.VehicleNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IVehicleService {
    List<VehicleDTO> getAllVehicles();
    VehicleDTO getVehicle(Long id) throws VehicleNotFoundException;
    List<VehicleDTO> getVehicleWithSorting(String field);

    List<VehicleDTO> getVehiclesByPrice(float minPrice, float maxPrice) throws VehicleNotFoundException;

    void addImageToVehicle(Long id, MultipartFile multipartFile) throws Exception;
    VehicleDTO addVehicle(VehicleDTO vehicleDTO,Long idCategory) throws CategoryNotFoundException;
    VehicleDTO updateVehicle(VehicleDTO vehicleDTO) throws VehicleNotFoundException;
    void deleteVehicle(Long idVehicle) throws VehicleNotFoundException;


}
