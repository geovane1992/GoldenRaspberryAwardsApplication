package com.restful.repository;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.restful.controller.GetProducerPrizesIntervalController;
import com.restful.controller.ImportCsvController;
import com.restful.model.ProducerPrizesIntervalModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class MovieRepositoryTest{

	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	private GetProducerPrizesIntervalController getProducerPrizesIntervalController;
	
	@Autowired
	private ImportCsvController importCsvController;
	
	@Autowired
	private MovieRepository movieRepository;
	
	
	
	//Testa se requidição de produtores premiadas é válida
	@Test
    public void getProducersPrizesInterval_ReturnStatus200() throws Exception {	
		mockMvc.perform(get("/producers/intervalPrizes")
		        .contentType("application/json")
		        .content(""))
		        .andExpect(MockMvcResultMatchers.status().isOk());
    }
	
	//Testa se total de produtores premiados com intevalo maximo bate com quantidade informada
	@Test
    public void getProducersPrizesIntervalTotalMin_ReturnTotalRecords() throws Exception {	
		mockMvc.perform(get("/producers/intervalPrizes")
		        .contentType("application/json")
		        .content(""))
		        .andExpect(MockMvcResultMatchers.status().isOk());
		
		Map<String, List<ProducerPrizesIntervalModel>> mapReturn = getProducerPrizesIntervalController.intervalPrizes();
		Assertions.assertEquals(mapReturn.get("min").size(), 1);
		
		
    }
	
	//Testa se total de produtores premiados com intevalo minimo bate com quantidade informada
	@Test
    public void getProducersPrizesIntervalTotalMax_ReturnStatus200() throws Exception {	
		mockMvc.perform(get("/producers/intervalPrizes")
		        .contentType("application/json")
		        .content(""))
		        .andExpect(MockMvcResultMatchers.status().isOk());
		
		Map<String, List<ProducerPrizesIntervalModel>> mapReturn = getProducerPrizesIntervalController.intervalPrizes();
		Assertions.assertEquals(mapReturn.get("max").size(), 1);
    }
	
	//Testa se total de registros importados bate com total de registros do banco.
	@Test
    public void getTotalRecordsImport_ReturnTotalRecords() throws Exception {	
		mockMvc.perform(post("/ImportCsv")
		        .contentType("text/plain")
		        .content(""))
		        .andExpect(MockMvcResultMatchers.status().isOk());
		
	long retImportCsv = importCsvController.importCsv();
	long recordsDatabase = movieRepository.count();
		Assertions.assertEquals(retImportCsv, recordsDatabase);
    }

}
