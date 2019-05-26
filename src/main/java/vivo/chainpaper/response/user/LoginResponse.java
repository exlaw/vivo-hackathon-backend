package vivo.chainpaper.response.user;

import vivo.chainpaper.response.Response;

public class LoginResponse extends Response {
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
