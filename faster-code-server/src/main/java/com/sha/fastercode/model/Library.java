package com.sha.fastercode.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "library")
public class Library implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "no")
    private int no;

    @Column(name = "name")
    private String name;
}
