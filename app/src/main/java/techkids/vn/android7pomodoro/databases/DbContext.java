package techkids.vn.android7pomodoro.databases;

import java.util.ArrayList;
import java.util.List;


import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.databases.models.Task;

/**
 * Created by Quoc Viet Dang on 2/8/2017.
 */

public class DbContext {
    public ArrayList<Task> tasks = new ArrayList<>();
    public final  static  DbContext instance = new DbContext();
    public List<Task> alltask() {
        //Fake data
        //1 : Create array list

        return tasks;
    }
    public List<String> allcolor() {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("#C62828");
        colors.add("#AD1457");
        colors.add("#6A1B9A");
        colors.add("#BBDEFB");
        colors.add("#7C4DFF");
        colors.add("#EA80FC");
        colors.add("#33691E");
        colors.add("#FFFF00");
        return colors;
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }
}
