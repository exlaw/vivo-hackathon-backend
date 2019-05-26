package vivo.chainpaper.util;

public class UserInfoUtil {

    public static NameUtil nameUtil = new RealNameUtil();
    public static String getUsername() {
        return nameUtil.getName();
    }
}
