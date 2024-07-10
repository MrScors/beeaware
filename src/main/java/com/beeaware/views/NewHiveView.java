package com.beeaware.views;

import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class NewHiveView {

    public View getView() {
        try {
            View view = FXMLLoader.load(NewHiveView.class.getResource("new_hive.fxml"));
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }
}
