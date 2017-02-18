package techkids.vn.android7pomodoro.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.vn.android7pomodoro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment {
    Timer timer;
    ReplaceFragmentListener r;
    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_timer3, container, false);
        setupUI(view);

        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this,view);
    }

    public void setListener(ReplaceFragmentListener r){
        this.r = r;
    }


}
