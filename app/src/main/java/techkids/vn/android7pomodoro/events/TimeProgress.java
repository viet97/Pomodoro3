package techkids.vn.android7pomodoro.events;

/**
 * Created by Quoc Viet Dang on 3/4/2017.
 */

public class TimeProgress {
    long mili;

    public TimeProgress(long mili) {
        this.mili = mili;
    }

    public long getMili() {
        return mili;
    }
}
