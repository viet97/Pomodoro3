package techkids.vn.android7pomodoro;

import android.app.Application;
import android.content.AsyncTaskLoader;
import android.util.Log;

import techkids.vn.android7pomodoro.databases.DbContext;
import techkids.vn.android7pomodoro.databases.models.Task;
import techkids.vn.android7pomodoro.settings.SharedPrefs;

/**
 * Created by apple on 1/14/17.
 */

public class PomodoroApplication extends Application {

    private static final String TAG = "PomodoroApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        SharedPrefs.init(this);
        for (Task task : DbContext.instance.alltask()){
            Log.d(TAG, String.format("onCreate: %s", task));
        }
    }
}
