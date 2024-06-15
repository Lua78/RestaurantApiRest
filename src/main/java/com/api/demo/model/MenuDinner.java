package com.api.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;

@Entity
public class MenuDinner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Id menu is require")
    @Positive(message = "Id menu is not valid")
    private int id_menu;
    @NotNull(message = "Id dinner is require")
    @Positive(message = "Id dinner is not valid")
    private int id_dinner;



    public MenuDinner(int id_dinner, int id_menu) {
        this.id_dinner = id_dinner;
        this.id_menu = id_menu;
    }
    public  MenuDinner(){}


    public int getMenu_id() {
        return id_menu;
    }

    public void setMenu_id(int id_menu) {
        this.id_menu = id_menu;
    }

    public int getDinner_id() {
        return id_dinner;
    }

    public void setDinner_id(int id_dinner) {
        this.id_dinner = id_dinner;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
