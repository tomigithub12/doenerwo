package com.example.doenerwo;

import com.example.doenerwo.service.OpenStreetMapUtils;
import com.example.doenerwo.service.ReadKoordinatesFromGivenAddress;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Map;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableMongoRepositories
public class DoenerwoApplication implements CommandLineRunner {

	//@Autowired
	//ReadKoordinatesFromGivenAddress readKoordinatesFromGivenAddress = new ReadKoordinatesFromGivenAddress();

	@Autowired
	OpenStreetMapUtils openStreetMapUtils = new OpenStreetMapUtils();

	public static void main(String[] args) {
		SpringApplication.run(DoenerwoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//OpenStreetMapUtils openStreetMapUtils = new OpenStreetMapUtils();
		String address = "Mariahilferstrasse 120";
		Map<String, Double> coords;
		coords = openStreetMapUtils.getInstance().getCoordinates(address);
		System.out.println("latitude :" + coords.get("lat"));
		System.out.println("longitude:" + coords.get("lon"));
	}
}
