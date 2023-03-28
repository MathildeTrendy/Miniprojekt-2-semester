package com.example.MiniProject.Model;

public class Items {
    private String name;
    private int quantity;
    private String description;
    private double price;
    private String URL;

    public Items(String name, int quantity, String description, double price, String URL) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.URL = URL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
