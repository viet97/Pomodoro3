package techkids.vn.android7pomodoro.fragments;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

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
import techkids.vn.android7pomodoro.databases.DbContext;
import techkids.vn.android7pomodoro.databases.models.Task;
import techkids.vn.android7pomodoro.networks.NetContext;
import techkids.vn.android7pomodoro.networks.jsonmodels.AddNewTaskBodyJson;
import techkids.vn.android7pomodoro.networks.jsonmodels.GetAllTaskResponeJson;
import techkids.vn.android7pomodoro.networks.services.EditTask;

import static android.content.ContentValues.TAG;

/**
 * Created by Quoc Viet Dang on 2/16/2017.
 */

public class EditTaskBehavior implements OnOptionMenuBehavior {
    private String TAG ="onoption";

    @Override
    public void onClickOptionMenu(Task task1, Task task) {

        for (int i=0; i< DbContext.instance.alltask().size();i++){
//            if (DbContext.instance.alltask().get(i)==task1 ){
//                DbContext.instance.alltask().get(i).setColor(task.getColor());
//                DbContext.instance.alltask().get(i).setName(task.getName());
//                DbContext.instance.alltask().get(i).setPaymentPerHour(task.getPaymentPerHour());
//                DbContext.instance.alltask().get(i).setLocalid(task.getLocalid());
//            }
            task.setId(task1.getId());
            DbContext.instance.addOrUpdate(task);

        }

        //add Header

    }
}
