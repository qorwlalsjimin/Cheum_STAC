package com.mirim.cheum_stac.Map;

public class Store{
    public int id;
    public String title;
    public String address;
    public Double lat;
    public Double lug;

    public Store(){};

    public Store(int id, String title, String address, Double lat, Double lug) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.lat = lat;
        this.lug = lug;
    }
}