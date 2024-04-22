package com.project.fitness.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.fitness.model.Nutritionist;

public interface NutritionistRepo extends JpaRepository<Nutritionist,Integer>{

}
