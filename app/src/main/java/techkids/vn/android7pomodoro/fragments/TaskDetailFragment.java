package techkids.vn.android7pomodoro.fragments;


import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.adapters.ColorAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailFragment extends Fragment {
    @BindView(R.id.d_payment)
    EditText editText;
    @BindView(R.id.rv_color1)
    RecyclerView recyclerView;
    ColorAdapter colorAdapter ;


    public TaskDetailFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
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
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Create a new task");
        colorAdapter=new ColorAdapter();
        recyclerView.setAdapter(colorAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),4));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit_task,menu);



    }
}
