package com.api.demo.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class MenuDinner implements Serializable {
    private int menu_id;
    private int dinner_id;

    public MenuDinner(int dinner_id, int menu_id) {
        this.dinner_id = dinner_id;
        this.menu_id = menu_id;
    }
    public  MenuDinner(){}


    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public int getDinner_id() {
        return dinner_id;
    }

    public void setDinner_id(int dinner_id) {
        this.dinner_id = dinner_id;
    }
}
