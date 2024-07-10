package com.beeaware.presenters;

import com.beeaware.data.Hive;
import com.beeaware.data.Observation;
import com.beeaware.data.storage.ObservationDataStore;
import com.gluonhq.charm.glisten.application.AppManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class NewObservationPresenter {

    @FXML
    private DatePicker observationDatePicker;

    @FXML
    private ChoiceBox<String> stateOfBroodChoiceBox;

    @FXML
    private ChoiceBox<String> queenPresenceChoiceBox;

    @FXML
    private ChoiceBox<String> chalkbroodChoiceBox;

    @FXML
    private ChoiceBox<String> swarmCellsChoiceBox;

    @FXML
    private ChoiceBox<String> angerLevelChoiceBox;

    @FXML
    private Button addObservation;

    private Hive hive;

    public void initialize() {
        hive = PrimaryPresenter.selectedHive;
//        stateOfBroodChoiceBox.getItems().addAll("Dronelayer", "Laying Workers", "Great Brood",
//                "Normal Brood", "Poor Brood", "Bad Brood");
        stateOfBroodChoiceBox.setValue("Normal Brood");

//        queenPresenceChoiceBox.getItems().addAll("Queen found", "Eggs found", "Virgin found", "Queen hatched");
        queenPresenceChoiceBox.setValue("Eggs found");

//        chalkbroodChoiceBox.getItems().addAll("Lots", "Little", "None");
        chalkbroodChoiceBox.setValue("None");

//        swarmCellsChoiceBox.getItems().addAll("Swarm Cells", "Supersedure Cells", "Both", "None");
        swarmCellsChoiceBox.setValue("None");

//        angerLevelChoiceBox.getItems().addAll("Peaceful", "Moderate", "Angry");
        angerLevelChoiceBox.setValue("Peaceful");
    }

    @FXML
    private void addAndReturn() {
        addObservation();
        AppManager.getInstance().switchView("home");
    }

    @FXML
    private void addAndAddAction() {
        addObservation();
        AppManager.getInstance().switchView("ActionView");
    }

    @FXML
    private void cancel() {
        AppManager.getInstance().switchView("home");
    }

    private void addObservation() {

        if (observationDatePicker.getValue() == null) {
            return;
            // add error display
        }

        String date = observationDatePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);

        String stateOfBrood = stateOfBroodChoiceBox.getValue();
        String queenPresence = queenPresenceChoiceBox.getValue();
        String chalkbrood = chalkbroodChoiceBox.getValue();
        String swarmCells = swarmCellsChoiceBox.getValue();
        String angerLevel = angerLevelChoiceBox.getValue();

        Observation observation = new Observation(UUID.randomUUID(), hive.getId(), date, stateOfBrood,
                queenPresence, chalkbrood, swarmCells, angerLevel);
        ObservationDataStore.addObservation(observation);

        // Close the view after adding observation
        AppManager.getInstance().switchView("home");
    }
}
