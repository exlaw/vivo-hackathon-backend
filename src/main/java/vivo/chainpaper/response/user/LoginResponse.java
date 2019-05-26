package vivo.chainpaper.response.user;

import vivo.chainpaper.entity.Role;
import vivo.chainpaper.response.Response;

public class LoginResponse extends Response {
    private String token;
    private Role role;
    private String avatarUrl;
    private String username;
    private String name;
    private boolean emailValidated;

    public LoginResponse() {
    }

    public LoginResponse(String token, Role role, String avatarUrl, String username, String name, boolean emailValidated) {
        this.token = token;
        this.role = role;
        this.avatarUrl = avatarUrl;
        this.username = username;
        this.name = name;
        this.emailValidated = emailValidated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEmailValidated() {
        return emailValidated;
    }

    public void setEmailValidated(boolean emailValidated) {
        this.emailValidated = emailValidated;
    }
}
