package com.beeaware.presenters;

import com.beeaware.data.Hive;
import com.beeaware.data.storage.HiveDataStore;
import com.gluonhq.charm.glisten.application.AppManager;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.gluonhq.charm.glisten.application.AppManager.HOME_VIEW;

@Slf4j
public class NewHivePresenter {
    @FXML
    private TextField boxNumbersField;

    @FXML
    private CheckBox isActiveCheckBox;

    @FXML
    private void addHive() {
        try {
            log.info("Hive adding");
            String boxNumbersText = boxNumbersField.getText();
            List<Integer> boxNumbers = Arrays.stream(boxNumbersText.split(" "))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            boolean isActive = isActiveCheckBox.isSelected();

            int newId = HiveDataStore.getHives().stream()
                    .mapToInt(Hive::getId)
                    .max()
                    .orElse(0) + 1;

            Hive newHive = new Hive(newId, boxNumbers, isActive);
            HiveDataStore.addHive(newHive);
            log.info("Hive added");

            AppManager.getInstance().switchView(HOME_VIEW);
        } catch (NumberFormatException e) {
            // Handle the error (e.g., show an error message to the user)
        }
    }

    @FXML
    private void cancel() {
        AppManager.getInstance().switchView(HOME_VIEW);
    }

}
