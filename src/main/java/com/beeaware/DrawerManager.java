package com.beeaware;

import com.gluonhq.attach.lifecycle.LifecycleService;
import com.gluonhq.attach.util.Platform;
import com.gluonhq.attach.util.Services;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Item;
import com.gluonhq.charm.glisten.control.NavigationDrawer.ViewItem;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.scene.image.Image;

import static com.beeaware.Main.*;

public class DrawerManager {

    public static void buildDrawer(AppManager app) {
        NavigationDrawer drawer = app.getDrawer();
        
        NavigationDrawer.Header header = new NavigationDrawer.Header("Gluon Application",
                "Multi View Project",
                new Avatar(21, new Image(DrawerManager.class.getResourceAsStream("/icon.png"))));
        drawer.setHeader(header);
        
        final Item primaryItem = new ViewItem("Hives List", MaterialDesignIcon.HOME.graphic(), PRIMARY_VIEW, ViewStackPolicy.SKIP);
        final Item secondaryItem = new ViewItem("New Hive", MaterialDesignIcon.DASHBOARD.graphic(), NEW_HIVE_VIEW);
        final Item listActionView = new ViewItem("Actions List", MaterialDesignIcon.DASHBOARD.graphic(), LIST_ACTION_VIEW);
        final Item listObservationView = new ViewItem("Observations List", MaterialDesignIcon.DASHBOARD.graphic(), LIST_OBSERVATION_VIEW);
        drawer.getItems().addAll(primaryItem, secondaryItem, listActionView, listObservationView);
        
        if (Platform.isDesktop()) {
            final Item quitItem = new Item("Quit", MaterialDesignIcon.EXIT_TO_APP.graphic());
            quitItem.selectedProperty().addListener((obs, ov, nv) -> {
                if (nv) {
                    Services.get(LifecycleService.class).ifPresent(LifecycleService::shutdown);
                }
            });
            drawer.getItems().add(quitItem);
        }
    }
}