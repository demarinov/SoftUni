package app.entites.mappers;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;

public final class JsonMapper {

    private static Gson gson = new Gson();

    private JsonMapper() {
    }

    public static Object mapFromJson(FileReader reader, Class clazz) {
        return gson.fromJson(reader, clazz);
    }
    public static Object[] mapFromJsonArray(FileReader reader, Class clazz) {
        return (Object[]) gson.fromJson(reader, clazz);
    }

    public static void mapToJsonArray(FileWriter writer, Object[] object) {
        gson.toJson(object, writer);
    }

    public static void mapToJson(FileWriter writer, Object object) {
        gson.toJson(object, writer);
    }
}
