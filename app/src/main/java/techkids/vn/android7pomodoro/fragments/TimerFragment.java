package techkids.vn.android7pomodoro.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.events.TimeProgress;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment {
    Button button;
    Timer timer;
    @BindView(R.id.donutProgress)
    DonutProgress donutProgress;
    ReplaceFragmentListener r;
    public TimerFragment() {
        // Required empty public constructor
    }

    @Subscribe

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_timer3, container, false);
        setupUI(view);

        return view;
    }
    @Subscribe
    public void onSetProgress(TimeProgress mili){
        donutProgress.setProgress(mili.getMili()/1000);
        donutProgress.setText(""+mili.getMili()/1000);
        if (donutProgress.getProgress() ==100) donutProgress.setText("DONE");
        Log.d("TiME", String.format("onSetProgress:"));


    }

    private void setupUI(View view) {
        EventBus.getDefault().register(this);
        ButterKnife.bind(this,view);

    }

    public void setListener(ReplaceFragmentListener r){
        this.r = r;
    }


}
