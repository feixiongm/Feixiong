package com.ascending.model;

import com.ascending.jsonView.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sellers")
public class Seller implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(View.Seller.class)
    private Long id;

    @Column(name = "name")
    @JsonView(View.Seller.class)
    private String name;

    @JsonView(View.Seller.class)
    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    @JsonView(View.Seller.class)

    private String phone_number;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;
//@JsonIgnore
    @ManyToMany(mappedBy = "sellers")
    private List<Location> locations;

    public Seller() {
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
