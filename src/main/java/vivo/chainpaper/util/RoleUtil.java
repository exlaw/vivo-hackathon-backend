package vivo.chainpaper.util;

import vivo.chainpaper.entity.Role;

import java.util.HashMap;
import java.util.Map;

import static vivo.chainpaper.entity.Role.*;

public class RoleUtil {
    private RoleUtil(){

    }

    private static final Map<String, Role> ROLE_TABLE = new HashMap<>();

    static {
        ROLE_TABLE.put(USER_NAME, Role.USER);
        ROLE_TABLE.put(TEACHER_NAME, Role.TEACHER);
        ROLE_TABLE.put(ADMIN_NAME, Role.ADMIN);
    }
}
