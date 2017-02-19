package com.pinnacle.garorasu.endlessscrolling.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by garorasu on 18/2/17.
 */
// POJO
public class Post {
    private String userId;
    private String id;
    private String title;
    private String body;

    public Post(String userId,String id,String title,String body){
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    //getters
    public String getTitle() {
        return this.title;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getId() {
        return this.id;
    }

    public String getBody() {
        return this.body;
    }


    public static Post jsonToPost(JSONObject jsonObject){

        try {
            String userId = jsonObject.getString("userId");
            String id = jsonObject.getString("id");
            String title = jsonObject.getString("title");
            String body = jsonObject.getString("body");
            return new Post(userId,id,title,body);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

