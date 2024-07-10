package com.beeaware.views;

import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class NewObservationView {

    public View getView() {
        try {
            View view = FXMLLoader.load(NewObservationView.class.getResource("new_observation.fxml"));
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }
}
