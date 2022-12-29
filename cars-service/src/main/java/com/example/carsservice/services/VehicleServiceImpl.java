package com.example.carsservice.services;


import com.example.carsservice.entities.Category;
import com.example.carsservice.entities.Vehicle;
import com.example.carsservice.exceptions.CategoryNotFoundException;
import com.example.carsservice.mappers.CarsMapper;
import com.example.carsservice.repositories.CategoryRepository;
import com.example.carsservice.repositories.VehiculeRepository;
import com.example.carsservice.dto.VehicleDTO;
import com.example.carsservice.exceptions.VehicleNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    private VehiculeRepository vehiculeRepository;
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
    public void addImageToVehicle(Long id, MultipartFile file) throws Exception {
        Vehicle vehicle = vehiculeRepository.findById(id).orElseThrow(()->new CategoryNotFoundException(id));

        String imageName = file.getOriginalFilename();
        System.out.println(imageName);
        vehicle.getImages().add(imageName);// imageName + it's extension
        vehiculeRepository.save(vehicle);
        Files.write(Paths.get(System.getProperty("user.home")+"/rental-app/vehicles/"+imageName), file.getBytes());
        //vehiculeRepository.save(vehicle);
    }

    @Override
    public List<byte[]>     getVehicleImages(Long vehicleId) throws VehicleNotFoundException {
        Vehicle vehicle = vehiculeRepository.findById(
                vehicleId).orElseThrow(() -> new VehicleNotFoundException(vehicleId));

        return vehicle.getImages().stream().
                map(img -> {
                    try {
                        return Files.readAllBytes(
                                Paths.get(System.getProperty("user.home")+"/rental-app/vehicles/"+img));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();

    }

    @Override
    public byte[] getVehicleImage(Long vehicleId) throws VehicleNotFoundException, IOException {
        Vehicle vehicle = vehiculeRepository.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException(vehicleId));

        String img = vehicle.getImages().get(0);
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/rental-app/vehicles/"+img));
    }

    @Override
    public List<VehicleDTO> getVehiclesByLocation(int officeId) {
        List<Vehicle> vehicles = vehiculeRepository.findByOfficeId(officeId);
        return vehicles.stream().map(vehicle -> carsMapper.fromVehicleToVehicleDto(vehicle)).toList();
    }


}
