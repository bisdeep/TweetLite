package com.codepath.apps.tweetlite.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    public String name;
    public long uid;
    public String screenName;
    public String profileImageUrl;

    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.name = jsonObject.getString("name");
        user.uid = jsonObject.getLong("id");
        user.screenName = jsonObject.getString("screen_name");

        user.profileImageUrl = jsonObject.getString("profile_image_url");
        //user index name = jsonObject.getString();
        return user;
    }
}