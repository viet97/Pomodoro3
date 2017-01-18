package com.example.quocvietdang.pomodoro1.settings;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Set;

/**
 * Created by Quoc Viet Dang on 1/14/2017.
 */

public class SharedPrefs {
    private static final String SHARED_PREFS_NAME = "SP";
    private static final String LOGIN_KEY = "login";
    private static final String SETTING_KEY = "setting";
    private Gson gson =new Gson();
    private static SharedPrefs instance;

    public static SharedPrefs getInstance() {
        return instance;
    }
    public static void init(Context context){
        instance = new SharedPrefs(context);
    }

    private SharedPreferences sharedPreferences;
    public SharedPrefs(Context context){
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME,context.MODE_PRIVATE);
    }

    public void put(LoginCredentials loginCredentials){
        String LoginJSON = (new Gson()).toJson(loginCredentials);

        this.sharedPreferences.edit().putString(LOGIN_KEY,LoginJSON).commit();

    }
    public void put(SettingCredentials settingCredentials){
        String settingJSON = (new Gson()).toJson(settingCredentials);

        this.sharedPreferences.edit().putString(SETTING_KEY,settingJSON).commit();

    }
    public SettingCredentials settingCredentials(){
        String settingJson = this.sharedPreferences.getString(SETTING_KEY,null);
        if (settingJson == null) return  null;
        SettingCredentials settingCredentials = gson.fromJson(settingJson,SettingCredentials.class);

        return settingCredentials;
    }
    public LoginCredentials getLoginCredentials(){
        String loginJson = this.sharedPreferences.getString(LOGIN_KEY,null);

        LoginCredentials loginCredentials = gson.fromJson(loginJson,LoginCredentials.class);

        return loginCredentials;
    }

}
