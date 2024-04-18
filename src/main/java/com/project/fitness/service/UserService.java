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
import com.project.fitness.model.User;
import com.project.fitness.repo.ExerciseRepository;
import com.project.fitness.repo.GymRepo;
import com.project.fitness.repo.MealRepository;
import com.project.fitness.repo.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MealRepository mealRepository;

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
    public User addUser(UserRequest endUser){
        var gymowner = gymRepo.findById(endUser.getGymownerid()).get();
        var user = User.builder()
                    .LastName(endUser.getLastName())
                    .age(endUser.getAge())
                    .email(endUser.getEmail())
                    .firstName(endUser.getFirstName())
                    .weight(endUser.getWeight())
                    .password(endUser.getPassword())
                    .gymowner(gymowner)
                    .build();
                    
        return userRepository.save(user);
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

    // public User mealidmapuserid(int userId, int mealId){
    //     User user = userRepository.findById(userId).get();
    //     Meal meal = mealRepository.findById(mealId).get();
    //     Set<Meal> meals = user.getMeals();
    //     meals.add(meal);
    //     user.setMeals(meals);
    //     return userRepository.save(user);
          
    // }

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

    // public Set<Meal> findmealsbyid(int id){
    //     User user = userRepository.findById(id).orElse(null);
    //     return user.getMeals();
    // }

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
    
    
}

