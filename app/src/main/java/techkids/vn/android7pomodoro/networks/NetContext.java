package techkids.vn.android7pomodoro.networks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by apple on 1/18/17.
 */

public class NetContext {
    public static  final NetContext instance = new NetContext();
     public String token;
    private Retrofit retrofit;

    public NetContext(String token) {
        this.token = token;
    }

    public NetContext() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
