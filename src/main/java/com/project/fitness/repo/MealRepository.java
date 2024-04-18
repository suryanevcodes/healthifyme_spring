package com.project.fitness.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.fitness.model.Meal;

public interface MealRepository  extends JpaRepository<Meal, Integer>  {

}
