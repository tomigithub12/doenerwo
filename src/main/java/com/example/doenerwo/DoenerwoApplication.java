package com.example.doenerwo;

import com.example.doenerwo.frontend.Appview;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Map;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableMongoRepositories
public class DoenerwoApplication {

	//Appview appview = new Appview();

/*
	public static void main(String[] args) {
		SpringApplication.run(DoenerwoApplication.class, args);
	}
 */
public static void main(String[] args) {
	Application.launch(Appview.class, args);
}


	//@Override
	//public void run(String... args) throws Exception {
	//	appview.start(new Stage());
	//}
}
