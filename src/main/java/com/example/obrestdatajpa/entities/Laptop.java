package com.example.obrestdatajpa.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name="laptops")
public class Laptop {
    @Id
    //notacion para auto asignar y generar las llaves primarias cuando se agregue un nuevo objeto
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String model;
    private String brand;
    private Integer battery;
    private Double price;
    private Boolean available;

    public Laptop() {
    }

    public Laptop(Integer id, String model, String brand, Integer battery, Double price, Boolean available) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.battery = battery;
        this.price = price;
        this.available = available;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", battery=" + battery +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
}
