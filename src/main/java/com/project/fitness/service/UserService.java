package com.project.fitness.service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.fitness.dto.UserRequest;
import com.project.fitness.model.Exercises;
import com.project.fitness.model.Meal;
import com.project.fitness.model.Nutritionist;
import com.project.fitness.model.Trainer;
import com.project.fitness.model.User;
import com.project.fitness.repo.ExerciseRepository;
import com.project.fitness.repo.GymRepo;
import com.project.fitness.repo.MealRepository;
import com.project.fitness.repo.NutritionistRepo;
import com.project.fitness.repo.TrainerRepo;
import com.project.fitness.repo.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private TrainerRepo trainerRepo;

    @Autowired
    private NutritionistRepo nutritionistRepo;

    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private GymRepo gymRepo;
    
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
    
    // here we are adding the user with the trainer id , nutritioner id , gymowner id

    public User addUser(UserRequest endUser){
        var gymowner = gymRepo.findById(endUser.getGymownerid()).get();
        var trainer = trainerRepo.findById(endUser.getTrainerId()).get();
        var nutritionist = nutritionistRepo.findById(endUser.getNutritionistId()).get();
        var user = User.builder()
                    .LastName(endUser.getLastName())
                    .age(endUser.getAge())
                    .email(endUser.getEmail())
                    .firstName(endUser.getFirstName())
                    .weight(endUser.getWeight())
                    .password(endUser.getPassword())
                    .gymowner(gymowner)
                    .trainer(trainer)
                    .nutritionist(nutritionist)
                    .build();
        return userRepository.save(user);
    }

// THIS CODE IS FOR CHANGING THE TRAINER FOR THE USER BY PASSING THE USER ID AND TRAINER ID
// HERE FOR THIS USERID WE NEED TO UPDATE THE TRAINERID THAT IS trainerid.

    public User changeTrainer(int Trainerid , int userid){

       Optional<Trainer> trainer = trainerRepo.findById(Trainerid);
       Optional<User> user = userRepository.findById(userid);   
         var  nutri = user.get().getNutritionist();
         user.get().setTrainer(trainer.get());
         user.get().setNutritionist(nutri);
         return userRepository.save(user.get());
    }
    // THIS CODE IS FOR CHANGING THE nutritioner FOR THE USER BY PASSING THE USER ID AND nutritioner ID
// HERE FOR THIS USERID WE NEED TO UPDATE THE nutritionerid THAT IS nutritionistid.


    public User changeNutritionist(int nutritionistid , int userid){

        Optional<Nutritionist> nutri = nutritionistRepo.findById(nutritionistid);
        Optional<User> user = userRepository.findById(userid);   
          //var  nutris = user.get().getNutritionist();
          user.get().setNutritionist(nutri.get());
          return userRepository.save(user.get());
     }

   

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

  
    // here in this code we are adding exercise for the particular userid

    public User exerciseidmapuserid (int userId, int exerciseId) {
        // Retrieve user and meal objects from repositories
        User user = userRepository.findById(userId).orElse(null);
        Exercises exercise = exerciseRepository.findById(exerciseId).orElse(null);
    
        // Check if user and meal exist
        if (user != null &&  exercise != null) {
            // Retrieve the user's existing meals
            Set<Exercises> exercises = user.getExercises();
    
            // Check if the meal is already associated with the user
            boolean exerciseExists = exercises.stream().anyMatch(m -> m.getId() == exerciseId);
    
            // If the meal is not already associated, add it
            if (!exerciseExists) {
                exercises.add(exercise);
                user.setExercises(exercises);
                return userRepository.save(user);
            }
        }
        return user; // Or handle the case when either user or meal is not found
    }

    // Here we are going to add meals to the user with respective meal and userids

    public User mealidmapuserid(int userId, int mealId) {
        // Retrieve user and meal objects from repositories
        User user = userRepository.findById(userId).orElse(null);
        Meal meal = mealRepository.findById(mealId).orElse(null);
    
        // Check if user and meal exist
        if (user != null && meal != null) {
            // Retrieve the user's existing meals
            Set<Meal> meals = user.getMeals();
    
            // Check if the meal is already associated with the user
            boolean mealExists = meals.stream().anyMatch(m -> m.getId() == mealId);
    
            // If the meal is not already associated, add it
            if (!mealExists) {
                meals.add(meal);
                user.setMeals(meals);
                return userRepository.save(user);
            }
        }
        return user; // Or handle the case when either user or meal is not found
    }


    // Here in this we are going to find all the meals for the respective user by userid

    public Set<Meal> findmealsbyid(int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            Set<Meal> meals = user.getMeals();
            System.out.println("Number of meals found for user with ID " + id + ": " + meals.size());
            for (Meal meal : meals) {
                System.out.println("Meal ID: " + meal.getId() + ", Name: " + meal.getName() + ", Description: " + meal.getDescription());
            }
            return meals;
        } else {
            System.out.println("User with ID " + id + " not found");
            return Collections.emptySet(); // Return empty set if user not found
        }
    }

    // here we are going to find all the exercises for the user with the help of userid

    public Set<Exercises> findexercisesbyid(int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            Set<Exercises> exs = user.getExercises();
            System.out.println("Number of meals found for user with ID " + id + ": " + exs.size());
            for (Exercises ex : exs) {
                System.out.println("Meal ID: " + ex.getId() + ", Name: " + ex.getName() + ", Description: " + ex.getDescription());
            }
            return exs;
        } else {
            System.out.println("User with ID " + id + " not found");
            return Collections.emptySet(); // Return empty set if user not found
        }
    }

    // here we can delete the meals associated with the respective user and meal id

    public User deletemealbyId(int userId, int Mealid){
        var user = userRepository.findById(userId).orElse(null);
        var meal = mealRepository.findById(Mealid).orElse(null);
        if(user != null && meal != null){
            user.getMeals().remove(meal);
            userRepository.save(user);
            return user;
        }
        return user;
    }

    // here we can delte the exercises associated with the respecitve  exercise id and meal id

    public User deleteexercisebyid(int userId , int mealid){
        var user = userRepository.findById(userId).orElse(null);
        var exercise = exerciseRepository.findById(mealid).orElse(null);

        if(user != null && exercise!= null){
            user.getExercises().remove(exercise);
            userRepository.save(user);
            return user;
        }
        return user;
    }
    
    
}

