package com.api.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

@Entity
public class Dinner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_dinner;
    @NotEmpty(message = "Name es require")
    private String name;
    @NotEmpty(message = "Description is require")
    private String description;
    @Positive(message = "Price must be greater than 0")
    private double price;
    private boolean active;

    public Dinner(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Dinner() {

    }

    public Long getId_dinner() {
        return id_dinner;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
