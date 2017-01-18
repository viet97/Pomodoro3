package com.example.quocvietdang.pomodoro1.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quocvietdang.pomodoro1.R;
import com.example.quocvietdang.pomodoro1.settings.LoginCredentials;
import com.example.quocvietdang.pomodoro1.settings.SharedPrefs;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btRegister;
    private Button btLogin;
    private Button st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // skipLoginIfPossible();
        setContentView(R.layout.activity_login);

        etUsername = (EditText)this.findViewById(R.id.et_username);
        etPassword = (EditText)this.findViewById(R.id.et_password);
        btRegister = (Button) this.findViewById(R.id.bt_register);
        btLogin = (Button) this.findViewById(R.id.bt_login);
        st =(Button)this.findViewById((R.id.st));
        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSettingActivity();
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempLogin();
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempRegiter();
            }
        });


        SharedPreferences sharedPreferences =
                this.getSharedPreferences("settings", MODE_PRIVATE);

        //save something
        //sharedPreferences.edit().putString("username","admin").commit();
        // sharedPreferences.edit().putString("password","admin").commit();
        //sharedPreferences.edit()
        String username = sharedPreferences.getString("username",null);
        Log.d(TAG, String.format("Done Reading %s", username));

       // LoginCredendials loginCredendials = new LoginCredendials("duy","daisuc");
        //object --> string
      //  Gson gson = new Gson();
      //  String json = gson.toJson(loginCredendials);
      //  Log.d(TAG, String.format("json: %s", json));

        //string --> object
       // LoginCredendials loginCredendials1 = gson.fromJson(json,LoginCredendials.class);
       // Log.d(TAG, String.format("onCreat:object:%s", LoginCredendials.class.toString()));
        SharedPrefs sharedPrefs = new SharedPrefs(this);
        sharedPrefs.put(new LoginCredentials("hieu","ccc"));
        Log.d(TAG, String.format("OnCreate:%s",
                sharedPrefs.getLoginCredentials().toString() ));
    }

    private void gotoSettingActivity() {
        Intent intent = new Intent(this,SettingActivity.class);
        startActivity(intent);
    }

    private void skipLoginIfPossible() {
       // if (SharedPrefs.getInstance().getLoginCredentials() != null) gotoTaskActivity();
    }

    private void attempRegiter() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (check(username)&& check(password) ) {
            // Notifications
            Toast.makeText(this, "Register success", Toast.LENGTH_SHORT).show();
        }else Toast.makeText(this,"Register failed",Toast.LENGTH_SHORT).show();
    }

    private void attempLogin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.equals( "admin")&& password.equals("admin") ) {
            // Notifications
            SharedPrefs.getInstance().put(new LoginCredentials(username,password));
            Toast.makeText(this, "login succes", Toast.LENGTH_SHORT).show();
            gotoTaskActivity();
        }else Toast.makeText(this,"login failed",Toast.LENGTH_SHORT).show();
    }
    private boolean check(String s){
        int i;
        for( i=0;i<s.length();i++){
            if (!Character.isLetter(s.charAt(i)) || s.equals("")){
                return false;
            }
        }
        return true;
    }
    private void gotoTaskActivity(){
        Intent intent = new Intent(this,TaskActivity.class);
        startActivity(intent);
    }
}
