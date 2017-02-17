package techkids.vn.android7pomodoro.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.activities.TaskActivity;
import techkids.vn.android7pomodoro.adapters.ColorAdapter;
import techkids.vn.android7pomodoro.databases.DbContext;
import techkids.vn.android7pomodoro.databases.models.Task;
import techkids.vn.android7pomodoro.decorations.TaskColorSpaceDecor;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailFragment extends Fragment {
    @BindView(R.id.rv_color1)
    RecyclerView recyclerView;
    ColorAdapter colorAdapter ;
    @BindView(R.id.d_name)
    EditText etTaskName;
   private OnOptionMenuBehavior onOptionMenuBehavior;
    @BindView(R.id.d_payment)
    EditText etPaymentPerHour;
    private String title;
    private Task task;
    public void setTitle(String title) {
        this.title = title;
    }

    public TaskDetailFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    public void setTask(Task task) {
        this.task = task;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_detail, container, false);
        setupUI(view);

        return view;

    }

    private void setupUI(View view) {
        ButterKnife.bind(this,view);
        colorAdapter=new ColorAdapter();
        recyclerView.setAdapter(colorAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),4));
        recyclerView.addItemDecoration(new TaskColorSpaceDecor());
        if (getActivity() instanceof TaskActivity) {
            ((TaskActivity) getActivity()).getSupportActionBar().setTitle(title);
        }
        if (task != null){
            etTaskName.setText(task.getName());
            etPaymentPerHour.setText(String.format("%.1f",task.getPaymentPerHour()));
            colorAdapter.setColor(task.getColor());
        }


    }
    public void setOnOptionMenuBehavior(OnOptionMenuBehavior onOptionMenuBehavior){
        this.onOptionMenuBehavior = onOptionMenuBehavior;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit_task,menu);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_item){
            //Get Data from UI
            String taskName = etTaskName.getText().toString();
             float paymentPerHour = Float.parseFloat(etPaymentPerHour.getText().toString());
            String color = colorAdapter.getSelectedColor();
           // Log.d(TAG, String.format("onOptionsItemSelected:%s ",paymentPerHour ));
            //Create a new Task
            Task newTask = new Task(taskName,color,paymentPerHour);
            //Add to database
            onOptionMenuBehavior.onClickOptionMenu(task,newTask);
            getActivity().onBackPressed();
        }
        return false;
    }
}
