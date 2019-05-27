package vivo.chainpaper.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Role {

    public static final  String USER_NAME = "ROLE_USER";
    public static final  String TEACHER_NAME = "ROLE_TEACHER";
    public static final  String ADMIN_NAME = "ROLE_ADMIN";
    public static final  Role USER = new Role(USER_NAME);
    public static final Role ADMIN = new Role(ADMIN_NAME);
    public static final Role TEACHER = new Role(TEACHER_NAME);

    private String name;

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
