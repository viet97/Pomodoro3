package techkids.vn.android7pomodoro.fragments;

import techkids.vn.android7pomodoro.databases.DbContext;
import techkids.vn.android7pomodoro.databases.models.Task;

/**
 * Created by Quoc Viet Dang on 2/16/2017.
 */

public class AddNewTaskBehavior implements OnOptionMenuBehavior {

    @Override
    public void onClickOptionMenu(Task task1,Task task) {
        //Create a new Task
        Task newTask = new Task(task.getName(),task.getColor(),task.getPaymentPerHour());
        //Add to database
        DbContext.instance.addTask(newTask);
    }
}
