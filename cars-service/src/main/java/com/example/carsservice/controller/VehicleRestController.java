package com.example.carsservice.controller;


import com.example.carsservice.entities.Vehicle;
import com.example.carsservice.exceptions.CategoryNotFoundException;
import com.example.carsservice.exceptions.VehicleNotFoundException;
import com.example.carsservice.services.IVehicleService;
import com.example.carsservice.dto.VehicleDTO;
import jakarta.servlet.http.HttpServletRequest;
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



    @GetMapping(path = "/images/{vehicleId}/{index}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getVehicleImage(@PathVariable(name = "vehicleId") Long vehicleId, @PathVariable int index) throws Exception{
        return iVehicleService.getVehicleImage(vehicleId,index);
    }




    @PostMapping(path = "/{idCategory}")
    public VehicleDTO addVehicle(@RequestBody VehicleDTO vehicleDTO, @PathVariable Long idCategory) throws CategoryNotFoundException {
        log.info("add vehicle");
        return iVehicleService.addVehicle(vehicleDTO,idCategory);
    }

    @DeleteMapping("/{vehicleId}")
    public void deleteVehicle(@PathVariable  Long vehicleId) throws Exception {
        iVehicleService.deleteVehicle(vehicleId);
    }
    @PutMapping(path="/{vehicleId}")
    public VehicleDTO updateVehicle(@PathVariable Long vehicleId,@RequestBody  VehicleDTO vehicleDTO) throws VehicleNotFoundException {
        vehicleDTO.setId(vehicleId);
        return iVehicleService.updateVehicle(vehicleDTO);
    }
    @PostMapping(value = "/images/{vehicleId}")
    public  void addImageToVehicle(HttpServletRequest request, @PathVariable("vehicleId") Long vehicleId, @RequestBody MultipartFile file) throws Exception {
        log.info("add vehicle");

        String url = request.getRequestURL().toString();
        iVehicleService.addImageToVehicle(vehicleId,file,url);
    }
    /*
     * this endpoints destined for latest vehicles
     * they will be used in the home page ( showing the latest images )
     * they will be registered by vehicle Id
     * */
    // i think I should delete this image
    @PostMapping(value = "/images/latest/{vehicleId}")
    public  void addLatestVehiclesImages( HttpServletRequest request,@PathVariable("vehicleId") Long vehicleId,MultipartFile file) throws Exception {
        /*
               Injecting HttpServletRequest to get the full url in intention to
            I have got the HttpServletRequest to get the api called for store the full src image
            while I know the actual api to make it dynamic , if I change the port or the api
            I won't find any problem
        */
        String url = request.getRequestURL().toString();
        iVehicleService.addImageToVehicle(vehicleId,file,url);
    }
    @DeleteMapping(value = "/images/{vehicleId}/{imageIndex}")
    public void deleteImage(@PathVariable Long vehicleId,@PathVariable int imageIndex) throws Exception {
        System.out.println(vehicleId +"---" +imageIndex);
        iVehicleService.deleteImageFromVehicle(vehicleId,imageIndex);
    }
    @PutMapping("/images/{vehicleId}/{imageIndex}")
    public void updateImage(@PathVariable Long vehicleId,@PathVariable int imageIndex,MultipartFile file) throws VehicleNotFoundException, Exception{
        iVehicleService.updateImage(vehicleId, imageIndex,file);
    }

}
