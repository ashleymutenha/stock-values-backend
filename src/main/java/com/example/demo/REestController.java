package com.example.demo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class REestController {
	
	StockRepository stockRepository;
	@GetMapping("/getStocks")
	public JsonNode getStocks() throws IOException {
		
		
		return getValues("Stocks");
	}
	
	
	
	@GetMapping("/getStockValues")
	
	public JsonNode getStockValues() throws IOException {
		
		return getValues("Stock__Values");
	}
	
	
	
	public JsonNode getValues(String fileName) throws IOException {
			InputStream file  = new ClassPathResource("/static/"+fileName +".json").getInputStream();
					
					BufferedReader streamReader = new BufferedReader(new InputStreamReader(file));
					
					StringBuilder stringBuilder = new StringBuilder();
					ObjectMapper mapper = new ObjectMapper();
					JsonFactory factory = mapper.getFactory();
					
			        JsonNode node;
					
					String currentLine;
					try {
					while((currentLine = streamReader.readLine())!= null) {
						stringBuilder.append(currentLine);
					}
					JsonParser jsonParser = factory.createParser(stringBuilder.toString());
					node = mapper.readTree(jsonParser);
				
					}
					catch(IOException error){
						
						JsonParser jsonParser = factory.createParser("{'value':'error'}");
						node = mapper.readTree(jsonParser);
						
					}
					
					
					
					return node;
				}

	//functionality work in progress
//	@RequestMapping(value ="/exportStockValues" ,consumes = MediaType.APP)
//	public String exportStockValues(@RequestBody StockValues stockValues) {
//		
//		stockRepository.save(stockValues);
//		return "saved";
//	}
}
