package vivo.chainpaper.util;

import java.io.File;

public class PathUtil {
    public static final String TEMP_FILE_NAME = "NJUTakeOut";

    private PathUtil(){

    }

    public static String getStaticPath() {
        return new File("static/").getAbsolutePath() + "/";
    }

}
