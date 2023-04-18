package com.example.doannmcnpm.model;


import javax.persistence.*;

@Entity
@Table
public class BillDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


}
