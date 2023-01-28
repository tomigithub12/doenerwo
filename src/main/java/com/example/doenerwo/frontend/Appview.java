package com.example.doenerwo.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Appview extends Application {

    @Override
    public void start(Stage mainStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startscreen-view.fxml"));
        Parent root = (Parent)loader.load();

        mainStage.setTitle("Alcatraz Registration");
        mainStage.setScene(new Scene(root));


        mainStage.show();
    }
}
