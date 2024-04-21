package com.project.fitness.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import com.project.fitness.model.Gymowner;
import com.project.fitness.model.User;
import com.project.fitness.service.GymService;

@RestController
@RequestMapping("/gymowner")
public class GymController {

    @Autowired
    private GymService gymService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Gymowner>> getAllEndUsers() {
        List<Gymowner> EndUsers = gymService.findAll();
        return ResponseEntity.ok(EndUsers);
    }

    @GetMapping("/findallusers/{id}")
    public ResponseEntity<Set<User>> findalleuser(@PathVariable int id){
        Set<User> users = gymService.findallusers(id);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    
    
    @GetMapping("/findById/{id}")
    public ResponseEntity<Gymowner> getEndUserById(@PathVariable int id) {
        Optional<Gymowner> EndUser = gymService.findById(id);
        return EndUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create" )
    public ResponseEntity<Gymowner> createEndUser(@RequestBody Gymowner gymuser) {
        Gymowner gymowner = gymService.create(gymuser);
        return new ResponseEntity<>(gymowner,HttpStatus.CREATED);
        
    }

   @PutMapping("/update/{id}")
   public ResponseEntity<Gymowner> updateEndUser(@PathVariable int id, @RequestBody Gymowner User) {
       if (gymService.findById(id).isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       Gymowner updatedEndUser = gymService.create(User);
       return ResponseEntity.ok(updatedEndUser);
   }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEndUser(@PathVariable int id) {
        gymService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllEndUsers() {
        gymService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
