package com.project.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.fitness.model.Trainer;


import com.project.fitness.service.TrainerService;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    @Autowired
    public TrainerService trainerService;

    @PostMapping("/create")
    public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer){
        Trainer newTrainer = trainerService.addTrainer(trainer);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTrainer);
    }

    

   
}
    

