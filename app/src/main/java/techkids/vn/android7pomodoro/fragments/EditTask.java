package techkids.vn.android7pomodoro.fragments;

import techkids.vn.android7pomodoro.databases.DbContext;
import techkids.vn.android7pomodoro.databases.models.Task;

/**
 * Created by Quoc Viet Dang on 2/16/2017.
 */

public class EditTask implements OnOptionMenuBehavior {
    @Override
    public void onClickOptionMenu(Task task1, Task task) {
        for (int i=0; i< DbContext.instance.alltask().size();i++){
            if (DbContext.instance.alltask().get(i)==task1 ){
                DbContext.instance.alltask().get(i).setColor(task.getColor());
                DbContext.instance.alltask().get(i).setName(task.getName());
                DbContext.instance.alltask().get(i).setPaymentPerHour(task.getPaymentPerHour());

            }
        }
    }
}
