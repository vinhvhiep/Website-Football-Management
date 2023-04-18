package com.example.doannmcnpm.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Table
public class SoccerFieldService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Size(max = 50)
    private String nameService;

    @Column
    @Positive
    @PositiveOrZero
    private double price;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "yardLocation_id")
    YardLocation yardLocation;

    public SoccerFieldService(int id, String nameService, double price, YardLocation yardLocation) {
        this.id = id;
        this.nameService = nameService;
        this.price = price;
        this.yardLocation=yardLocation;
    }

    public SoccerFieldService() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public YardLocation getYardLocation() {
        return yardLocation;
    }

    public void setYardLocation(YardLocation yardLocation) {
        this.yardLocation = yardLocation;
    }
}
