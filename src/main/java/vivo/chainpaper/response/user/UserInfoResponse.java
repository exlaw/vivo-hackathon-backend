package vivo.chainpaper.response.user;

import vivo.chainpaper.response.Response;

public class UserInfoResponse extends Response {
    private String avatarUrl;
    private String realName;
    private String studentNumber;
    private String phone;
    private String email;

    public UserInfoResponse() {
    }

    public UserInfoResponse(String avatarUrl, String realName, String studentNumber, String phone, String email) {
        this.avatarUrl = avatarUrl;
        this.realName = realName;
        this.studentNumber = studentNumber;
        this.phone = phone;
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
