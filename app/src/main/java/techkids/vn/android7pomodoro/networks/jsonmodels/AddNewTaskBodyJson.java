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
    SerializedName id;
    @SerializedName("color")
    String color;

    public AddNewTaskBodyJson(String name, boolean done, float payment, String date, SerializedName id, String color) {
        this.name = name;
        this.done = done;
        this.payment = payment;
        this.date = date;
        this.id = id;
        this.color = color;
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

    public SerializedName getId() {
        return id;
    }

    public void setId(SerializedName id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "AddNewTaskBodyJson{" +
                "name='" + name + '\'' +
                ", done=" + done +
                ", payment=" + payment +
                ", date='" + date + '\'' +
                ", id=" + id +
                ", color='" + color + '\'' +
                '}';
    }
}
