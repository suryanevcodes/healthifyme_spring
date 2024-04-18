package com.project.fitness.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.fitness.model.Meal;
import com.project.fitness.repo.MealRepository;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;
   

    public List<Meal> findAll() {
        return mealRepository.findAll();
    }

    public Optional<Meal> findById(int id) {
        return mealRepository.findById(id);
    }

    

    public Meal save(Meal meal) {
        return mealRepository.save(meal);
    }

    public void deleteById(int id) {
        mealRepository.deleteById(id);
    }

    public void deleteAll() {
        mealRepository.deleteAll();
    }
}


