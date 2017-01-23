package techkids.vn.android7pomodoro.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Quoc Viet Dang on 1/21/2017.
 */

public class RegisterResponseJson {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("token")
    private String token;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "RegisterResponseJson{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
