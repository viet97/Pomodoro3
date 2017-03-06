package techkids.vn.android7pomodoro.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.adapters.viewholders.TaskViewHolder;
import techkids.vn.android7pomodoro.databases.DbContext;
import techkids.vn.android7pomodoro.databases.models.Task;
import techkids.vn.android7pomodoro.events.TimerCommand;
import techkids.vn.android7pomodoro.events.TimerCommandEvent;
import techkids.vn.android7pomodoro.networks.NetContext;
import techkids.vn.android7pomodoro.networks.jsonmodels.DeleteJson;
import techkids.vn.android7pomodoro.networks.services.DeleteService;

import static android.content.ContentValues.TAG;

/**
 * Created by Quoc Viet Dang on 2/8/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    private int selection;
    private String TAG = " Task adapter";
    private  TaskLongClickListener taskLongClickListener ;
    private CheckDoneListener checkdone;
    public interface TaskItemClickListener{
        void onItemClick(Task task);
    }

    public interface TaskTimeClickListener{
        void timeTaskClick(Task task);
    }

    public interface TaskLongClickListener{
        void taskLongClick();
    }
    public interface CheckDoneListener{
        void checkListener(Task task);
    }
    public void setCheckDoneListner(CheckDoneListener checkDoneListner) {
       this.checkdone = checkDoneListner;
    }

     private TaskItemClickListener taskItemClickListener;

    private TaskTimeClickListener taskTimeClickListener;

    public void setTaskItemClickListener(TaskItemClickListener taskItemClickListener) {
        this.taskItemClickListener = taskItemClickListener;
    }
    public void setTaskTimeClickListener(TaskTimeClickListener taskTimeClickListener){
        this.taskTimeClickListener = taskTimeClickListener;
    }

    public void setTaskLongClickListener(TaskLongClickListener taskLongClickListener){
        this.taskLongClickListener = taskLongClickListener;
    }

    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
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
    public void onBindViewHolder(final TaskViewHolder holder, final int position) {
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
                TimerCommandEvent event = new TimerCommandEvent(TimerCommand.START_TIMER);
                EventBus.getDefault().post(event);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(TAG, "onLongClick: 1");
                setSelection(position);
                taskLongClickListener.taskLongClick();
                return false;

            }
        });
        holder.getvTaskColor().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    holder.getCheckColor().setVisibility(View.VISIBLE);


                checkdone.checkListener(task);
                Log.d(TAG, "onClick: zo");

            }
        });
        holder.getCheckColor().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.getCheckColor().setVisibility(View.INVISIBLE);
                checkdone.checkListener(task);
                Log.d(TAG, "onClick: 1");
            }
        });

    }

    @Override
    public int getItemCount() {
        return DbContext.instance.alltask().size();
    }
}
