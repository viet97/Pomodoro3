package techkids.vn.android7pomodoro.networks.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import techkids.vn.android7pomodoro.networks.jsonmodels.GetAllTaskResponeJson;

/**
 * Created by Quoc Viet Dang on 2/20/2017.
 */

public interface GetAllTaskService {
    @GET("task")
    Call<List<GetAllTaskResponeJson>> getAllTask( );

}
