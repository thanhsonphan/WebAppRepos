package com.java.RateSystem.services;

import com.java.RateSystem.models.Rating;
import com.java.RateSystem.models.Services;
import com.java.RateSystem.repository.RatingRespository;
import com.java.RateSystem.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class CaculateAverageCore {
    @Autowired
    RatingRespository ratingRespository;
    @Autowired
    ServiceRepository serviceRepository;
    public OptionalDouble caculateAC(Rating newRating){
        List<Rating> findRatingbyServiceID = ratingRespository.findByServiceId(newRating.getServiceId());
        OptionalDouble AC=  findRatingbyServiceID.stream().mapToInt(i-> i.getPoint()).average();
        return AC;
    }
    public void updateAC(Rating newRating){
        Services foundServices = serviceRepository.findByOtherId(newRating.getServiceId()).get(0);
        Double AC = caculateAC(newRating).orElse(0);
        if(foundServices != null){
            foundServices.setEverageCore(AC);
            serviceRepository.save(foundServices);
        }
    }
}

