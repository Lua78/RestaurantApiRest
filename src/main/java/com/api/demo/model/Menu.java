package com.api.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_menu;
    @NotEmpty(message = "Name is require")
    private String name;
    @Positive(message = "Price is must be greater than 0")
    private double price;
    private boolean state = true;

    public Menu(String name, double price, boolean state) {
        this.name = name;
        this.price = price;
        this.state = state;
    }
    public Menu(){

    }

    public Long getId_menu() {
        return id_menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
