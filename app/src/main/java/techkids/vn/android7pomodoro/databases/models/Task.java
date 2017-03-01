package techkids.vn.android7pomodoro.databases.models;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quoc Viet Dang on 2/8/2017.
 */

public class Task extends RealmObject {
    @PrimaryKey
    String id;

    private String name;
    private String color;
    private float paymentPerHour;
    private String localid;



//    public Task(String name, String color) {
//        this.name = name;
//        this.color = color;
//    }

    public Task(String name, String color, float paymentPerHour, String localid) {

        this.name = name;
        this.color = color;
        this.paymentPerHour = paymentPerHour;
        this.localid = localid;
        id = UUID.randomUUID().toString();
    }
    public Task() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public float getPaymentPerHour() {
        return paymentPerHour;
    }

    public void setPaymentPerHour(float paymentPerHour) {
        this.paymentPerHour = paymentPerHour;
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

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", paymentPerHour=" + paymentPerHour +
                ", localid='" + localid + '\'' +
                '}';
    }
}
