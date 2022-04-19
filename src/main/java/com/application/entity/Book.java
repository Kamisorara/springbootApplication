package com.application.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "autuor")
    String author;

    @Column(name = "publish")
    String publish;

    @Column(name = "pages")
    int pages;

    @Column(name = "price")
    double price;

}
