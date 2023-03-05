package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;




@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class REestController {
	

	

	
	//gets stock details
	@GetMapping("/getStocks")
	public JsonNode getStocks() throws IOException {
		
		
		return getValues("Stocks");
	}
	
	
	//gets stock values
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
	
	//returns json format of selected stocks
@PostMapping("/exportStockValues")
public ResponseEntity<List <StockValues>> exportStockValues(@RequestBody List<StockValues> stockValues) throws StreamWriteException, DatabindException{
	
	return ResponseEntity.ok(stockValues);
			
		
}
}
