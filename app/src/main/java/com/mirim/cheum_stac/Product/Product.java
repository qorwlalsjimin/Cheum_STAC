package com.mirim.cheum_stac.Product;

public class Product {
    public int id;
    public String kate;
    public int img;
    public boolean best;
    public String name;
    public String price;

    public Product(){};

    public Product(int id, String kate, int img, boolean best, String name, String price) {
        this.id = id;
        this.kate = kate;
        this.img = img;
        this.best = best;
        this.name = name;
        this.price = price;
    }
}
