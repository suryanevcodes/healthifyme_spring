package com.project.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fitness.model.Nutritionist;
import com.project.fitness.service.NutritionistService;

@RestController
@RequestMapping("/nutritionist")
public class NutritionistController {
    @Autowired
    NutritionistService nutritionistService;
    
    @PostMapping("/create")
    public ResponseEntity<Nutritionist> create(@RequestBody Nutritionist nutritionist){
        var newnutritionist = nutritionistService.createnutritionist(nutritionist);
        return new ResponseEntity<>(newnutritionist,HttpStatus.CREATED);
    }

}
