package com.example.quocvietdang.pomodoro1.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quocvietdang.pomodoro1.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btRegister;
    private Button btLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText)this.findViewById(R.id.et_username);
        etPassword = (EditText)this.findViewById(R.id.et_password);
        btRegister = (Button) this.findViewById(R.id.bt_register);
        btLogin = (Button) this.findViewById(R.id.bt_login);
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
            Toast.makeText(this, "login succes", Toast.LENGTH_SHORT).show();
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
}
