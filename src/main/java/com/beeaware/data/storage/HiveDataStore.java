package com.beeaware.data.storage;

import com.beeaware.data.Hive;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HiveDataStore {
    private static final String FILE_PATH = "hives.json";
    private static List<Hive> hives = new ArrayList<>();

    static {
        loadHives();
    }

    public static void loadHives() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Hive>>() {}.getType();
            hives = new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveHives() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            new Gson().toJson(hives, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Hive> getHives() {
        return hives;
    }

    public static void addHive(Hive hive) {
        hives.add(hive);
        saveHives();
    }

    public static void removeHive(Hive hive) {
        hives.remove(hive);
        saveHives();
    }
}