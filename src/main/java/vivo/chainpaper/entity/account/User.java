package vivo.chainpaper.entity.account;

import vivo.chainpaper.entity.Role;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class User {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @Column(name = "avatarUrl")
    private String avatarUrl;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "real_name")
    private String realName;
    @Column(name = "student_number")
    private String number;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private Role role;
    @Column(name = "validationToken")
    private String validationToken;
    @Column(name = "validationCode")
    private String validationCode;
    @Column(name = "isValidated")
    private boolean isValidated;
    @Column(name = "isActive")
    private boolean isActive;
    @Column(name = "time")
    private long time;

    public User() {
    }

    public User(String avatarUrl, String username, String email, String phone, String realName, String number, String password, Role role, String validationToken, String validationCode, boolean isValidated, boolean isActive, long time) {
        this.avatarUrl = avatarUrl;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.realName = realName;
        this.number = number;
        this.password = password;
        this.role = role;
        this.validationToken = validationToken;
        this.validationCode = validationCode;
        this.isValidated = isValidated;
        this.isActive = isActive;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getValidationToken() {
        return validationToken;
    }

    public void setValidationToken(String validationToken) {
        this.validationToken = validationToken;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
