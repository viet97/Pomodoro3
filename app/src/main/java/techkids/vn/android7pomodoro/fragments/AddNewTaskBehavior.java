package techkids.vn.android7pomodoro.fragments;

import android.util.Log;

import java.util.UUID;

import techkids.vn.android7pomodoro.databases.DbContext;
import techkids.vn.android7pomodoro.databases.models.Task;

/**
 * Created by Quoc Viet Dang on 2/16/2017.
 */

public class AddNewTaskBehavior implements OnOptionMenuBehavior {
        private String TAG= " onOption";
    @Override
    public void onClickOptionMenu(Task task1,Task task) {
        //Create a new Task
        Log.d(TAG, String.format("onClickOptionMenu:%s ", task.getLocalid()));
        Task newTask = new Task(task.getName(),task.getColor(),task.getPaymentPerHour(),task.getLocalid());
        //Add to database
        DbContext.instance.addTask(newTask);
    }
}
