package techkids.vn.android7pomodoro.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.databases.DbContext;
import techkids.vn.android7pomodoro.databases.models.Task;
import techkids.vn.android7pomodoro.networks.NetContext;
import techkids.vn.android7pomodoro.networks.jsonmodels.GetAllTaskResponeJson;
import techkids.vn.android7pomodoro.networks.jsonmodels.LoginBodyJson;
import techkids.vn.android7pomodoro.networks.jsonmodels.LoginResponseJson;
import techkids.vn.android7pomodoro.networks.jsonmodels.RegisterBodyJson;
import techkids.vn.android7pomodoro.networks.jsonmodels.RegisterResponseJson;
import techkids.vn.android7pomodoro.networks.services.GetAllTaskService;
import techkids.vn.android7pomodoro.networks.services.LoginService;
import techkids.vn.android7pomodoro.networks.services.RegisterService;
import techkids.vn.android7pomodoro.settings.LoginCredentials;
import techkids.vn.android7pomodoro.settings.SharedPrefs;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btRegister;
    private Button btLogin;
    TextInputLayout tlUsername;
    TextInputLayout tlPassword;
    private ProgressBar pb;
    Retrofit retrofit;
    private String token;
    private String username;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);
        skipLoginIfPossible();
        pb = (ProgressBar) this.findViewById(R.id.pb);
        etUsername = (EditText) this.findViewById(R.id.et_username);
        etPassword = (EditText) this.findViewById(R.id.et_password);
        btRegister = (Button) this.findViewById(R.id.bt_register);
        btLogin = (Button) this.findViewById(R.id.bt_login);
        tlUsername = ( TextInputLayout) this.findViewById(R.id.tl_username);
        tlPassword = ( TextInputLayout) this.findViewById(R.id.tl_password);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {attemptRegister();
            }
        });
        check(etUsername,tlUsername);
        check(etPassword,tlPassword);
        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    attemptLogin();
                    return false;
                }
                return false;
            }
        });

    }

    private void attemptRegister() {
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        sendRegister(username,password);

    }
    private void sendRegister(final String username, final String password){
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
                pb.setVisibility(View.INVISIBLE);
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
                pb.setVisibility(View.INVISIBLE);
                Log.d(TAG, "onFailure: 0");
                sendRegister(username,password);
            }
        });
    }


    private void sendLogin(final String username, final String password) {

        //1:create retrofit


        //2:creat service
        LoginService loginService = NetContext.instance.creat();

        // data & format
        // format => MediaType
        // data => json

        MediaType jsonMediaType = MediaType.parse("application/json");
        String loginJson = (new Gson()).toJson(new LoginBodyJson(username, password));

        RequestBody loginBody = RequestBody.create(jsonMediaType, loginJson);

        //3: create call
        Call<LoginResponseJson> loginCall = loginService.login(loginBody);

        loginCall.enqueue(new Callback<LoginResponseJson>() {;
                    @Override
                    public void onResponse(Call<LoginResponseJson> call, Response<LoginResponseJson> response) {
                        pb.setVisibility(View.INVISIBLE);
                        LoginResponseJson loginResponseJson = response.body();
                        if (loginResponseJson == null) {
                            Log.d(TAG, "onResponse: Could not parse body");
                        } else {
                            Log.d(TAG, String.format("onResponse, oh yeah: %s", loginResponseJson));
                            if (response.code() == 200) {
                               token = loginResponseJson.getAccess_token();
                                NetContext.instance.token = loginResponseJson.getAccess_token();
                                onLoginSuccess();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponseJson> call, Throwable t) {
                        Log.d(TAG, String.format("onFailure: %s", t));
                        sendLogin(username,password);
                        pb.setVisibility(View.INVISIBLE);
                    }
                });


    }

    private void skipLoginIfPossible() {
        if (SharedPrefs.getInstance().getAccessToken() != null) {
            gotoTaskActivity();
        }

    }

    private void    onLoginSuccess() {
        SharedPrefs.getInstance().put(new LoginCredentials(token, password,username));
        Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
        gotoTaskActivity();

    }

    private void onRegisterSuccess() {
        Toast.makeText(this, "Register success", Toast.LENGTH_SHORT).show();
    }

    private void attemptLogin() {
        pb.setVisibility(View.VISIBLE);
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        sendLogin(username, password);
    }

    private void gotoTaskActivity() {
        Intent intent = new Intent(this, TaskActivity.class);
      //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void check(final EditText editText, final TextInputLayout textInputLayout){
     editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
         @Override
         public void onFocusChange(View v, boolean hasFocus) {
             textInputLayout.setError(null);
            if (editText.getText().toString().equalsIgnoreCase("")) {
                if (editText.getId()==etUsername.getId()) textInputLayout.setError("Enter your username");
                else textInputLayout.setError("Enter your passowrd");
            }
             else textInputLayout.setError(null);
         }
     });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText.getText().toString().equalsIgnoreCase("")){
                    editText.setError("this can not be blank");
                }
                else editText.setError(null);
            }
        });

    }

}
