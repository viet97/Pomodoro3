package techkids.vn.android7pomodoro.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.adapters.ColorAdapter;

public class   ColorActivity extends AppCompatActivity {
    @BindView(R.id.rv_color)
    RecyclerView recyclerView;
    ColorAdapter colorAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        colorAdapter=new ColorAdapter();
        ButterKnife.bind(this);
        recyclerView.setAdapter(colorAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
    }

}
