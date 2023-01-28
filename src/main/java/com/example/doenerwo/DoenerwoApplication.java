package com.example.doenerwo;

import com.example.doenerwo.frontend.Start;
import com.example.doenerwo.service.DoenerstandService;
import com.example.doenerwo.service.OpenStreetMapUtils;
import com.example.doenerwo.service.ReadKoordinatesFromGivenAddress;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.Map;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableMongoRepositories
public class DoenerwoApplication implements CommandLineRunner {

	//@Autowired
	//ReadKoordinatesFromGivenAddress readKoordinatesFromGivenAddress = new ReadKoordinatesFromGivenAddress();

	@Autowired
	Start starter;

	@Autowired
	OpenStreetMapUtils openStreetMapUtils = new OpenStreetMapUtils();

	@Autowired
	DoenerstandService ds;

	@Autowired
	ExcelManager excelManager;


	public static void main(String[] args) {
		//SpringApplication.run(DoenerwoApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(DoenerwoApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		starter.init();
		//OpenStreetMapUtils openStreetMapUtils = new OpenStreetMapUtils();
		//String address = "Reinprechtsdorfer Strasse 9";
		//Map<String, Double> coords;
		//coords = openStreetMapUtils.getInstance().getCoordinates(address);
		//System.out.println("latitude :" + coords.get("lat"));
		//System.out.println("longitude:" + coords.get("lon"));

		//ds.printFindings();
		//ds.calculateFindings("Favoritenstrasse 226");
	}
}
