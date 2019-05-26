package vivo.chainpaper.util;


import java.util.UUID;

public class RandomUtil {
    public static final String ALLCHAR = "0123456789";

    public static String generateCode(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuilder.append(ALLCHAR.charAt((int) Math.floor(Math.random() * ALLCHAR.length())));
        }
        return new String(stringBuilder);
    }


    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}