package com.java.RateSystem.controller;

import com.java.RateSystem.models.ResponseObject;
import com.java.RateSystem.models.Rating;
import com.java.RateSystem.services.RatingService;
import com.java.RateSystem.services.CaculateAverageCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @Autowired
    CaculateAverageCore caculateAverageCore;

    @GetMapping("")
    List<Rating> getAllRating(){
        return ratingService.getAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findByUiId(@PathVariable UUID id){
        Optional<Rating> foundRating = ratingService.findRating(id);
        return foundRating.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok","Querry product successfully", foundRating)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("False","Cannot find", foundRating)
                );
    }


    //Add Calulate Average Core
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertRating(@RequestBody Rating newRating) {
        Optional<Rating> foundRating = ratingService.findRating(newRating.getUuId());
        if (foundRating.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "UUID hasn't existed","")
            );
        } else {
            ratingService.insertRating(newRating);
            caculateAverageCore.updateAC(newRating);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Insert Rating successfully","")
            );
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteRating (@PathVariable UUID id){
        boolean exists = ratingService.isRatingExist(id);
        if(exists)
        {
            ratingService.deleteRating(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Delete Rating successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed", "Cannot find Rating to delete ", "")
        );
    }
}
