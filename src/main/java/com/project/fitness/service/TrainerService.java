package com.project.fitness.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.fitness.model.Trainer;
import com.project.fitness.repo.TrainerRepo;
import com.project.fitness.repo.UserRepository;

@Service
public class TrainerService {
    @Autowired
    TrainerRepo trainerRepo;
    @Autowired
    UserRepository userRepository;

    public Trainer addTrainer(Trainer trainer){
        return trainerRepo.save(trainer);
    }

    public Optional<Trainer> findtrainerbyid(int id){
        return trainerRepo.findById(id);
    }

    

}
