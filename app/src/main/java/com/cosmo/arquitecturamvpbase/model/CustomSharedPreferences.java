package com.cosmo.arquitecturamvpbase.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;

import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.google.gson.Gson;

/**
 * Created by leidyzulu on 17/10/17.
 */

public class CustomSharedPreferences {


    private SharedPreferences sharedPreferences;


    public CustomSharedPreferences(Context context) {

        this.sharedPreferences = context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);

    }


    public void saveObjectUser(String key, User user){

        Gson gson = new Gson();
        String json =  gson.toJson(user);
        addValue(key, json);
    }

    private void addValue(String key, String json) {
        sharedPreferences.edit().putString(key, json).commit();
    }


    public User getObjectUser(String key){

        Gson gson = new Gson();
        String json =  sharedPreferences.getString(key,"");
        User user = gson.fromJson(json, User.class);
        return user;
    }


    public  void deleteValue(String key){
        sharedPreferences.edit().remove(key).commit();
    }
}
