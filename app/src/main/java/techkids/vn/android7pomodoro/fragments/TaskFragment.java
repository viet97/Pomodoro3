package techkids.vn.android7pomodoro.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.activities.TaskActivity;
import techkids.vn.android7pomodoro.adapters.TaskAdapter;
import techkids.vn.android7pomodoro.databases.models.Task;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment {
    ReplaceFragmentListener r;
    private TaskAdapter taskAdapter;
    @BindView(R.id.rv_task)
    RecyclerView rvTask;
    public TaskFragment() {
        // Required empty public constructor
    }
    TaskDetailFragment taskDetailFragment;
    TimerFragment timerFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        setupUI(view);
        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    private void setupUI(View view) {
        taskDetailFragment = new TaskDetailFragment();
        timerFragment = new TimerFragment();
        ButterKnife.bind(this,view);
        taskAdapter = new TaskAdapter();
        taskAdapter.setTaskItemClickListener(new TaskAdapter.TaskItemClickListener() {
            @Override
            public void onItemClick(Task task) {
                taskDetailFragment.setOnOptionMenuBehavior(new EditTask());
                taskAdapter = new TaskAdapter();
                taskDetailFragment.setTitle("Edit Task");
                r.replaceFragment(taskDetailFragment,true);
                SetListener(r);
                Log.d(TAG, String.format("onItemClick: %s", task));
                taskDetailFragment.setTask(task);
            }
        });
        taskAdapter.setTaskTimeClickListener(new TaskAdapter.TaskTimeClickListener() {
            @Override
            public void timeTaskClick(Task task) {

                r.replaceFragment(timerFragment,true);
                SetListener(r);
            }
        });

        rvTask.setAdapter(taskAdapter);
        rvTask.setLayoutManager(new LinearLayoutManager(this.getContext()));
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Tasks");
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(),DividerItemDecoration.VERTICAL);
        rvTask.addItemDecoration(dividerItemDecoration);
        setHasOptionsMenu(true);
    }
    @OnClick(R.id.fab)
    void onclick(){
        //TODO: MAKE TASKACTIVITY AND FRAGMENT INDEPENDENT
        r.replaceFragment(taskDetailFragment,true);
        taskDetailFragment.setTitle("Add a new Tasak");
        taskDetailFragment.setOnOptionMenuBehavior(new AddNewTaskBehavior());

        SetListener(r);
    }
    public  void SetListener(ReplaceFragmentListener r){
        this.r = r;
    }

}
