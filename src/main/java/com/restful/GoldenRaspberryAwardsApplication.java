package com.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.restful.controller.ImportCsvController;

@SpringBootApplication
public class GoldenRaspberryAwardsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(GoldenRaspberryAwardsApplication.class, args);

		ImportCsvController importCsvController = new ImportCsvController(); 
		importCsvController.importCsv();

	}

}
