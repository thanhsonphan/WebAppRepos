package com.java.RateSystem.services;

import com.java.RateSystem.models.Rating;
import com.java.RateSystem.repository.RatingRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RatingService {
    @Autowired
    RatingRespository ratingRespository;

    public List<Rating> getAll() {
//        System.out.println(ratingRespository.findAll()+"Repo");
        return ratingRespository.findAll();
    }

    public Optional<Rating> findRating(@PathVariable UUID id) {
        return ratingRespository.findById(id);
    }

    public Rating insertRating(@RequestBody Rating newRating) {

        return ratingRespository.save(newRating);
    }

    public void deleteRating(@PathVariable UUID id) {
        ratingRespository.deleteById(id);
    }

    public boolean isRatingExist(@PathVariable UUID id) {
        return ratingRespository.existsById(id);
    }

}