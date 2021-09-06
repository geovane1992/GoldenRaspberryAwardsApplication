package com.restful.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.restful.entity.Movie;
import com.restful.model.ProducerPrizesIntervalModel;
import com.restful.repository.MovieRepository;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/producers")
public class GetProducerPrizesIntervalController {
	
	@Autowired
	private MovieRepository movieRepository;

	
	@GetMapping
	@RequestMapping("/intervalPrizes")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, List<ProducerPrizesIntervalModel>> intervalPrizes() {
		
		List<String> producers = new ArrayList<>();
		List<Integer> years = new ArrayList<>();
		List<ProducerPrizesIntervalModel> listProducerPrizesInterval = new ArrayList<>();
		List<ProducerPrizesIntervalModel> listIntervalMin = new ArrayList<>();
		List<ProducerPrizesIntervalModel> listIntervalMax = new ArrayList<>();
		Map<String,Integer> maxInteval = new HashMap<String,Integer>();
		Map<String,Integer> minInteval = new HashMap<String,Integer>();
		int interval;
		
		for(Movie movie : movieRepository.findAll()) {
			if (!producers.contains(movie.getProducer())) {
				producers.add(movie.getProducer());
			}
				
		}

		for(String producer : producers) {
			years.clear();
			interval = 0;
			List<Movie> movieForProducer = movieRepository.findAll().stream().filter(movie -> movie.getProducer().contains(producer) && movie.getWinner().equals("yes")).collect(Collectors.toList());
			if(movieForProducer.size() > 1) {
				for (Movie movie : movieForProducer){
					years.add(movie.getYear());
				}
				
				if(years.size() > 0) {
					 interval = Collections.max(years) - Collections.min(years) ;
					 
					 
					 ProducerPrizesIntervalModel producerPrizesInterval = new ProducerPrizesIntervalModel();
					 producerPrizesInterval.setProducer(producer);
					 producerPrizesInterval.setInterval(interval);
					 producerPrizesInterval.setPreviousWin(Collections.min(years));
					 producerPrizesInterval.setFollowingWin(Collections.max(years));
					 listProducerPrizesInterval.add(producerPrizesInterval);
					 
					 if(maxInteval.isEmpty()) {
							maxInteval.put(producer, interval) ;
							minInteval.put(producer, interval) ;
					}
					else {
						for(String keyMap : maxInteval.keySet()) {

							if(maxInteval.get(keyMap) < interval) {
								maxInteval.remove(keyMap);
								maxInteval.put(producer, interval) ;
							}
							else if(maxInteval.get(keyMap) == interval) {
								maxInteval.put(producer, interval) ;
							}
						}
						
						for(String keyMap : minInteval.keySet()) {
							if(minInteval.get(keyMap) > interval) {
								minInteval.remove(keyMap);
								minInteval.put(producer, interval) ;
							}
							else if(minInteval.get(keyMap) == interval) {
								minInteval.put(producer, interval) ;
							}
						}
					}
				}
				
			}

		}
		
		
		for(ProducerPrizesIntervalModel producerPrizesInterval : listProducerPrizesInterval) {
			int intervalComp = producerPrizesInterval.getFollowingWin() - producerPrizesInterval.getPreviousWin();
			for(String keyMap : minInteval.keySet()) {
				if(minInteval.get(keyMap) == intervalComp) {
					if(!listIntervalMin.contains(producerPrizesInterval)) {
						listIntervalMin.add(producerPrizesInterval);
					}
					
				}
			}
			for(String keyMap : maxInteval.keySet()) {
				if(maxInteval.get(keyMap) == intervalComp) {
					if(!listIntervalMax.contains(producerPrizesInterval)) {
						listIntervalMax.add(producerPrizesInterval);
					}
				}
			}
		}
		
	
		HashMap<String, List<ProducerPrizesIntervalModel>> mapListInterval = new HashMap<>();
		mapListInterval.put("min", listIntervalMin);
		mapListInterval.put("max", listIntervalMax);
		
		
		 return mapListInterval;
	}	
	
	
}
