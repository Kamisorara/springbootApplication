package com.application.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data

@Entity
@Table(name = "account")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;
}
