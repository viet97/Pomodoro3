package techkids.vn.android7pomodoro.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Quoc Viet Dang on 2/20/2017.
 */

public class GetAllTaskResponeJson {
    @SerializedName("name")
     String name;

    @SerializedName("payment_per_hour")
    float payment;

    @SerializedName("color")
    String color;

    public GetAllTaskResponeJson(String name, float payment, String color) {
        this.name = name;
        this.payment = payment;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
