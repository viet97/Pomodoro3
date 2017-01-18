package com.example.quocvietdang.pomodoro1;

import android.app.Application;
import android.util.Log;

import com.example.quocvietdang.pomodoro1.settings.SharedPrefs;

import static android.content.ContentValues.TAG;

/**
 * Created by Quoc Viet Dang on 1/14/2017.
 */

public class PomodoroApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate: ");
        SharedPrefs.getInstance().init(this);
    }
}
