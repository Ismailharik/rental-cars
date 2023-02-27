package com.example.carsservice.services;

import com.example.carsservice.dto.VehicleDTO;
import com.example.carsservice.entities.Vehicle;
import com.example.carsservice.exceptions.CategoryNotFoundException;
import com.example.carsservice.exceptions.VehicleNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IVehicleService {
    List<VehicleDTO> getAllVehicles();
    VehicleDTO getVehicle(Long id) throws VehicleNotFoundException;
    List<VehicleDTO> getVehicleWithSorting(String field);

    List<VehicleDTO> getVehiclesByPrice(float minPrice, float maxPrice) throws VehicleNotFoundException;

    VehicleDTO addVehicle(VehicleDTO vehicleDTO,Long idCategory) throws CategoryNotFoundException;
    VehicleDTO updateVehicle(VehicleDTO vehicleDTO) throws VehicleNotFoundException;
    void deleteVehicle(Long idVehicle) throws VehicleNotFoundException;

    List<VehicleDTO> findVehiclesWithPagination(int offset,int pageSize);
    List<VehicleDTO> findVehiclesWithPaginationAndSorting(int offset,int pageSize,String field);

    void addImageToVehicle(Long id, MultipartFile multipartFile) throws Exception;

    byte[] getVehicleImage(Long vehicleId,int index) throws VehicleNotFoundException, IOException;
    List<VehicleDTO> getVehiclesByLocation(int officeId);
    void deleteImageFromVehicle(Long vehicleId,int imageIndex) throws Exception;

    void updateImage(Long vehicleId,int imageIndex,MultipartFile multipartFile) throws VehicleNotFoundException,IOException;
}