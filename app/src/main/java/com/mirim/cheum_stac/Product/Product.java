package com.mirim.cheum_stac.Product;

public class Product {
    public int id;
    public int simg;
    public String kate;
    public String best;
    public String name;
    public String price;
    public String cont;
    public String hash;

    public Product(){};

    public Product(int id,int simg, String kate, String best, String name, String price, String cont, String hash) {
        super();
        this.id = id;
        this.simg = simg;
        this.kate = kate;
        this.best = best;
        this.name = name;
        this.price = price;
        this.cont = cont;
        this.hash = hash;
    }

}
