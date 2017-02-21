package techkids.vn.android7pomodoro.networks.services;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import techkids.vn.android7pomodoro.networks.jsonmodels.GetAllTaskResponeJson;

/**
 * Created by Quoc Viet Dang on 2/20/2017.
 */

public interface AddNewTaskService {
    @POST("task")
    Call<GetAllTaskResponeJson> addTask(@Body RequestBody body);
}
