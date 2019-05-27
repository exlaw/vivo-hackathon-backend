package vivo.chainpaper.response.user;

import vivo.chainpaper.response.Response;

public class RegisterResponse extends Response {
    private String token;
    private String userId;

    public RegisterResponse() {
    }

    public RegisterResponse(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
