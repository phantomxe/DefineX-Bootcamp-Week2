package com.phantomxe;

public class Product {
    private String name;
    private Double price;
    private Integer quantity;
    private ProductCategory category;
    private Seller seller;

    public Product(Seller seller, String name, Double price, Integer quantity, ProductCategory category) {
        this.seller = seller;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return category + " | " + name + " " + quantity + "pcs x " + price + "TL Total: " + price*quantity + "TL";
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    } 
}
