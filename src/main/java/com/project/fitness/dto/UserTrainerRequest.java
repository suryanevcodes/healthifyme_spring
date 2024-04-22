package com.project.fitness.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserTrainerRequest {
    private String firstName;
    private String LastName;
    private int age;
    private double weight;
    private String email;
    private String password;
    private double Experience;
    private int trainerId;

}
