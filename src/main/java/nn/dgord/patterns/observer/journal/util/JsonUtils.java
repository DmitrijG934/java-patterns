package nn.dgord.patterns.observer.journal.util;

import com.google.gson.Gson;

public class JsonUtils {
    private static Gson gson = getInstance();
    private JsonUtils() {}

    private static Gson getInstance() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static Object fromJson(String json, Class<?> clazz) {
        return gson.fromJson(json, clazz);
    }
}
