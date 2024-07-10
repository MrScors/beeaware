package com.beeaware.presenters;

import com.beeaware.data.Action;
import com.beeaware.data.storage.ActionDataStore;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ListActionPresenter {

    @FXML
    private View view;

    @FXML
    private ListView<Action> actionsListView;

    public void initialize() {
        view.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = AppManager.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e ->
                        AppManager.getInstance().getDrawer().open()));
                appBar.setTitleText("Actions");
            }
        });

        loadActions();
    }

    private void loadActions() {
        System.err.println("Building Actions");
        actionsListView.getItems().setAll(ActionDataStore.getActions());
    }


}
