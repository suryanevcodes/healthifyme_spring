package com.project.fitness.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.fitness.model.Gymowner;
import com.project.fitness.model.User;
import com.project.fitness.repo.GymRepo;

@Service
public class GymService {

    @Autowired
    private GymRepo gymrepo;

    public List<Gymowner> findAll() {
        return gymrepo.findAll();
    }

    public Optional<Gymowner> findById(int id) {
        return gymrepo.findById(id);
    }

    public Gymowner create(Gymowner user) {
        return gymrepo.save(user);
    }
    
    public Set<User> findallusers(int gymownerid) {
        Gymowner gymowner = gymrepo.findById(gymownerid).orElse(null);
        if (gymowner != null) {
            Set<User> users = gymowner.getUsers();
            System.out.println("Number of users found for gym owner with ID " + gymownerid + ": " + users.size());
            for (User user : users) {
                System.out.println("User ID: " + user.getId() + ", Name: " + user.getFirstName() + " " + user.getLastName() + ", Email: " + user.getEmail());
            }
            return users;
        } else {
            System.out.println("Gym owner with ID " + gymownerid + " not found");
            return Collections.emptySet(); // Return empty set if gym owner not found
        }
    }

    // public Set<User> findallusers(int gymownerid){
    //     Gymowner gymowner = gymrepo.findById(gymownerid).get();
    //     return gymowner.getUsers();
    // }

    public void deleteById(int id) {
        gymrepo.deleteById(id);
    }

    public void deleteAll() {
        gymrepo.deleteAll();
    }


}
