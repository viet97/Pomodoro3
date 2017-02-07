package techkids.vn.android7pomodoro.settings;

/**
 * Created by apple on 1/14/17.
 */

public class LoginCredentials {
    private String accessToken;
    private String username;
    private String password;

    public LoginCredentials(String accessToken, String username, String password) {
        this.accessToken = accessToken;
        this.username = username;
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginCredentials{" +
                "accessToken='" + accessToken + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}