package techkids.vn.android7pomodoro.databases;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;


import io.realm.Realm;
import io.realm.RealmConfiguration;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.activities.TaskActivity;
import techkids.vn.android7pomodoro.databases.models.Task;

/**
 * Created by Quoc Viet Dang on 2/8/2017.
 */

public class DbContext   {
    private Realm realm ;
    public ArrayList<Task> tasks = new ArrayList<>();
    public static DbContext instance = null;


    public DbContext(Context context) {
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }




//    public List<Task> alltask() {
//        //Fake data
//        //1 : Create array list
//
//        return tasks;
//    }
    public List<String> allcolor() {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("#C62828");
        colors.add("#AD1457");
        colors.add("#6A1B9A");
        colors.add("#BBDEFB");
        colors.add("#7C4DFF");
        colors.add("#EA80FC");
        colors.add("#33691E");
        colors.add("#9C27B0");
        return colors;
    }

    public void addTask(Task newTask) {
        alltask().add(newTask);
    }
    public void addOrUpdate(Task task){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(task);
        realm.commitTransaction();
    }
    public List<Task> alltask(){
        return realm.where(Task.class).findAll();
    }
    public void deleteAllTask(){

        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }
    public void deleteTask(Task task){
        realm.beginTransaction();
        realm.where(Task.class).equalTo("id",task.getId()).findFirst().deleteFromRealm();
        realm.commitTransaction();
    }
}
