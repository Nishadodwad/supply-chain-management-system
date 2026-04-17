package com.supplychain.management.dto;

public class ExternalProduct {

    private String title;
    private String description;
    private double price;
    private int stock;
    private String thumbnail;

    // getters & setters

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getThumbnail() { return thumbnail; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }
}