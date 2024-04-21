package com.project.fitness.model;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String LastName;
    private int age;
    private double weight;
    private String email;
    private String password;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_meal" ,
                joinColumns = @JoinColumn (name = "user_id"),
                inverseJoinColumns = @JoinColumn(name ="meal_id") )
    private Set<Meal> meals;

    @JsonManagedReference
    @ManyToMany(cascade =CascadeType.ALL)
    @JoinTable(name = "user_exercises" ,
                joinColumns = @JoinColumn(name ="user_id") ,
                inverseJoinColumns = @JoinColumn(name = "exercise_id")) 
    private Set<Exercises> exercises;
    
    // @OneToMany(cascade = CascadeType.ALL,
    //             fetch = FetchType.LAZY,
    //             mappedBy = "user" )
    // private List<Tracker> taracker;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "gym_owner_id")
    private Gymowner gymowner;

    //@JsonBackReference
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Trainer trainer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Nutritionist nutritionist;


}
