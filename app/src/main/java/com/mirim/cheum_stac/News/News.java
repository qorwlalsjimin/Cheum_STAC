package com.mirim.cheum_stac.News;

public class News {
    public int id;
    public String title;
    public String best;
    public String days;
    public int news;
    public int mimg;

    public News(){};

    public News(int id, String title, String best, String days,int news,int mimg){
        this.id = id;
        this.title = title;
        this.best = best;
        this.days = days;
        this.news = news;
        this.mimg = mimg;
    }
}
