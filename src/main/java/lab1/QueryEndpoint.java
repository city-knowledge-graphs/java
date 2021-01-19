package lab1;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;

/**
 * 
 * @author ernesto
 *
 */
public class QueryEndpoint {

	public QueryEndpoint(String endpoint, String queryStr) {
		
		Query q = QueryFactory.create(queryStr);
		
		
		QueryExecution qe =
				QueryExecutionFactory.sparqlService(endpoint,q);
				try {
				ResultSet res = qe.execSelect();
				while( res.hasNext()) {
					QuerySolution soln = res.next();
					RDFNode a = soln.get("?x");
					System.out.println(""+a);
				}
			    
				} finally {
				qe.close();
				}
	
	}


	public static void main(String[] args) {

		//Query a remote RDF graph (e.g., SPARQL endpoint)
		String dbpedia_endpoint = "http://dbpedia.org/sparql";
		String  dbpedia_query = "SELECT DISTINCT ?x WHERE { <http://dbpedia.org/resource/Chicago_Bulls> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?x . }";

		//See more examples here: https://www.nobelprize.org/about/linked-data-examples/
		String nobelprize_endpoint = "http://data.nobelprize.org/sparql";
		String nobelprize_query = "SELECT DISTINCT ?x WHERE { ?laur <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://data.nobelprize.org/terms/Laureate> . ?laur <http://www.w3.org/2000/01/rdf-schema#label> ?x . ?laur <http://xmlns.com/foaf/0.1/gender> \"female\" . }";

		
		System.out.println("\nQuerying DBPedia Knowledge Graph (types of Chicago Bulls)");
		new QueryEndpoint(dbpedia_endpoint, dbpedia_query);

		System.out.println("\nQuerying Nobel Prize Knowledge Graph (Female laureates):");
		new QueryEndpoint(nobelprize_endpoint, nobelprize_query);

		
	
		
		
		
			
		
		
	}
}