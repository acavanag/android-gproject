package com.example.andrewcavanagh.acproject;

import com.orm.SugarRecord;

/**
 * Created by andrewcavanagh on 3/9/15.
 */
public class Post extends SugarRecord<Post> {
    String address;

    public Post() {}

    public Post(String address) {
        this.address = address;
    }
}
