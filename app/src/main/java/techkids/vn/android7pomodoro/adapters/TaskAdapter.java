package techkids.vn.android7pomodoro.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.adapters.viewholders.TaskViewHolder;
import techkids.vn.android7pomodoro.databases.DbContext;
import techkids.vn.android7pomodoro.databases.models.Task;

import static android.content.ContentValues.TAG;

/**
 * Created by Quoc Viet Dang on 2/8/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    public interface TaskItemClickListener{
        void onItemClick(Task task);
    }
    public interface TaskTimeClickListener{
        void timeTaskClick(Task task);
    }
     private TaskItemClickListener taskItemClickListener;
    private TaskTimeClickListener taskTimeClickListener;

    public void setTaskItemClickListener(TaskItemClickListener taskItemClickListener) {
        this.taskItemClickListener = taskItemClickListener;
    }
    public void setTaskTimeClickListener(TaskTimeClickListener taskTimeClickListener){
        this.taskTimeClickListener = taskTimeClickListener;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //1: Create View

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView =  layoutInflater.inflate(R.layout.item_task,parent,false);

        //2: Create ViewHolder
        TaskViewHolder taskViewHolder = new TaskViewHolder(itemView);

        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(final TaskViewHolder holder, int position) {
        //1: Get data based on position
        final Task task = DbContext.instance.alltask().get(position);
        //2: Bind data into view
        holder.bind(task);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Log.d(TAG, "onClick: ");
                    if (taskItemClickListener != null) {
                        taskItemClickListener.onItemClick(task);

                }
            }
        });
        holder.getTimeTask().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskTimeClickListener.timeTaskClick(task);
            }
        });

    }

    @Override
    public int getItemCount() {
        return DbContext.instance.alltask().size();
    }
}
