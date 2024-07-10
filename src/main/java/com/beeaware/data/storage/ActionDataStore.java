package com.beeaware.data.storage;

import com.beeaware.data.Action;
import com.beeaware.data.Hive;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ActionDataStore {
    private static final String FILE_PATH = "actions.json";
    private static List<Action> actions = new ArrayList<>();

    static {
        loadActions();
    }

    public static void loadActions() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Action>>() {}.getType();
            actions = new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveActions() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            new Gson().toJson(actions, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Action> getActions() {
        return actions;
    }

    public static void addAction(Action action) {
        actions.add(action);
        saveActions();
    }

    public static void removeAction(Action action) {
        actions.remove(action);
        saveActions();
    }
}