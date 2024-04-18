package com.project.fitness.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.fitness.model.Gymowner;

@Repository
public interface GymRepo extends JpaRepository<Gymowner,Integer> {

}
