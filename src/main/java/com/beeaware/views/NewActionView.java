package com.beeaware.views;

import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class NewActionView {

    public View getView() {
        try {
            View view = FXMLLoader.load(NewActionView.class.getResource("new_action.fxml"));
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }
}
