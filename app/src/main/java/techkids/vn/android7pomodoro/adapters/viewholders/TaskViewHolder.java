package techkids.vn.android7pomodoro.adapters.viewholders;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.databases.models.Task;

/**
 * Created by Quoc Viet Dang on 2/8/2017.
 */

public class TaskViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.v_task_color)
    View vTaskColor;
    @BindView(R.id.tv_task_name)
    TextView tvTaskname;
    @BindView(R.id.time_task)
    ImageButton timeTask;
    public TaskViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

    }

    public ImageButton getTimeTask() {
        return timeTask;
    }

    public void bind(Task task){
        //1: Binde Color
        //vTaskColor.setBackgroundColor(Color.parseColor(task.getColor()));
        GradientDrawable drawable = (GradientDrawable)vTaskColor.getBackground();
        drawable.setColor(Color.parseColor(task.getColor()));
        //2:Bind Task Name
        tvTaskname.setText(task.getName());
    }
}
