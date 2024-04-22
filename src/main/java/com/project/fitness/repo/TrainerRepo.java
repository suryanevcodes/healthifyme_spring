package com.project.fitness.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.fitness.model.Trainer;

public interface TrainerRepo extends JpaRepository<Trainer,Integer> {

}
