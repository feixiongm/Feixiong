package com.ascending.model;

import com.ascending.jsonView.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
//product
public class Product implements Serializable {

    @Id
    @JsonView({View.Location.class, View.Product.class, View.Seller.class})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @JsonView({View.Location.class, View.Product.class, View.Seller.class})
    @Column(name = "product_name")
    private String name;
    @JsonView({View.Location.class, View.Product.class, View.Seller.class})
    @Column(name = "description")
    private String description;
    @JsonView({View.Location.class, View.Product.class, View.Seller.class})
    @Column(name = "price")
    private double price;
    @JsonView({View.Location.class, View.Product.class, View.Seller.class})
    @Column(name = "weight")
    private double weight;
    @JsonView({View.Location.class, View.Product.class, View.Seller.class})
    @Column(name = "year")
    private String year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    @JsonView({View.Product.class})
    private Location location;

    public Product() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
