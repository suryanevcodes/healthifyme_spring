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

import com.project.fitness.model.Meal;
import com.project.fitness.service.MealService;

@RestController
@RequestMapping("/meal")
public class MealController {

    @Autowired
    private  MealService mealService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Meal>> getAllEndUsers() {
        List<Meal> meals = mealService.findAll();
        return ResponseEntity.ok(meals);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Meal> getEndUserById(@PathVariable int id) {
        Optional<Meal> meal =mealService.findById(id);
        return meal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    

    @PostMapping(value = "/create")
    public ResponseEntity<Meal> createEndUser(@RequestBody Meal meal) {
        Meal meals = mealService.save(meal);
        return new ResponseEntity<>(meals,HttpStatus.CREATED);
    }

   @PutMapping("/update/{id}")
   public ResponseEntity<Meal> updateEndUser(@PathVariable int id, @RequestBody Meal meal) {
       if (mealService.findById(id).isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       Meal newmeal = mealService.save(meal);
       return ResponseEntity.ok(newmeal);
   }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEndUser(@PathVariable int id) {
        mealService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllEndUsers() {
        mealService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
