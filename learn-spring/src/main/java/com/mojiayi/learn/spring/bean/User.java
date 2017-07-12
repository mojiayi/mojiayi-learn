package com.mojiayi.learn.spring.bean;

import com.fasterxml.jackson.annotation.JsonView;
import com.mojiayi.learn.spring.view.View;

/**
 * Created by Administrator on 2017/7/11.
 */
public class User {
    @JsonView(View.Summary.class)
    private Long id;

    @JsonView(View.Summary.class)
    private String firstname;

    @JsonView(View.Summary.class)
    private String lastname;

    @JsonView(View.SummaryDetail.class)
    private String email;

    @JsonView(View.SummaryDetail.class)
    private String address;
    private String postalCode;
    private String city;
    private String country;

    public User(Long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User(Long id, String firstname, String lastname, String email, String address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
