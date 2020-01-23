package com.ascending.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Feixiong {
    private int id;
    private String name;
    private String description;
    private String location;
    private Logger logger = LoggerFactory.getLogger(getClass());
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
