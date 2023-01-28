package com.example.doenerwo.frontend;

import com.example.doenerwo.DoenerwoApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class Appview extends Application {
    private ConfigurableApplicationContext applicationContext;
    /*
    @Override
    public void start(Stage mainStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startscreen-view.fxml"));
        Parent root = (Parent)loader.load();

        mainStage.setTitle("Alcatraz Registration");
        mainStage.setScene(new Scene(root));


        mainStage.show();
    }
     */

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(DoenerwoApplication.class).run();
    }

    @Override
    public void start(Stage stage) {
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        public Stage getStage() {
            return ((Stage) getSource());
        }
    }
}
