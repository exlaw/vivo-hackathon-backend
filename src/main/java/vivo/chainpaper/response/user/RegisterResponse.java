package vivo.chainpaper.response.user;

import vivo.chainpaper.response.Response;

public class RegisterResponse extends Response {
    private String token;

    public RegisterResponse() {
    }

    public RegisterResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
