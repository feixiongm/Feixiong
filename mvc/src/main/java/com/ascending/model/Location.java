package com.ascending.model;

import com.ascending.jsonView.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "locations")
//@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.Location.class, View.Product.class})
    @Column(name = "id")
    private Long id;
    @JsonView({View.Location.class, View.Product.class})
    @Column(name = "name")
    private String name;
    @JsonView({View.Location.class, View.Product.class})
    @Column(name = "phone_number")
    private String phone_number;
    @JsonView({View.Location.class, View.Product.class})
    @Column(name = "email")
    private String email;
    @JsonView({View.Location.class, View.Product.class})
    @Column(name = "address")
    private String address;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "sellers_locations",
            joinColumns = {@JoinColumn(name = "seller_id")},
            inverseJoinColumns = {@JoinColumn(name = "location_id")}
    )
    private List<Seller> sellers;
    @JsonView({View.Location.class})
    @OneToMany(mappedBy = "location", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Product> products;

    public Location() {
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
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

    public List<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }
}
