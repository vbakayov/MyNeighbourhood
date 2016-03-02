package com.example.viktor.myneighbourhood;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Viktor on 3/1/2016.
 */
public class Profile implements Serializable {

    private String name;
    private String message;
    private int rating;
    private ArrayList<Post> posts;
    private ArrayList<Post> historyPosts;

    private static Profile profile  = new Profile();

    // Constructor
    public Profile(){
        this("Viktor");
    }

    // constructor
    public Profile(String name){
        this.name = name;
    }

    public static Profile getInstance(){
        return profile;
    }


    // GETTERS AND SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Post> getHistoryPosts() {
        return historyPosts;
    }

    public void setHistoryPosts(ArrayList<Post> historyPosts) {
        this.historyPosts = historyPosts;
    }

    public String toString(){
        return this.name;
    }
}
