package com.application.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "titleindex")
public class title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "title")
    String title;

    @Column(name = "addr")
    String addr;

}
