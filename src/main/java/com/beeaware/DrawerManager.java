package com.beeaware;

import com.beeaware.data.storage.HiveDataStore;
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
        
        NavigationDrawer.Header header = new NavigationDrawer.Header("Bee Aware",
                "Your Apiary Notes",
                new Avatar(21, new Image(DrawerManager.class.getResourceAsStream("/beeIcon.jpg"))));
        drawer.setHeader(header);
        
        final Item primaryItem = new ViewItem("Hives List", MaterialDesignIcon.HOME.graphic(), PRIMARY_VIEW, ViewStackPolicy.SKIP);
        final Item secondaryItem = new ViewItem("New Hive", MaterialDesignIcon.PLUS_ONE.graphic(), NEW_HIVE_VIEW);
        final Item listActionView = new ViewItem("Actions List", MaterialDesignIcon.LIST.graphic(), LIST_ACTION_VIEW);
        final Item listObservationView = new ViewItem("Observations List", MaterialDesignIcon.LIST.graphic(), LIST_OBSERVATION_VIEW);
        drawer.getItems().addAll(primaryItem, secondaryItem, listActionView, listObservationView);

        Runtime.getRuntime().addShutdownHook(new Thread(HiveDataStore::saveHives));
        
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