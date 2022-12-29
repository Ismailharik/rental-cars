package com.example.carsservice.controller;


import com.example.carsservice.exceptions.CategoryNotFoundException;
import com.example.carsservice.exceptions.VehicleNotFoundException;
import com.example.carsservice.services.IVehicleService;
import com.example.carsservice.dto.VehicleDTO;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
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

    @DeleteMapping("/{vehicleId}")
    public void deleteVehicle(@PathVariable  Long vehicleId) throws VehicleNotFoundException {
        iVehicleService.deleteVehicle(vehicleId);
    }

    @GetMapping("/prices")
    public List<VehicleDTO> getVehiclesByPrice(@RequestParam(value = "maxPrice",defaultValue = "1000000000") float minPrice,@RequestParam(value = "minPrice",defaultValue = "0") float maxPrice) throws VehicleNotFoundException {
        System.out.println("maxPrice : "+maxPrice+" minPrice"+minPrice);
        if(minPrice>= 0 && maxPrice>minPrice){
            return iVehicleService.getVehiclesByPrice(minPrice,maxPrice);
        }else {
                return iVehicleService.getVehicleWithSorting("dailyPrice");
        }
    }

    @GetMapping("/sort/{field}")
    public List<VehicleDTO> getVehiclesWithSort(@PathVariable String field){
        return iVehicleService.getVehicleWithSorting(field);
    }
    @RequestMapping(path="/products/{vehicleId}",method = RequestMethod.PATCH)
    public VehicleDTO updateVehicle(@PathVariable Long vehicleId,@RequestBody  VehicleDTO vehicleDTO) throws VehicleNotFoundException {
            vehicleDTO.setId(vehicleId);
            return iVehicleService.updateVehicle(vehicleDTO);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public  List<VehicleDTO>findVehiclesWithPagination(
            @PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize ){

        return this.iVehicleService.findVehiclesWithPagination(offset,pageSize);
    }
    @GetMapping("/location/{officeId}")
    public List<VehicleDTO> getVehiclesByLocation(@PathVariable("officeId") int officeId){
        return this.iVehicleService.getVehiclesByLocation(officeId);
    }
    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    public  List<VehicleDTO>findVehiclesWithPaginationAndSort(
            @PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize,@PathVariable String field ){

        return this.iVehicleService.findVehiclesWithPaginationAndSorting(offset,pageSize,field);
    }
    @PostMapping(value = "/images/{vehicleId}")
    public  void addImageToVehicle(@PathVariable("vehicleId") Long vehicleId,MultipartFile file) throws Exception {
        iVehicleService.addImageToVehicle(vehicleId,file);
    }
    @GetMapping(path = "/images/{vehicleId}")
    public List<byte[]> getVehicleImages(@PathVariable(name = "vehicleId") Long vehicleId) throws Exception{

        // get one img
   //   return Files.readAllBytes(
     //           Paths.get(System.getProperty("user.home")+"/rental-app/vehicles/car2.jpg"));
        return iVehicleService.getVehicleImages(vehicleId);
    }
    @GetMapping(path = "/image/{vehicleId}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getVehicleImage(@PathVariable(name = "vehicleId") Long vehicleId) throws Exception{
        return iVehicleService.getVehicleImage(vehicleId);
    }

    /*
    * this endpoints destined for latest vehicles
    * they will be used in the home page ( showing the latest images )
    * they will be registered by vehicle Id
    * */
    @GetMapping(path = "/image/latest/{vehicleId}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getLatestVehiclesImages(@PathVariable(name = "vehicleId") Long vehicleId) throws Exception{
        return iVehicleService.getVehicleImage(vehicleId);
    }
    @PostMapping(value = "/images/latest/{vehicleId}")
    public  void addLatestVehiclesImages(@PathVariable("vehicleId") Long vehicleId,MultipartFile file) throws Exception {
        iVehicleService.addImageToVehicle(vehicleId,file);
    }
}
