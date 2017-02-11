package techkids.vn.android7pomodoro.databases;

import java.util.ArrayList;
import java.util.List;


import techkids.vn.android7pomodoro.databases.models.Task;

/**
 * Created by Quoc Viet Dang on 2/8/2017.
 */

public class DbContext {
    public final  static  DbContext instance = new DbContext();
    public List<Task> alltask() {
        //Fake data
        //1 : Create array list
        ArrayList<Task> tasks = new ArrayList<>();


        //2: add some task and return
            tasks.add(new Task("Study RecycleView","#C62828"));
            tasks.add(new Task("Study RecycleView1 ","#AD1457"));
            tasks.add(new Task("Study RecycleView2","#6A1B9A"));
            tasks.add(new Task("Study RecycleView3","#BBDEFB"));
            tasks.add(new Task("Study RecycleView4","#7C4DFF"));
        return tasks;
    }
    public List<String> allcolor() {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("#EF5350");
        colors.add("#64B5F6 ");
        colors.add("#82B1FF");
        colors.add("#FF9800");
        colors.add("#3949AB");
        colors.add("#EA80FC");
        colors.add("#33691E");
        colors.add("#FFFF00");
        return colors;
    }
}
