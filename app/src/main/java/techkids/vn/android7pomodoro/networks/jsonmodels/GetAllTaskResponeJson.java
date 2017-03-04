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

    @SerializedName("local_id")
    String localid;

    @SerializedName("id")
    String id;

    @SerializedName("done")
    Boolean done;


    public GetAllTaskResponeJson(String name, float payment, String color, String localid, String id) {
        this.name = name;
        this.payment = payment;
        this.color = color;
        this.localid = localid;
        this.id = id;
        this.done = false;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
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

    public String getLocalid() {
        return localid;
    }

    public void setLocalid(String localid) {
        this.localid = localid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GetAllTaskResponeJson{" +
                "name='" + name + '\'' +
                ", payment=" + payment +
                ", color='" + color + '\'' +
                ", localid='" + localid + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
