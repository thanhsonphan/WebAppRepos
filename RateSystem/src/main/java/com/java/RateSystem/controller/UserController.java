package com.java.RateSystem.controller;

import com.java.RateSystem.models.ResponseObject;
import com.java.RateSystem.models.User;
import com.java.RateSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    List<User> getAllUserName(){return userRepository.findAll();}

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject>findByUserId(@PathVariable Long id){
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok","Querry product successfully", foundUser)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("False","Cannot findd", foundUser)
                );
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser){
        Optional<User> foundUser = userRepository.findByEmail(newUser.getEmail().trim());
        return foundUser.isPresent() ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("failed", "Email already taken", "")
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("Ok","Register User Name successfully",userRepository.save(newUser))
                );
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateUser (@RequestBody User newUser, @PathVariable Long id){
        User updateUser =  userRepository.findById(id)
                .map(user -> {
                    user.setUserName(newUser.getUserName());
                    user.setEmail(newUser.getEmail());
                    user.setRole(newUser.getRole());
                    return userRepository.save(user);
                }).orElseGet(()->{
                    newUser.setUserId(id);
                    return userRepository.save(newUser);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok","Update User successfully",userRepository.save(newUser))
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteUser (@PathVariable Long id){
        boolean exists = userRepository.existsById(id);
        if(exists)
        {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Delete User successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed", "Cannot find User to delete ", "")
        );
    }
}
