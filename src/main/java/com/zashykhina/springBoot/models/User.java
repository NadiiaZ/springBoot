package com.zashykhina.springBoot.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name= "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "firstname")
    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;
    @Column(name = "lastname")
    @NotEmpty
    @Size(min = 2, max = 30)
    private String surname;
    @Column(name = "email")
    @NotEmpty
    @Email
    private String email;

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public User() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

}
