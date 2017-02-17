package techkids.vn.android7pomodoro.fragments;

import android.support.v4.app.Fragment;

import techkids.vn.android7pomodoro.databases.models.Task;

/**
 * Created by Quoc Viet Dang on 2/16/2017.
 */

public interface OnOptionMenuBehavior {
    public void onClickOptionMenu(Task task1, Task task);
}
