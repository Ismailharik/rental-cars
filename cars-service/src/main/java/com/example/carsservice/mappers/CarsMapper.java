package com.example.carsservice.mappers;

import com.example.carsservice.entities.Vehicle;
import com.example.carsservice.dto.VehicleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public  class CarsMapper {
    public VehicleDTO fromVehicleToVehicleDto(Vehicle vehicle){
        VehicleDTO vehicleDTO = new VehicleDTO();
        BeanUtils.copyProperties(vehicle,vehicleDTO);
        return vehicleDTO;
    }
    public Vehicle fromVehicleDtoToVehicle(VehicleDTO vehicleDTO)  {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleDTO,vehicle);
        return vehicle;
    }

}
