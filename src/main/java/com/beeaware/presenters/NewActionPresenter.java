package com.beeaware.presenters;

import com.beeaware.data.Action;
import com.beeaware.data.storage.ActionDataStore;
import com.beeaware.data.Hive;
import com.gluonhq.charm.glisten.application.AppManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NewActionPresenter {

    @FXML
    private DatePicker actionDatePicker;

    @FXML
    private CheckBox addingQueen;

    @FXML
    private CheckBox removingQueen;

    @FXML
    private CheckBox exportingBroodFrames;

    @FXML
    private CheckBox harvestingHoney;

    @FXML
    private Button addAction;

    private Hive hive;

    @FXML
    private void addAction() {

        hive = PrimaryPresenter.selectedHive;

        if (actionDatePicker.getValue() == null) {
            return;
            // add error display
        }

        String date = actionDatePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
        List<String> actionTypes = new ArrayList<>();

        if (addingQueen.isSelected()) {
            Action action = new Action(UUID.randomUUID(), hive.getId(), date, "Add Queen");
            ActionDataStore.addAction(action);
        }
        if (removingQueen.isSelected()) {
            Action action = new Action(UUID.randomUUID(), hive.getId(), date, "Remove Queen");
            ActionDataStore.addAction(action);
        }
        if (exportingBroodFrames.isSelected()) {
            Action action = new Action(UUID.randomUUID(), hive.getId(), date, "Export Brood");
            ActionDataStore.addAction(action);
        }
        if (harvestingHoney.isSelected()) {
            Action action = new Action(UUID.randomUUID(), hive.getId(), date, "Harvest");
            ActionDataStore.addAction(action);
        }
        // Close the view after adding actions
        AppManager.getInstance().switchView("home");
    }

    @FXML
    private void cancel() {
        AppManager.getInstance().switchView("home");
    }

}
