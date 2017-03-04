package techkids.vn.android7pomodoro.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
import techkids.vn.android7pomodoro.adapters.TaskAdapter;
import techkids.vn.android7pomodoro.databases.DbContext;
import techkids.vn.android7pomodoro.databases.models.Task;
import techkids.vn.android7pomodoro.networks.NetContext;
import techkids.vn.android7pomodoro.networks.jsonmodels.AddNewTaskBodyJson;
import techkids.vn.android7pomodoro.networks.jsonmodels.DeleteJson;
import techkids.vn.android7pomodoro.networks.jsonmodels.GetAllTaskResponeJson;
import techkids.vn.android7pomodoro.networks.services.DeleteService;
import techkids.vn.android7pomodoro.networks.services.EditTask;
import techkids.vn.android7pomodoro.networks.services.GetAllTaskService;
import techkids.vn.android7pomodoro.settings.SharedPrefs;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment {
    String token;
    private TaskDetailFragment.Notifydata notifydata1;
    ReplaceFragmentListener r;
    public TaskAdapter taskAdapter;
    @BindView(R.id.rv_task)
    RecyclerView rvTask;
    @BindView(R.id.taskfragment)
    View layout;
    @BindView(R.id.pb_taskfragment)
    ProgressBar progressBar;
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
        //Token

        taskAdapter = new TaskAdapter();
        taskAdapter.setTaskItemClickListener(new TaskAdapter.TaskItemClickListener() {
            @Override
            public void onItemClick(Task task) {
                taskDetailFragment.setOnOptionMenuBehavior(new EditTaskBehavior());
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
        //add Header
        getAllTask();
        //OnFailed
        taskDetailFragment.setOnFailedListener(new TaskDetailFragment.OnFailedListener() {
            @Override
            public void onFailed() {
                Toast.makeText(getActivity(),"failed to connect internet",Toast.LENGTH_SHORT).show();
            }
        });
        //checkDone
      taskAdapter.setCheckDoneListner(new TaskAdapter.CheckDoneListener() {
          @Override
          public void checkListener(Task task) {
              Task newTask = null;
//              if (task.getDone() == false)
              newTask = new Task(task.getId(),task.getName(),task.getColor(),task.getPaymentPerHour(),task.getLocalid(),!task.getDone());

             // taskDetailFragment.sendEdit();

                  OkHttpClient.Builder httpclient = new OkHttpClient().newBuilder();
                  httpclient.addInterceptor(new Interceptor() {
                      @Override
                      public okhttp3.Response intercept(Chain chain) throws IOException {
                          Request original = chain.request();

                          Request request = original.newBuilder()
                                  .header("Authorization","JWT "+ SharedPrefs.getInstance().getAccessToken())
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
                  final String json = (new Gson()).toJson(new AddNewTaskBodyJson(newTask.getName(),newTask.getDone(),newTask.getPaymentPerHour(),null,newTask.getLocalid(),newTask.getColor(),newTask.getId()));

                  RequestBody requestBody = RequestBody.create(mediaType,json);

              final Task finalNewTask = newTask;
              editTask.editTask(task.getLocalid(),requestBody).enqueue(new Callback<GetAllTaskResponeJson>() {
                      @Override
                      public void onResponse(Call<GetAllTaskResponeJson> call, Response<GetAllTaskResponeJson> response) {
                          Log.d(TAG, String.format("onResponse: %s", response.body()));

                          DbContext.instance.addOrUpdate(finalNewTask);
                      }

                      @Override
                      public void onFailure(Call<GetAllTaskResponeJson> call, Throwable t) {
                          Log.d(TAG, String.format("onFailure: %s", t));
                          Toast.makeText(getActivity(),"failed to connect internet",Toast.LENGTH_SHORT).show();

                      }
                  });
          }
      });
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setCancelable(true);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure ?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       sendDelete();

                    }
                });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        final AlertDialog dialog = builder.create();



        taskAdapter.setTaskLongClickListener(new TaskAdapter.TaskLongClickListener() {
            @Override
            public void taskLongClick() {
//                Log.d(TAG, "taskLongClick: %d", DbContext.instance.alltask().size());
                dialog.show();
            }
        });
        taskDetailFragment.setnotifydata(new TaskDetailFragment.Notifydata() {
            @Override
            public void changedata() {

                taskAdapter.notifyDataSetChanged();
            }
        });
    }

    private void sendDelete() {
        OkHttpClient.Builder httpclient = new OkHttpClient().newBuilder();
        httpclient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization","JWT "+ SharedPrefs.getInstance().getAccessToken())
                        .method(original.method(),original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpclient.build();

        //Create Retrofit
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        DeleteService deleteService = retrofit.create(DeleteService.class);

        deleteService.deleteTask(DbContext.instance.alltask().get(taskAdapter.getSelection()).getLocalid()).enqueue(new Callback<DeleteJson>() {
            @Override
            public void onResponse(Call<DeleteJson> call, Response<DeleteJson> response) {

                Log.d(TAG, String.format("onResponse: %s",response.body() ));
                DbContext.instance.deleteTask(DbContext.instance.alltask().get(taskAdapter.getSelection()));
                getAllTask();
                taskAdapter.notifyDataSetChanged();



            }

            @Override
            public void onFailure(Call<DeleteJson> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                Toast.makeText(getActivity(),"failed to connect internet",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getAllTask() {
        progressBar.setVisibility(View.VISIBLE);
        OkHttpClient.Builder httpclient = new OkHttpClient().newBuilder();
        httpclient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization","JWT "+SharedPrefs.getInstance().getAccessToken())
                        .method(original.method(),original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpclient.build();

        //Create Retrofit
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        GetAllTaskService getAllTaskService = retrofit.create(GetAllTaskService.class);

        getAllTaskService.getAllTask().enqueue(new Callback<List<GetAllTaskResponeJson>>() {
            @Override
            public void onResponse(Call<List<GetAllTaskResponeJson>> call, Response<List<GetAllTaskResponeJson>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                DbContext.instance.deleteAllTask();
                List<GetAllTaskResponeJson> taskJsonList = response.body();
                for (GetAllTaskResponeJson getAllTaskResponeJson : taskJsonList) {
                    layout.setVisibility(View.VISIBLE);
                    Log.d(TAG, String.format("onResponse: %s", getAllTaskResponeJson));
                    Task task = new Task(getAllTaskResponeJson.getName(),getAllTaskResponeJson.getColor(),getAllTaskResponeJson.getPayment(),getAllTaskResponeJson.getLocalid());
                    task.setId(getAllTaskResponeJson.getId());
                    task.setDone(getAllTaskResponeJson.getDone());
                    if (task.getName()!= null) {
                        DbContext.instance.addOrUpdate(task);
                        taskAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<GetAllTaskResponeJson>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                layout.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
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
