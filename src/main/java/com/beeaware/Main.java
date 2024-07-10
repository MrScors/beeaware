package com.beeaware;

import com.beeaware.views.*;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static com.gluonhq.charm.glisten.application.AppManager.HOME_VIEW;

public class Main extends Application {

    public static final String PRIMARY_VIEW = HOME_VIEW;
    public static final String NEW_HIVE_VIEW = "NewHiveView";
    public static final String ACTION_VIEW = "ActionView";
    public static final String OBSERVATION_VIEW = "ObservationView";
    public static final String LIST_ACTION_VIEW = "ListActionView";
    public static final String LIST_OBSERVATION_VIEW = "ListObservationView";

    private final AppManager appManager = AppManager.initialize(this::postInit);

    @Override
    public void init() {
        appManager.addViewFactory(PRIMARY_VIEW, () -> new PrimaryView().getView());
        appManager.addViewFactory(NEW_HIVE_VIEW, () -> new NewHiveView().getView());
        appManager.addViewFactory(ACTION_VIEW, () -> new NewActionView().getView());
        appManager.addViewFactory(OBSERVATION_VIEW, () -> new NewObservationView().getView());
        appManager.addViewFactory(LIST_ACTION_VIEW, () -> new ListActionView().getView());
        appManager.addViewFactory(LIST_OBSERVATION_VIEW, () -> new ListObservationView().getView());

        DrawerManager.buildDrawer(appManager);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        appManager.start(primaryStage);
    }

    private void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);

        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
    }

    public static void main(String args[]) {
        launch(args);
    }
}
