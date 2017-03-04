package techkids.vn.android7pomodoro.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Quoc Viet Dang on 2/20/2017.
 */

public class AddNewTaskBodyJson {
    @SerializedName("name")
    String name;
    @SerializedName("done")
    boolean done;
    @SerializedName("payment_per_hour")
    float payment;
    @SerializedName("due_date")
    String date;
    @SerializedName("local_id")
    String localID;
    @SerializedName("color")
    String color;
    @SerializedName("id")
    String id;

    public AddNewTaskBodyJson(String name, boolean done, float payment, String date, String localID, String color, String id) {
        this.name = name;
        this.done = done;
        this.payment = payment;
        this.date = date;
        this.localID = localID;
        this.color = color;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocalID() {
        return localID;
    }

    public void setLocalID(String localID) {
        this.localID = localID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AddNewTaskBodyJson{" +
                "name='" + name + '\'' +
                ", done=" + done +
                ", payment=" + payment +
                ", date='" + date + '\'' +
                ", localID='" + localID + '\'' +
                ", color='" + color + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
