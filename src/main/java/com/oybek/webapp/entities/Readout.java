package com.oybek.webapp.entities;

import javax.persistence.*;

@Entity
public class Readout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String date;

    @ManyToOne(cascade=CascadeType.ALL)
    private Type type;

    private Double value;

    @ManyToOne(cascade=CascadeType.ALL)
    private User userlogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public User getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(User userlogin) {
        this.userlogin = userlogin;
    }
}
