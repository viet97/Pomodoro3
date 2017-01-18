package com.example.quocvietdang.pomodoro1.settings;

/**
 * Created by Quoc Viet Dang on 1/16/2017.
 */

public class SettingCredentials {
    private int worktime;
    private int breaktime;
    private int longbreaktime;
    private static final SettingCredentials instance = new SettingCredentials(0,0,0);

    public static SettingCredentials getInstance() {
        return instance;
    }

    public SettingCredentials(int worktime, int breaktime, int longbreaktime) {
        this.worktime = worktime;
        this.breaktime = breaktime;
        this.longbreaktime = longbreaktime;
    }

    public int getLongbreaktime() {
        return longbreaktime;
    }

    public void setLongbreaktime(int longbreaktime) {
        this.longbreaktime = longbreaktime;
    }

    public int getBreaktime() {
        return breaktime;
    }

    public void setBreaktime(int breaktime) {
        this.breaktime = breaktime;
    }

    public int getWorktime() {
        return worktime;
    }

    public void setWorktime(int worktime) {
        this.worktime = worktime;
    }
    public String toString() {
        return "SettingCredendials{" +
                "worktime='" + worktime + '\'' +
                ", breaktime='" + breaktime + '\'' +
                ",longbreaktime=" + longbreaktime +'\'' +
                '}';
    }
}
