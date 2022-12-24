package com.example.carsservice.controller;


import com.example.carsservice.exceptions.CategoryNotFoundException;
import com.example.carsservice.exceptions.VehicleNotFoundException;
import com.example.carsservice.services.IVehicleService;
import com.example.carsservice.dto.VehicleDTO;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/vehicles")
@CrossOrigin("*")
public class VehicleRestController {
    private static Logger log = LoggerFactory.getLogger(VehicleRestController.class);

    private IVehicleService iVehicleService;

    @GetMapping("")
    public List<VehicleDTO> getAllVehicles(){
        log.info("Get all vehicles");
        return iVehicleService.getAllVehicles();
    }
    @GetMapping("/{vehicleId}")
    public VehicleDTO getVehicle(@PathVariable Long vehicleId) throws VehicleNotFoundException {
        return iVehicleService.getVehicle(vehicleId);
    }
    @PostMapping(path = "/{idCategory}")
    public VehicleDTO addVehicle(@RequestBody VehicleDTO vehicleDTO,@PathVariable Long idCategory) throws CategoryNotFoundException {
        return iVehicleService.addVehicle(vehicleDTO,idCategory);
    }
    @PostMapping("/addImage/{vehicleId}")
    public  void addImageToVehicle( @PathVariable Long vehicleId,MultipartFile file) throws Exception {
        iVehicleService.addImageToVehicle(vehicleId,file);
    }
    @DeleteMapping("/{vehicleId}")
    public void deleteVehicle(@PathVariable  Long vehicleId) throws VehicleNotFoundException {
        iVehicleService.deleteVehicle(vehicleId);
    }

    @GetMapping("/prices/{min-max}")
    public List<VehicleDTO> getVehiclesByPrice(@PathVariable("min-max")  float[] priceRange) throws VehicleNotFoundException {
        float minPrice= priceRange[0];
        float maxPrice =priceRange[1];

        if(minPrice>= 0 && maxPrice>minPrice){
            return iVehicleService.getVehiclesByPrice(minPrice,maxPrice);
        }else {
                return iVehicleService.getVehicleWithSorting("dailyPrice");
        }
    }

    @GetMapping("/sortedBy/{field}")
    public List<VehicleDTO> getVehiclesWithSort(@PathVariable String field){
        return iVehicleService.getVehicleWithSorting(field);
    }
    @RequestMapping(path="/products/{vehicleId}",method = RequestMethod.PATCH)
    public VehicleDTO updateVehicle(@PathVariable Long vehicleId,@RequestBody  VehicleDTO vehicleDTO) throws VehicleNotFoundException {
            vehicleDTO.setId(vehicleId);
            return iVehicleService.updateVehicle(vehicleDTO);
    }
}
