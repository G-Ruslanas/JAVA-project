package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name="BUILDINGS")
public class Building {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="ADDRESS")
    private String address;
    @Column(name="OWNER")
    private String owner;
    @Column(name="SIZE")
    private Double size;
    @Column(name="VALUE")
    private Double value;
    @Column(name="TYPE")
    private String type;
    @Column(name="TAXES")
    private Double tax=0.0;

    public Building(){
    }
    public Building(Long id, String address, String owner, Double size, Double value, String type, Double tax) {
        this.id = id;
        this.address = address;
        this.owner = owner;
        this.size = size;
        this.value = value;
        this.type = type;
        this.tax = tax;
    }

    public Double getTax() { return tax; }

    public void setTax(Double tax) { this.tax = tax; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
