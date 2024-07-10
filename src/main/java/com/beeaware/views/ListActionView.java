package com.beeaware.views;

import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class ListActionView {

    public View getView() {
        try {
            View view = FXMLLoader.load(ListActionView.class.getResource("actions_list.fxml"));
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }
}
