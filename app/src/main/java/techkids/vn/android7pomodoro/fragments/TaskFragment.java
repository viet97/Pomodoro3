package techkids.vn.android7pomodoro.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

        ButterKnife.bind(this,view);
        taskAdapter = new TaskAdapter();
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
        r.replaceFragment(new TaskDetailFragment(),true);
        SetListener(r);
    }
    public  void SetListener(ReplaceFragmentListener r){
        this.r = r;
    }

}
