package techkids.vn.android7pomodoro.networks.services;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import techkids.vn.android7pomodoro.networks.jsonmodels.GetAllTaskResponeJson;

/**
 * Created by Quoc Viet Dang on 2/23/2017.
 */

public interface EditTask {
    @PUT("task/{localid}")
    Call<GetAllTaskResponeJson> editTask(@Path("localid") String localid,
                                         @Body RequestBody body);
}
