package com.restful.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.restful.entity.Movie;
import com.restful.repository.MovieRepository;

@RestController
@RequestMapping("/ImportCsv")
public class ImportCsvController{
	
	@Autowired
	private MovieRepository movieRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	@PostConstruct
	public int importCsv()  {
		
		Path path = Paths.get("src/main/java/file/movielist.csv");
		List<String> rowFile = new ArrayList<>() ;
		int cont = 0;
		int imported = 0;
		
		try {
			rowFile = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
			movieRepository.deleteAll();
			for (String row : rowFile) {
				if(!row.contains(";;;;")) {
					String[] splitValue = row.split(";",-1);
					if (cont >= 1 ) {				
						Movie movie = new Movie();
						movie.setYear(Integer.parseInt(splitValue[0]));
						movie.setTitle(splitValue[1]);
						movie.setStudio(splitValue[2]);
						movie.setProducer(splitValue[3]);
						movie.setWinner(splitValue[4]);
										
						movieRepository.save(movie);
						imported ++;
					}
				}

				cont ++;
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imported;
	}

}
