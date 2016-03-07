package com.example.viktor.myneighbourhood;

import java.util.ArrayList;

/**
 * Created by Viktor on 3/1/2016.
 * A class to store all the post in the app
 */
public class PostStorage {

    private static PostStorage  postStorage = new PostStorage();
    private static ArrayList<Post> posts= new ArrayList<Post>();


    //prevent any other class from instantiating
    private PostStorage(){}

    public static PostStorage getInstance(){
        return postStorage;
    }

    protected static  void addPost( Post item){
        posts.add(item);
    }

    public ArrayList<Post>  getPosts(){
        return posts;
    }
}
