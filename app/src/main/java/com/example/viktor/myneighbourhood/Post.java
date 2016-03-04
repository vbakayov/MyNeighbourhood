package com.example.viktor.myneighbourhood;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Viktor on 3/1/2016.
 */
public class Post {
    private String title;
    private String description;
    private String pictureSource1;
    private String pictureSource2;
    private String Owner;
    private Boolean posttoMyHood;
    private Boolean postToCurrentHood;
    private String homeAddress;
    private String picturePath;
    private String picturePath2;
    private String currentAdress;

    public Post(String title, String description, String pictureSource1, String pictureSource2, String owner, Boolean posttoMyHood,
                Boolean postToCurrentHood, String homeAddress, String currentAdress, String picturePath, String picturePath2) {
        this.title = title;
        this.description = description;
        this.pictureSource1 = pictureSource1;
        this.pictureSource2 = pictureSource2;
        this. Owner = owner;
        this.posttoMyHood = posttoMyHood;
        this.postToCurrentHood = postToCurrentHood;
        this.homeAddress = homeAddress;
        this.currentAdress = currentAdress;
        this.picturePath = picturePath;
        this.picturePath2 = picturePath2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrentAdress() {
        return currentAdress;
    }

    public String getHomeAddress() {
        return homeAddress;
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

    public Boolean getPosttoMyHood() {
        return posttoMyHood;
    }

    public Boolean getPostToCurrentHood() {
        return postToCurrentHood;
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


    public String getPicturePath() {
        return picturePath;
    }


    public String getPicturePath2() {
        return picturePath2;
    }


    public void setPictureSource1(String pictureSource1) {
        this.pictureSource1 = pictureSource1;
    }

}
