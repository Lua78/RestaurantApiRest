package com.api.demo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

@Entity
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reserve;
    @NotBlank(message = "Costumer name is require")
    private String costumer_name;
    @Positive(message = "Customer number must be greater than 0")
    private long costumer_number;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Date and time is require")
    private Date date_reserve;
    private boolean state = true;
    private int id_menu;


    public void setCostumer_number(long costumer_number) {
        this.costumer_number = costumer_number;
    }

    public Reserve(String costumer_name, Date date_reserve, int id_menu) {
        this.costumer_name = costumer_name;
        this.date_reserve = date_reserve;
        this.id_menu = id_menu;
    }
    public  Reserve(){

    }

    public Long getId_reserve() {
        return id_reserve;
    }


    public String getCostumer_name() {
        return costumer_name;
    }

    public void setCostumer_name(String costumer_name) {
        this.costumer_name = costumer_name;
    }

    public long getCostumer_number() {
        return costumer_number;
    }


    public Date getDate_reserve() {
        return date_reserve;
    }

    public void setDate_reserve(Date date_reserve) {
        this.date_reserve = date_reserve;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }
}
