package vivo.chainpaper.util;

public class UserInfoUtil {

    private UserInfoUtil(){

    }

    public static NameUtil nameUtil = new RealNameUtil();
    public static String getUsername() {
        return nameUtil.getName();
    }
}
