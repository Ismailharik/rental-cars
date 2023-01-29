package com.example.carsservice.services;


import com.example.carsservice.entities.Category;
import com.example.carsservice.entities.Vehicle;
import com.example.carsservice.exceptions.CategoryNotFoundException;
import com.example.carsservice.mappers.CarsMapper;
import com.example.carsservice.repositories.CategoryRepository;
import com.example.carsservice.repositories.VehicleRepository;
import com.example.carsservice.dto.VehicleDTO;
import com.example.carsservice.exceptions.VehicleNotFoundException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class VehicleServiceImpl implements IVehicleService {
    private VehicleRepository vehiculeRepository;
    private CategoryRepository categoryRepository;
    private CarsMapper carsMapper;

    @Override
    public List<VehicleDTO> getAllVehicles() {
        log.info("select all categories");
        List<Vehicle> vehicles = vehiculeRepository.findAll();
        List<VehicleDTO> vehicleDTOS =vehicles.stream().map(vehicle -> carsMapper.fromVehicleToVehicleDto(vehicle)).toList();
        return vehicleDTOS;
    }

    @Override
    public VehicleDTO getVehicle(Long id) throws VehicleNotFoundException {
        log.info("get category with id : "+id);
        Vehicle vehicle = vehiculeRepository.findById(id).orElseThrow(()->new VehicleNotFoundException(id));
        return carsMapper.fromVehicleToVehicleDto(vehicle);
    }

    @Override
    public VehicleDTO addVehicle(VehicleDTO vehicleDTO,Long idCategory) throws CategoryNotFoundException {
        log.info("Add Vehicle");

        Vehicle vehicle =carsMapper.fromVehicleDtoToVehicle(vehicleDTO);
        Category category = categoryRepository.findById(idCategory).orElseThrow(()->new CategoryNotFoundException(idCategory));
        vehicle.setCategory(category);
        vehicle=vehiculeRepository.save(vehicle);
        return carsMapper.fromVehicleToVehicleDto(vehicle);
    }

    @Override
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO)throws VehicleNotFoundException {
        log.info("Update Vehicle");
        Vehicle vhl = vehiculeRepository.findById(vehicleDTO.getId()).orElseThrow(()->new VehicleNotFoundException(vehicleDTO.getId()));

        Vehicle vehicle =carsMapper.fromVehicleDtoToVehicle(vehicleDTO);
        vehicle.setCategory(vhl.getCategory());

        //IMAGES SHOULD PRESERVE THEIR OLD VALUES BACAUSE THEY WON'T CHANGE HERE (BECAUSE THEY ARE NULL IN vehicleDTO IN THIS CASE)
        // THERE SPECIFIC API FOR CHANGING IMG ...

         vehicle.setUrls(vhl.getUrls());
         vehicle.setImages(vhl.getImages());


         vehicle=vehiculeRepository.save(vehicle);
        return carsMapper.fromVehicleToVehicleDto(vehicle);
    }

    @Override
    public void deleteVehicle(Long idVehicle) throws VehicleNotFoundException{
        log.info("Delete Vehicle");
        Vehicle vehicle = vehiculeRepository.findById(idVehicle).orElseThrow(()->new VehicleNotFoundException(idVehicle));
            vehiculeRepository.delete(vehicle);
    }

    @Override
    public  List<VehicleDTO> findVehiclesWithPagination(int offset, int pageSize) {
        List<VehicleDTO> vehiclesDto = vehiculeRepository.findAll(PageRequest.of(offset, pageSize)).stream().map(
                vehicle -> carsMapper.fromVehicleToVehicleDto(vehicle)).toList();
        return vehiclesDto;
    }

    @Override
    public List<VehicleDTO> findVehiclesWithPaginationAndSorting(int offset, int pageSize, String field) {
        List<VehicleDTO> vehiclesDto = vehiculeRepository.findAll(
                PageRequest.of(offset, pageSize)
                        .withSort(Sort.by(field))
                ).stream().map(vehicle -> carsMapper.fromVehicleToVehicleDto(vehicle)).toList();
        return vehiclesDto;
    }


    @Override
    public List<VehicleDTO> getVehicleWithSorting(String field) {
        List<Vehicle> vehicles = vehiculeRepository.findAll(Sort.by(Sort.Direction.ASC,field));
        return vehicles.stream().map(vehicle -> carsMapper.fromVehicleToVehicleDto(vehicle)).toList();
    }

    @Override
    public List<VehicleDTO> getVehiclesByPrice(float minPrice, float maxPrice) throws VehicleNotFoundException {
            List<Vehicle> vehicles = vehiculeRepository.getVehicleByDailyPriceBetween(minPrice,maxPrice);
        return vehicles.stream().map(vehicle -> carsMapper.fromVehicleToVehicleDto(vehicle)).toList();
    }


    @Override
    public List<VehicleDTO> getVehiclesByLocation(int officeId) {
        List<Vehicle> vehicles = vehiculeRepository.findByOfficeId(officeId);
        return vehicles.stream().map(vehicle -> carsMapper.fromVehicleToVehicleDto(vehicle)).toList();
    }

    @Override
    public void deleteImageFromVehicle(Long id, int imageIndex) throws Exception {
        Vehicle vehicle = vehiculeRepository.findById(id).orElseThrow(()->new VehicleNotFoundException(id));

        String imageName = vehicle.getImages().get(imageIndex);
        vehicle.getImages().remove(imageIndex);
        vehicle.getUrls().remove(imageIndex);

        vehiculeRepository.save(vehicle);

        Files.delete(Paths.get(System.getProperty("user.home")+"/rental_app/vehicles/"+imageName));
    }
    @Override
    public void addImageToVehicle(Long id, MultipartFile file,String url) throws Exception {
        // Images name  = vahicleId + _ + index of this image
        Vehicle vehicle = vehiculeRepository.findById(id).orElseThrow(()->new CategoryNotFoundException(id));

//        String imageName = file.getOriginalFilename();
        int index = vehicle.getImages().size();
        String imageName = vehicle.getId() +"_"+ index+".jpg";
        String src = url+"/"+index; //the image url

        vehicle.getImages().add(imageName);// imageName + it's extension
        vehicle.getUrls().add(src);
        System.out.println(imageName);
        vehiculeRepository.save(vehicle);
        Files.write(Paths.get(System.getProperty("user.home")+"/rental_app/vehicles/"+imageName), file.getBytes());
        //vehiculeRepository.save(vehicle);
    }


    @Override
    public byte[] getVehicleImage(Long vehicleId,int index) throws VehicleNotFoundException, IOException {
        Vehicle vehicle = vehiculeRepository.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException(vehicleId));

        String img = vehicle.getImages().get(index);
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/rental_app/vehicles/"+img));
    }

    @Override
    public void updateImage(Long vehicleId,int imageIndex,MultipartFile file) throws IOException, VehicleNotFoundException {
        Vehicle vehicle = vehiculeRepository.findById(vehicleId).orElseThrow(()->new VehicleNotFoundException(vehicleId));
        String imageName = vehicle.getImages().get(imageIndex);
        Files.delete(Paths.get(System.getProperty("user.home")+"/rental_app/vehicles/"+imageName));
        Files.write(Paths.get(System.getProperty("user.home")+"/rental_app/vehicles/"+imageName), file.getBytes());
    }

}
