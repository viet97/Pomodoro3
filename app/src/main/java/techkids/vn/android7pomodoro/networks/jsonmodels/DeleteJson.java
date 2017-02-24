package techkids.vn.android7pomodoro.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Quoc Viet Dang on 2/23/2017.
 */

public class DeleteJson {
    @SerializedName("message")
    private String message;

    @SerializedName("code")
    private int code;

    public DeleteJson(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DeleteJson{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
