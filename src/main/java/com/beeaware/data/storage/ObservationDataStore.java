package com.beeaware.data.storage;

import com.beeaware.data.Hive;
import com.beeaware.data.Observation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ObservationDataStore {
    private static final String FILE_PATH = "observations.json";
    private static List<Observation> observations = new ArrayList<>();

    static {
        loadObservations();
    }

    public static void loadObservations() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Observation>>() {}.getType();
            observations = new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveObservations() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            new Gson().toJson(observations, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Observation> getObservations() {
        return observations;
    }

    public static void addObservation(Observation observation) {
        observations.add(observation);
        saveObservations();
    }

    public static void removeHive(Observation observation) {
        observations.remove(observation);
        saveObservations();
    }
}