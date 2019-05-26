package vivo.chainpaper.parameters.user;

public class UserInfoParams {
    private String realName;
    private String studentNumber;
    private String phone;

    public UserInfoParams() {
    }

    public UserInfoParams(String realName, String studentNumber, String phone) {
        this.realName = realName;
        this.studentNumber = studentNumber;
        this.phone = phone;
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
}
