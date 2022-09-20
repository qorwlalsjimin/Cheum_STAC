package com.mirim.cheum_stac.Product;

public class Product {
    public int id;
    public String kate;
    public int img;
    public String best;
    public String name;
    public String price;

    public Product(){};

    public Product(int id, String kate, int img, String best, String name, String price) {
        super();
        this.id = id;
        this.kate = kate;
        this.img = img;
        this.best = best;
        this.name = name;
        this.price = price;
    }
}
