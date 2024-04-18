package com.project.fitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fitness.model.Exercises;
import com.project.fitness.service.ExerciseService;


@RestController()
@RequestMapping("/exercise")
public class ExerciseController {
    @Autowired
    private  ExerciseService exerciseService;

    @PostMapping(value = "/create")
    public ResponseEntity<Exercises> create(@RequestBody Exercises exercise){
        Exercises newexer = exerciseService.save(exercise);
        return new ResponseEntity<>(newexer,HttpStatus.CREATED);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Exercises> findbyId(@PathVariable int id){
        if(exerciseService.findById(id).isPresent()){
            return ResponseEntity.ok().body(exerciseService.findById(id).get());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getall")
    public List<Exercises> findAll(){
        return exerciseService.findAll();
    }

    
   @PutMapping("/update/{id}")
   public ResponseEntity<Exercises> updateEndUser(@PathVariable int id, @RequestBody Exercises exercise) {
       if (exerciseService.findById(id).isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       Exercises updatedExercise = exerciseService.save(exercise);
       return ResponseEntity.ok(updatedExercise);
   }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEndUser(@PathVariable int id) {
        exerciseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllEndUsers() {
       exerciseService.deleteAll();
        return ResponseEntity.noContent().build();
    }



}
