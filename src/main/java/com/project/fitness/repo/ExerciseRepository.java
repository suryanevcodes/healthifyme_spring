package com.project.fitness.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.fitness.model.Exercises;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercises,Integer> {

}
