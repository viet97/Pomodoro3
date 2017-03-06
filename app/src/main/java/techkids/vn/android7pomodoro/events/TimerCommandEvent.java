package techkids.vn.android7pomodoro.events;

/**
 * Created by Quoc Viet Dang on 3/4/2017.
 */


public class TimerCommandEvent {
    private TimerCommand command;

    public TimerCommandEvent(TimerCommand command) {
        this.command = command;
    }

    public TimerCommand getCommand() {
        return command;
    }


}
