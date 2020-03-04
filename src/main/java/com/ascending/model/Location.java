package com.ascending.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "locations")
//@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "seller_id")
    private Long seller_id;

//    @JsonIgnore
    @OneToMany(mappedBy = "location", cascade= CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Product> products;

    public Location() {
    }

    public Location(long id, String name, String phone_number, String email, String address, Long seller_id){
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.seller_id = seller_id;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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

    public Long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Long seller_id) {
        this.seller_id = seller_id;
    }
}
