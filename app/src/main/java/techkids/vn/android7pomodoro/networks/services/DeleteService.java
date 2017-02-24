package techkids.vn.android7pomodoro.networks.services;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Url;
import techkids.vn.android7pomodoro.databases.DbContext;
import techkids.vn.android7pomodoro.networks.jsonmodels.DeleteJson;

/**
 * Created by Quoc Viet Dang on 2/23/2017.
 */

public interface DeleteService {
    @DELETE("task/{local_id}")
    Call<DeleteJson> deleteTask(@Path("local_id") String localid);
}
