package techkids.vn.android7pomodoro.activities;

import android.content.Context;
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

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.networks.jsonmodels.LoginBodyJson;
import techkids.vn.android7pomodoro.networks.jsonmodels.LoginResponseJson;
import techkids.vn.android7pomodoro.networks.jsonmodels.RegisterBodyJson;
import techkids.vn.android7pomodoro.networks.jsonmodels.RegisterResponseJson;
import techkids.vn.android7pomodoro.networks.services.LoginService;
import techkids.vn.android7pomodoro.networks.services.RegisterService;
import techkids.vn.android7pomodoro.settings.LoginCredentials;
import techkids.vn.android7pomodoro.settings.SharedPrefs;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText etUsername;
    private EditText etPassword;
    private Button btRegister;
    private Button btLogin;

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //skipLoginIfPossible();

        setContentView(R.layout.activity_login);

        etUsername = (EditText) this.findViewById(R.id.et_username);
        etPassword = (EditText) this.findViewById(R.id.et_password);
        btRegister = (Button) this.findViewById(R.id.bt_register);
        btLogin = (Button) this.findViewById(R.id.bt_login);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();
            }
        });

    }

    private String username;
    private String password;

    private void attemptRegister() {

        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        sendRegister(username,password);

    }
    private void sendRegister(String username, String password){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterService registerService = retrofit.create(RegisterService.class);
        MediaType mediaType = MediaType.parse("application/json");
        final String json = (new Gson()).toJson(new RegisterBodyJson(username,password));
        RequestBody requestBody = RequestBody.create(mediaType,json);
        registerService.register(requestBody).enqueue(new Callback<RegisterResponseJson>() {
            @Override
            public void onResponse(Call<RegisterResponseJson> call, Response<RegisterResponseJson> response) {
              RegisterResponseJson registerResponseJson= response.body();
                Log.d(TAG, "onResponse: 1");
                if (registerResponseJson == null){
                    Log.d(TAG, "onResponse:null ");

                }
                else {
                    Log.d(TAG, "onResponse: ok");
                    if (response.code() == 200) {
                        Log.d(TAG, "onResponse: ohyeah");
                        onRegisterSuccess();
                    }
                }

            }

            @Override
            public void onFailure(Call<RegisterResponseJson> call, Throwable t) {
                Log.d(TAG, "onFailure: 0");
            }
        });
    }

    private void sendLogin(String username, String password) {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginService loginService = retrofit.create(LoginService.class);

        // data & format
        // format => MediaType
        // data => json
        MediaType jsonMediaType = MediaType.parse("application/json");
        String loginJson = (new Gson()).toJson(new LoginBodyJson(username, password));

        RequestBody loginBody = RequestBody.create(jsonMediaType, loginJson);

        loginService
                .login(loginBody)
                .enqueue(new Callback<LoginResponseJson>() {
                    @Override
                    public void onResponse(Call<LoginResponseJson> call, Response<LoginResponseJson> response) {
                        LoginResponseJson loginResponseJson = response.body();
                        if (loginResponseJson == null) {
                            Log.d(TAG, "onResponse: Could not parse body");
                        } else {
                            Log.d(TAG, String.format("onResponse, oh yeah: %s", loginResponseJson));
                            if (response.code() == 200) {
                                onLoginSuccess();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponseJson> call, Throwable t) {
                        Log.d(TAG, String.format("onFailure: %s", t));
                    }
                });
    }

    private void skipLoginIfPossible() {
        if (SharedPrefs.getInstance().getLoginCredendials() != null) {
            gotoTaskActivity();
        }
    }

    private void onLoginSuccess() {
        SharedPrefs.getInstance().put(new LoginCredentials(username, password));
        Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
        gotoTaskActivity();
    }
    private void onRegisterSuccess() {
        Toast.makeText(this, "Register success", Toast.LENGTH_SHORT).show();
    }

    private void attemptLogin() {
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        sendLogin(username, password);
    }

    private void gotoTaskActivity() {
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }

}
