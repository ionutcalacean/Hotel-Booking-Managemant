package com.example.demo_initializer.components;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clasa pentru date in persistenta logica cu tabela admin
 */
@Entity
@Table(name="Admin")
public class Admin {

    @Id
    @Column(name = "username",nullable = false)
    private String username;
    @Column(name="password")
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Empty constructor for database reasons
     */
    public Admin() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
