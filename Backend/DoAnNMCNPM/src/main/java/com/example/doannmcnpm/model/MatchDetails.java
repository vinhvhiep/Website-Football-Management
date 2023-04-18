package com.example.doannmcnpm.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class MatchDetails {

    @Id
    private int id;

    @Column
    private Date dateBooking;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @Column
    private double totalMoney;
}
