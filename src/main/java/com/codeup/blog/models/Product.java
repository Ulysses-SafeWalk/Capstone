package com.codeup.blog.models;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "the_name_of_the_product", nullable = false)
    private String name;

    private double price;

    public Product() {}

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return String.format(
            "<Product id=%d name='%s', price=%.2f />",
            this.id, this.name, this.price
        );
    }
}
