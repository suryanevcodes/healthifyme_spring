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

import com.project.fitness.dto.UserRequest;
import com.project.fitness.model.Exercises;
import com.project.fitness.model.Meal;
import com.project.fitness.model.Nutritionist;
import com.project.fitness.model.Trainer;
import com.project.fitness.model.User;
import com.project.fitness.repo.NutritionistRepo;
import com.project.fitness.repo.TrainerRepo;
import com.project.fitness.repo.UserRepository;
import com.project.fitness.service.MealService;
import com.project.fitness.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  UserService userService;
    @Autowired
    private MealService mealService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainerRepo trainerRepo;
    @Autowired
    private NutritionistRepo nutritionistRepo;



    @PutMapping("/changeuser/{trainerId}/{userid}")
    public ResponseEntity<User> changetrainerforuser(@PathVariable int trainerId, @PathVariable int userid){
        Optional<Trainer> trainer = trainerRepo.findById(trainerId);
        Optional<User> user = userRepository.findById(userid);
        if(trainer.isPresent() && user.isPresent()){
            var newuser = userService.changeTrainer(trainerId, userid);
            return new ResponseEntity<>(newuser,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/changenutri/{nutriId}/{userid}")
    public ResponseEntity<User> changenutritionistforuser(@PathVariable int nutriId, @PathVariable int userid){
       Optional<Nutritionist> newnutri= nutritionistRepo.findById(nutriId);
        Optional<User> user = userRepository.findById(userid);
        if(newnutri.isPresent() && user.isPresent()){
            var newuser = userService.changeNutritionist(nutriId, userid);
            return new ResponseEntity<>(newuser,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/deleteexercisebyuserid/{uid}/{exeid}")
    public ResponseEntity<String> deleteexercisebyuserid(@PathVariable int uid, @PathVariable int exeid){
        try{
            var user = userService.deleteexercisebyid(uid,exeid);
            return new ResponseEntity<>("Deleted the Exercise",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Not deleted that exercises",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @PutMapping("/deletemealidbyuserid/{userid}/{mealid}")
    public ResponseEntity<String> deletemealbyuserid(@PathVariable int userid , @PathVariable int mealid){
        try{
          var user = userService.deletemealbyId(userid, mealid);
          Optional<Meal> meal = mealService.findById(mealid);
          if(!(meal.isPresent())){
                return new ResponseEntity<>("meal not found",HttpStatus.NOT_FOUND);
          }
           return new ResponseEntity<>("Deleted the meal ",HttpStatus.OK); 
        }catch(Exception e){
            return new ResponseEntity<>("not deleted ",HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }


    @PostMapping("/insert/{userId}/{mealId}")
    public ResponseEntity<String> insetmealanduser(@PathVariable int userId, @PathVariable int mealId) {
    try {
        User user = userService.mealidmapuserid(userId, mealId);
        if (user != null) {
            return new ResponseEntity<>("Meals added to the user", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("User or meal not found", HttpStatus.NOT_FOUND);
        }
    } catch (Exception e) {
        return new ResponseEntity<>(" Meal alredy exits", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@PostMapping("/insertex/{userId}/{exerciseId}")
public ResponseEntity<String> exerciseanduser(@PathVariable int userId, @PathVariable int exerciseId) {
try {
    User user = userService.exerciseidmapuserid(userId, exerciseId);
    if (user != null) {
        return new ResponseEntity<>("User received and excercise Added", HttpStatus.CREATED);
    } else {
        return new ResponseEntity<>("User or Exercise not found", HttpStatus.NOT_FOUND);
    }
} catch (Exception e) {
    return new ResponseEntity<>(" Exercises alredy exits", HttpStatus.INTERNAL_SERVER_ERROR);
}

}

@GetMapping("findallmeansbyid/{id}")
public ResponseEntity<Set<Meal>> findmealofuser(@PathVariable int id){
    try{

        return new ResponseEntity<>(userService.findmealsbyid(id),HttpStatus.FOUND);
    }
    catch(Exception e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

@GetMapping("findallexercisesbyid/{id}")
public ResponseEntity<Set<Exercises>> findexofuser(@PathVariable int id){
    try{
        return new ResponseEntity<>(userService.findexercisesbyid(id),HttpStatus.FOUND);
    }
    catch(Exception e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> getAllEndUsers() {
        List<User> EndUsers = userService.findAll();
        return ResponseEntity.ok(EndUsers);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<User> getEndUserById(@PathVariable int id) {
        Optional<User> EndUser =userService.findById(id);
        return EndUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<User> createEndUser(@RequestBody UserRequest EndUser) {
        User createdEndUser = userService.addUser(EndUser);
        return new ResponseEntity<>(createdEndUser,HttpStatus.CREATED);
    }

   @PutMapping("/update/{id}")
   public ResponseEntity<User> updateEndUser(@PathVariable int id, @RequestBody User User) {
       if (userService.findById(id).isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       User updatedEndUser = userService.save(User);
       return ResponseEntity.ok(updatedEndUser);
   }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEndUser(@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllEndUsers() {
        userService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
