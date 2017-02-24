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

import com.google.gson.Gson;

import java.io.IOException;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.activities.TaskActivity;
import techkids.vn.android7pomodoro.adapters.ColorAdapter;
import techkids.vn.android7pomodoro.databases.models.Task;
import techkids.vn.android7pomodoro.decorations.TaskColorSpaceDecor;
import techkids.vn.android7pomodoro.networks.NetContext;
import techkids.vn.android7pomodoro.networks.jsonmodels.AddNewTaskBodyJson;
import techkids.vn.android7pomodoro.networks.jsonmodels.GetAllTaskResponeJson;
import techkids.vn.android7pomodoro.networks.services.AddNewTaskService;
import techkids.vn.android7pomodoro.networks.services.EditTask;

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
    private Task newTask;

    public void setTitle(String title) {
        this.title = title;
    }
    private Notifydata notifydata;
    public TaskDetailFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }
    public interface Notifydata {
        void changedata();
    }

    public void setnotifydata(TaskDetailFragment.Notifydata notifydata){
        this.notifydata = notifydata;
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
            String s = etPaymentPerHour.getText().toString().replace(",",".");
             float paymentPerHour = Float.parseFloat(s);
            String color = colorAdapter.getSelectedColor();
           // Log.d(TAG, String.format("onOptionsItemSelected:%s ",paymentPerHour ));
            //Create a new Task

            String uuid = UUID.randomUUID().toString();

            newTask = new Task(taskName,color,paymentPerHour,uuid);

            //Add to database
            if (onOptionMenuBehavior.getClass() == AddNewTaskBehavior.class)
            sendNewTask();
            else sendEdit();


        }
        return false;
    }

    private void sendNewTask( ) {

        //add Header
        OkHttpClient.Builder httpclient = new OkHttpClient().newBuilder();
        httpclient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization","JWT "+ NetContext.instance.token)
                        .method(original.method(),original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpclient.build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        AddNewTaskService addNewTaskService = retrofit.create(AddNewTaskService.class);

        MediaType mediaType = MediaType.parse("application/json");
        final String json = (new Gson()).toJson(new AddNewTaskBodyJson(newTask.getName(),true,newTask.getPaymentPerHour(),null,newTask.getLocalid(),newTask.getColor()));
        RequestBody requestBody = RequestBody.create(mediaType,json);

        addNewTaskService.addTask(requestBody).enqueue(new Callback<GetAllTaskResponeJson>() {
            @Override
            public void onResponse(Call<GetAllTaskResponeJson> call, Response<GetAllTaskResponeJson> response) {
                Log.d(TAG, String.format("onResponse: &s", response.body()));


                onOptionMenuBehavior.onClickOptionMenu(task,newTask);

                getActivity().onBackPressed();

                notifydata.changedata();
            }

            @Override
            public void onFailure(Call<GetAllTaskResponeJson> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
    private void sendEdit( ){
        OkHttpClient.Builder httpclient = new OkHttpClient().newBuilder();
        httpclient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization","JWT "+ NetContext.instance.token)
                        .method(original.method(),original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpclient.build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        EditTask editTask = retrofit.create(EditTask.class);
        MediaType mediaType = MediaType.parse("application/json");
        final String json = (new Gson()).toJson(new AddNewTaskBodyJson(newTask.getName(),true,newTask.getPaymentPerHour(),null,newTask.getLocalid(),newTask.getColor()));
        RequestBody requestBody = RequestBody.create(mediaType,json);

        editTask.editTask(task.getLocalid(),requestBody).enqueue(new Callback<GetAllTaskResponeJson>() {
            @Override
            public void onResponse(Call<GetAllTaskResponeJson> call, Response<GetAllTaskResponeJson> response) {
                Log.d(TAG, String.format("onResponse: %s", response.body()));

                onOptionMenuBehavior.onClickOptionMenu(task,newTask);

                getActivity().onBackPressed();

                notifydata.changedata();
            }

            @Override
            public void onFailure(Call<GetAllTaskResponeJson> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}
