package com.beeaware.presenters;

import com.beeaware.data.Hive;
import com.beeaware.data.storage.HiveDataStore;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PrimaryPresenter {

    @FXML
    private View primary;

    @FXML
    private TextField filterBoxId;

    @FXML
    private ListView<Hive> hiveListView;

    public static Hive selectedHive;

    public void initialize() {
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = AppManager.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e ->
                        AppManager.getInstance().getDrawer().open()));
                appBar.setTitleText("Primary");
                appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e ->
                        System.out.println("Search")));
            }
        });

        loadHives();
        setupHiveListView();
    }

    private void loadHives() {
        hiveListView.getItems().setAll(HiveDataStore.getHives());
    }

    private void setupHiveListView() {
        hiveListView.setCellFactory(lv -> {
            ListCell<Hive> cell = new ListCell<Hive>() {
                @Override
                protected void updateItem(Hive hive, boolean empty) {
                    super.updateItem(hive, empty);
                    if (empty || hive == null) {
                        setText(null);
                    } else {
                        setText(hive.toString());
                    }
                }
            };

            cell.setOnMousePressed(event -> {
                if (event.getButton() == MouseButton.PRIMARY && event.isPrimaryButtonDown() && event.getClickCount() == 1) {
                    cell.setContextMenu(createContextMenu(cell.getItem()));
                }
            });

            return cell;
        });
    }

    private ContextMenu createContextMenu(Hive hive) {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem removeItem = new MenuItem("Remove Hive");
        MenuItem actionItem = new MenuItem("Action");
        MenuItem observationItem = new MenuItem("Observation");

        removeItem.setOnAction(event -> showConfirmationDialog(hive));
        actionItem.setOnAction(event -> {
            selectedHive = hive;
            AppManager.getInstance().switchView("ActionView", ViewStackPolicy.SKIP);
        });
        observationItem.setOnAction(event -> {
            selectedHive = hive;
            AppManager.getInstance().switchView("ObservationView", ViewStackPolicy.SKIP);
        });

        contextMenu.getItems().addAll(removeItem, actionItem, observationItem);
        return contextMenu;
    }

    private void showConfirmationDialog(Hive hive) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Removal");
        alert.setHeaderText("Are you sure you want to remove this hive?");
        alert.setContentText("Hive ID: " + hive.getId());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            HiveDataStore.removeHive(hive);
            loadHives();
        }
    }

    @FXML
    private void filterHives() {
        String filterText = filterBoxId.getText();
        if (filterText.isEmpty()) {
            loadHives();
        } else {
            try {
                int boxId = Integer.parseInt(filterText);
                List<Hive> filteredHives = HiveDataStore.getHives().stream()
                        .filter(hive -> hive.getOccupiedBoxes().contains(boxId))
                        .collect(Collectors.toList());
                hiveListView.getItems().setAll(filteredHives);
            } catch (NumberFormatException e) {
                loadHives();
            }
        }
    }
}
