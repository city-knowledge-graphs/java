package lab6;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;

public class OpenCSV {
	
	public OpenCSV(String fstring) throws IOException {
		
		CSVReader reader = new CSVReader(new FileReader(fstring));
		String [] nextRecord;//nextRecord
		 while ((nextRecord = reader.readNext()) != null) {
			 
			  for (String col : nextRecord){
				  System.out.println(col);
			  }
		 	  
		 }
		  
		  reader.close();
	}


	public static void main (String[] args) {
		
		try {
			new OpenCSV("files_lab6/worldcities-free-100.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
