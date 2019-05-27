package vivo.chainpaper.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import springfox.documentation.spring.web.json.Json;
import vivo.chainpaper.config.jsonAdapter.SpringfoxJsonToGsonAdapter;

public class GsonFactory {
    private GsonFactory(){

    }
    public static Gson get() {
        return new GsonBuilder()
                .registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter())
                .create();
    }
}
