package com.project.fitness.model;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Exercises {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String reputations;
    private String sets;
    private String calPerRep;
    @JsonBackReference
    //@JsonIgnoreProperties("exercises")
    @ManyToMany(mappedBy = "exercises")
    private Set<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReputations() {
        return reputations;
    }

    public void setReputations(String reputations) {
        this.reputations = reputations;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getCalPerRep() {
        return calPerRep;
    }

    public void setCalPerRep(String calPerRep) {
        this.calPerRep = calPerRep;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
