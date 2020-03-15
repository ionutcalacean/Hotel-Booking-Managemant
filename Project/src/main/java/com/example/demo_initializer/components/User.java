package com.example.demo_initializer.components;

import javax.persistence.*;

@Entity
@Table(name="User")
public class User {

    @Id
    @Column(nullable=false)
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="phone")
    private String phone;
    @Column(name="email")
    private String email;
    @Column(name="city")
    private String city;

    public User(String username, String password, String phone, String email, String city) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.city = city;
    }

    public User() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
