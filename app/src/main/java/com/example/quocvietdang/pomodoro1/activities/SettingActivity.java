package com.example.quocvietdang.pomodoro1.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.example.quocvietdang.pomodoro1.R;
import com.example.quocvietdang.pomodoro1.settings.SettingCredentials;
import com.example.quocvietdang.pomodoro1.settings.SharedPrefs;

public class SettingActivity extends AppCompatActivity {
    private static final String TAG = "123";
    private SeekBar sb1;
    private SeekBar sb2;
    private SeekBar sb3;
    private Spinner spbreak;
    private Button df;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sb1 = (SeekBar)this.findViewById(R.id.sb1);
        sb2 = (SeekBar)this.findViewById(R.id.sb2);
        sb3 = (SeekBar)this.findViewById(R.id.sb3);
        spbreak = (Spinner)this.findViewById(R.id.sp);
        df = (Button)this.findViewById(R.id.df);
        final SharedPrefs sharedPrefs = new SharedPrefs(this);
        addListener(sharedPrefs);
        setUI();
    }

    private void addListener(final SharedPrefs sharedPrefs){
        df.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb1.setProgress(0);
                sb2.setProgress(0);
                sb3.setProgress(0);
                sharedPrefs.put(new SettingCredentials(0,0,0));
            }
        });
        sb1.setProgress(sharedPrefs.settingCredentials().getWorktime());
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
              sharedPrefs.put(new SettingCredentials(sb1.getProgress(),sb2.getProgress(),sb3.getProgress()));
            }
        });
        sb2.setProgress(sharedPrefs.settingCredentials().getBreaktime());
        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
              sharedPrefs.put(new SettingCredentials(sb1.getProgress(),sb2.getProgress(),sb3.getProgress()));
            }
        });
        sb3.setProgress(sharedPrefs.settingCredentials().getLongbreaktime());
        sb3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
              sharedPrefs.put(new SettingCredentials(sb1.getProgress(),sb2.getProgress(),sb3.getProgress()));
            }
        });
    }
    private void setUI(){
        String[] sp = new String[]{
                "1",
                "2",
                "3"
        };
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                sp
        );
        spbreak.setAdapter(arrayAdapter);
    }
}
