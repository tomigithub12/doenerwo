package com.example.doenerwo.frontend;

import com.example.doenerwo.frontend.Appview;
import com.example.doenerwo.frontend.Appview.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    private final String applicationTitle;

    //private final FxWeaver fxWeaver;


    //public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle) {
    public StageInitializer(@Value("DoenerWo") String applicationTitle) {
        this.applicationTitle = applicationTitle;
        //this.fxWeaver = fxWeaver;
    }
    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("startscreen-view.fxml"));
        Parent root = null;
        try {
            root = (Parent)loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //stage.setTitle("Alcatraz Registration");
        //stage.setScene(new Scene(root));

        stage.setScene(new Scene(root));
        stage.setTitle(applicationTitle);
        stage.show();
    }
}