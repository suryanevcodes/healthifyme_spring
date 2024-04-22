package com.project.fitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.fitness.model.Nutritionist;
import com.project.fitness.repo.NutritionistRepo;

@Service
public class NutritionistService {
        @Autowired
        NutritionistRepo nutritionistRepo ;

        public Nutritionist createnutritionist(Nutritionist nutritionist){
            return nutritionistRepo.save(nutritionist);
        }
}
