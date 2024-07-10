package com.beeaware.presenters;

import com.beeaware.data.Observation;
import com.beeaware.data.storage.ObservationDataStore;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ListObservationPresenter {

    @FXML
    private View view;

    @FXML
    private ListView<Observation> observationsListView;

    public void initialize() {
        view.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = AppManager.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e ->
                        AppManager.getInstance().getDrawer().open()));
                appBar.setTitleText("Primary");
                appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e ->
                        System.out.println("Search")));
            }
        });

        loadObservations();
    }

    private void loadObservations() {
        observationsListView.getItems().setAll(ObservationDataStore.getObservations());
    }
}
