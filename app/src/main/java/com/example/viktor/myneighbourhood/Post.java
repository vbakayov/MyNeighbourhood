package com.example.viktor.myneighbourhood;

/**
 * Created by Viktor on 3/1/2016.
 */
public class Post {
    private String title;
    private String description;
    private String pictureSource1;
    private String pictureSource2;
    private String Owner;

    public Post(String title, String description, String pictureSource1, String pictureSource2, String owner) {
        this.title = title;
        this.description = description;
        this.pictureSource1 = pictureSource1;
        this.pictureSource2 = pictureSource2;
        Owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getPictureSource2() {
        return pictureSource2;
    }

    public void setPictureSource2(String pictureSource2) {
        this.pictureSource2 = pictureSource2;
    }

    public String getPictureSource1() {
        return pictureSource1;
    }

    public void setPictureSource1(String pictureSource1) {
        this.pictureSource1 = pictureSource1;
    }

}
