package com.example.doannmcnpm.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Bill {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date createdate;

    @Column
    private double totalMoney;

    @Column
    private int status;


}
