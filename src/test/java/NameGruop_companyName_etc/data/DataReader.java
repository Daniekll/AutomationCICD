package NameGruop_companyName_etc.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String,String>> getJsonDataToMap() throws IOException {
	
		
		//reading json as String
	String jsonContent=	FileUtils.readFileToString(new File ("C:/Users/Hp/eclipse-workspace/SeleniumFrameworkDesign2/"
				+ "src/test/java/NameGruop_companyName_etc/data/PurchaseOrder.json"), StandardCharsets.UTF_8);
	
	   // String to HashMap - will need Jackson databid
	
	ObjectMapper  mapper = new ObjectMapper();
	 List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
	 return data;
		 
	 
	}
	
}
