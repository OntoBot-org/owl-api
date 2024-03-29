package org.ontobot;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class JsonFileReader {
    public static void main(String[] args) {

    }
    public static JsonArray GetTaxonomies(String jsonPath) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(jsonPath)) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            // use jsonObject to access the data
            JsonObject msg = jsonObject.getAsJsonObject("msg");

            return msg.getAsJsonArray("taxonomy");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonArray GetOps(String jsonPath) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(jsonPath)) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            // use jsonObject to access the data
            JsonObject msg = jsonObject.getAsJsonObject("msg");

            return msg.getAsJsonArray("op");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] GetConcepts(String jsonPath) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(jsonPath)) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            // use jsonObject to access the data
            JsonObject msg = jsonObject.getAsJsonObject("msg");

            JsonArray jsonArray = msg.getAsJsonArray("concepts");

            String[] stringArray = new String[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                stringArray[i] = jsonArray.get(i).toString();
            }
            return stringArray;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSessionID(String jsonPath) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(jsonPath)){
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            // use jsonObject to access the data
            JsonObject msg = jsonObject.getAsJsonObject("msg");
            return msg.get("sessionID").toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void RecPrint(JsonArray taxonomies) {
        for (JsonElement taxo : taxonomies) {
            JsonObject classObject = taxo.getAsJsonObject();
            String class_name = classObject.get("class_name").getAsString();
            int level = classObject.get("level").getAsInt();
            System.out.println(class_name + " - " + level);
            if (classObject.has("sub_classes")) {
                RecPrint((JsonArray) classObject.get("sub_classes"));
                System.out.println("-----");
            }

        }
    }
}
