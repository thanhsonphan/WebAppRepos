package com.java.RateSystem.controller;

import com.java.RateSystem.models.ResponseObject;
import com.java.RateSystem.models.Servicerate;
import com.java.RateSystem.models.User;
import com.java.RateSystem.repository.ServiceRepository;
import com.java.RateSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("")
    List<Servicerate> getAllServiceName() {
        return serviceRepository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long ServiceId){
        Optional<Servicerate> foundProduct = serviceRepository.findById(ServiceId);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok","Querry Service successfully", foundProduct)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("false", "Cannot find Service with id =" + ServiceId,"")
                );

    }

    //insert data
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Servicerate newService){
        Optional<Servicerate> foundService = serviceRepository.findByServiceName(newService.getServiceName().trim());
        return foundService.isPresent() ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("failed", "Service Name already taken", "")
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("Ok","Insert Service successfully",serviceRepository.save(newService))
                );
    }

    //Update data
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateService(@RequestBody Servicerate newService, @PathVariable Long ServiceId){
        Servicerate updateService =  serviceRepository.findById(ServiceId)
                .map(service -> {
                    service.setServiceName(newService.getServiceName());
                    service.setImage(newService.getImage());
                    service.setDescription(newService.getDescription());
                    return serviceRepository.save(service);
                }).orElseGet(()->{
                    newService.setServiceId(ServiceId);
                    return serviceRepository.save(newService);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok","Update Service successfully",serviceRepository.save(newService))
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct (@PathVariable Long ServiceId){
        boolean exists = serviceRepository.existsById(ServiceId);
        if(exists)
        {
            serviceRepository.deleteById(ServiceId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Delete Service successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed", "Cannot find Service to delete ", "")
        );
    }
}