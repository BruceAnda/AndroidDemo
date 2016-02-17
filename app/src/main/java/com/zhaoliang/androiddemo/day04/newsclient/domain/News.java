package com.zhaoliang.androiddemo.day04.newsclient.domain;

/**
 * Created by pro on 16/2/5.
 */
public class News {

    public String title;
    public String detail;
    public String comment;
    public String imageUrl;

    public News() {
    }

    public News(String title, String detail, String comment, String imageUrl) {
        this.title = title;
        this.detail = detail;
        this.comment = comment;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", comment='" + comment + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
