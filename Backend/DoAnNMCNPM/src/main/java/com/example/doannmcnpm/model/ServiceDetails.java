package com.example.doannmcnpm.model;


import javax.persistence.*;

@Entity
@Table

public class ServiceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
