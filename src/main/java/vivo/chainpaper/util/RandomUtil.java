package vivo.chainpaper.util;


import java.util.Random;
import java.util.UUID;

public class RandomUtil {
    private RandomUtil(){

    }
    public static final String ALLCHAR = "0123456789";

    public static String generateCode(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuilder.append(ALLCHAR.charAt(new Random().nextInt(ALLCHAR.length())));
        }
        return new String(stringBuilder);
    }


    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}