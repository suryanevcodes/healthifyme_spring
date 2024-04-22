package com.project.fitness.controller;

import java.util.List;
import java.util.Optional;

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
import com.project.fitness.repo.ExerciseRepository;
import com.project.fitness.service.ExerciseService;


@RestController()
@RequestMapping("/exercise")
public class ExerciseController {
    @Autowired
    private  ExerciseService exerciseService;
    @Autowired
    private ExerciseRepository exerciseRepository;

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
    public ResponseEntity<String> deletemeals (@PathVariable int id) {
        try{
        exerciseService.deleteById(id);
        Optional<Exercises> exc = exerciseRepository.findById(id);
        if(!exc.isPresent()){
            return new ResponseEntity<>("Exercise not found ",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("not deleted",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllEndUsers() {
       exerciseService.deleteAll();
        return ResponseEntity.noContent().build();
    }



}
