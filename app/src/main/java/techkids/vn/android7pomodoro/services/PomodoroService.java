package techkids.vn.android7pomodoro.services;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import techkids.vn.android7pomodoro.events.TimeProgress;
import techkids.vn.android7pomodoro.events.TimerCommand;
import techkids.vn.android7pomodoro.events.TimerCommandEvent;


/**
 * Created by Quoc Viet Dang on 3/4/2017.
 */

public class PomodoroService extends Service {
    private static final String TAG = "PomodoroService";
    private CountDownTimer countdown;
    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void onCommand(TimerCommandEvent event){
        if (event.getCommand() == TimerCommand.STOP_TIMER)
        Log.d(TAG, "onCommand: hura");
        startTimer();

    }





    private void startTimer(){
        countdown = new CountDownTimer(100000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("123", "onTick: ");

                EventBus.getDefault().post(new TimeProgress(millisUntilFinished));
            }

            @Override
            public void onFinish() {

            }
        };
        countdown.start();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
