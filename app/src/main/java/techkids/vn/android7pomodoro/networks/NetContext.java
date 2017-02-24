package techkids.vn.android7pomodoro.networks;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;
import techkids.vn.android7pomodoro.networks.services.LoginService;
import techkids.vn.android7pomodoro.settings.SharedPrefs;


/**
 * Created by apple on 1/18/17.
 */

public class NetContext {
    public String token;
    public static  final NetContext instance = new NetContext();
    public Retrofit retrofit;


    private NetContext() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor())
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public LoginService creat(){
        return retrofit.create(LoginService.class);
    }

    class LoggerInterceptor implements Interceptor{
        public String TAG = "loggerInterceptor";
        @Override
        public Response intercept(Chain chain) throws IOException {
            // 1: Get request
            Request request = chain.request();

            //2: process request(print out)
            RequestBody body = request.body();

            if (body!= null)
                Log.d(TAG, String.format("intercept: %s", body.toString()));
            okhttp3.Headers headers = request.headers();

            if (headers!=null)
                Log.d(TAG, String.format("intercept: %s", headers));


            //3: Proceed
            Response response = chain.proceed(request);

            String s = getResponseString(response);

            //4:proceed respone
            Log.d(TAG, String.format("intercept: %s", s));

            return response;
        }
    }
    private String getResponseString(okhttp3.Response response) {
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        try {
            source.request(Long.MAX_VALUE); // Buffer the entire body.
        } catch (IOException e) {
            e.printStackTrace();
        }
        Buffer buffer = source.buffer();
        return buffer.clone().readString(Charset.forName("UTF-8"));
    }
    class HeaderInterceptor implements  Interceptor {
        String token1 = SharedPrefs.getInstance().getAccessToken();

        @Override
        public Response intercept(Chain chain) throws IOException {

            if (token1 != null) {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization","JWT "+ NetContext.instance.token)
                        .method(original.method(),original.body())
                        .build();
                
                return chain.proceed(request);
            }

            return chain.proceed(chain.request());
        }
    }
}
