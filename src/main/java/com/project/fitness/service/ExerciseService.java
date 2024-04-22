package com.project.fitness.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.fitness.model.Exercises;
import com.project.fitness.repo.ExerciseRepository;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Exercises> findAll() {
        return exerciseRepository.findAll();
    }

    public Optional<Exercises> findById(int id) {
        return exerciseRepository.findById(id);
    }

    public Exercises save(Exercises exercises) {
        return exerciseRepository.save(exercises);
    }

    public void deleteById(int id) {
        exerciseRepository.deleteById(id);
    }

    public void deleteAll() {
        exerciseRepository.deleteAll();
    }
}



