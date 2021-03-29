package lab10;


import java.io.IOException;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;

public class GraphdbCommunication {
	
	
	public GraphdbCommunication() {
		
	}
	
	
	public void loadingData(String graphdb_endpoint, String file) throws IOException, InterruptedException {
		
		System.out.println("Uploading file: " + file);
	    String curl_command = "curl '" + graphdb_endpoint + "/statements' -X POST -H \"Content-Type:application/x-turtle\" -T '" + file + "'";

	    //Windows
	    //String[] commands = new String[]{"CMD", "/c", curl_command};
	    
	    //Linux/Mac
	    String[] commands = new String[]{"/bin/bash", "-c", curl_command};
		Runtime.getRuntime().exec(commands);
		
	}
	
	
	public void queryGraphDBRepo(String graphdb_endpoint, String queryStr) {
		
		Query q = QueryFactory.create(queryStr);
	
		
		QueryExecution qe =
				QueryExecutionFactory.sparqlService(graphdb_endpoint,q);
				try {
				ResultSet res = qe.execSelect();
				
				while( res.hasNext()) {
					QuerySolution soln = res.next();
					RDFNode country = soln.get("?country");
					RDFNode cities = soln.get("?num_cities");
					
					System.out.println(country.asResource().getURI() + " " + cities.asLiteral().getValue());
					
					
				}
			    
				} finally {
				qe.close();
				}
		
		
	}
	
	
	

	public static void main(String[] args) {
	
		//GraphDB Endpoint
		String graphdb_endpoint = "http://192.168.0.18:7200/repositories/Lab6_repository_automatic";
								
		//
		String path_to_data_file = "/home/ernesto/git/java/files_lab6/worldcities-free-100-task4.ttl";
		String path_to_onto_file = "/home/ernesto/git/java/files_lab6/ontology_lab6.ttl";

		try {
			GraphdbCommunication graphdb_access = new GraphdbCommunication();
		
			
			//LOAD DATA
			graphdb_access.loadingData(graphdb_endpoint, path_to_onto_file);
			graphdb_access.loadingData(graphdb_endpoint, path_to_data_file);
			
			
			
			
			//QUERY DATA
			String queryStr = 
		            "PREFIX lab6: <http://www.semanticweb.org/ernesto/inm713/lab6/>\n" +
		                   	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
		                	"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> \n" +
		                   	"SELECT DISTINCT ?country (COUNT(?city) AS ?num_cities) WHERE { \n" +
		                    "?country lab6:hasCity ?city .  \n" +
		                "\n}"+
		                "GROUP BY ?country\n" +
		                "ORDER BY DESC(?num_cities)\n" +
		                "LIMIT 10";
			
			
			graphdb_access.queryGraphDBRepo(graphdb_endpoint, queryStr);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
