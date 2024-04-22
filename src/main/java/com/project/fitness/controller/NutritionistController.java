package com.project.fitness.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Nutritionist> findbyId(@PathVariable int id){
       Optional <Nutritionist> nutritionist = nutritionistService.getnutritionist(id);
       if(nutritionist.isPresent()){
        return new ResponseEntity<>(nutritionist.get(),HttpStatus.FOUND);
       }
       else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    }

}
