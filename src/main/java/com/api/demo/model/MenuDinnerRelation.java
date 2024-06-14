package com.api.demo.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu_dinner")
public class MenuDinnerRelation  {

    @EmbeddedId
    private MenuDinner id;

    public MenuDinner getId() {
        return id;
    }

    public void setId(MenuDinner id) {
        this.id = id;
    }
}
