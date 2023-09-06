package com.example.accessingdatamysql;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class FeedingSpot implements Serializable {

    @Id
    public String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name='" + name;

    }
}
