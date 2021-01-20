package lab1;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.riot.RDFDataMgr;
//import org.apache.jena.util.FileManager;

/**
 * 
 * @author ernesto
 *
 */
public class LoadRDFGraph {
	
	public LoadRDFGraph(String file) {
				
		Dataset dataset = RDFDataMgr.loadDataset(file);
		Model model = dataset.getDefaultModel();
				
		//Deprecated
		//FileManager.g.get().addLocatorClassLoader(LoadRDFGraph.class.getClassLoader());
        //Model model = FileManager.get().loadModel(file, null, "TURTLE");

        StmtIterator iter = model.listStatements();
        
               
        
        System.out.println("Printing '" + model.listStatements().toSet().size() + "' triples.");
        
        try {
            while ( iter.hasNext() ) {
                Statement stmt = iter.next();
                
                Resource s = stmt.getSubject();
                Resource p = stmt.getPredicate();
                RDFNode o = stmt.getObject();
                
                System.out.println(s.getURI() + " " + p.getURI() + " " + o.toString());
            }
        } finally {
            if ( iter != null ) iter.close();
        }
        

        //Query local model
        String queryStr = "SELECT DISTINCT ?x WHERE { ?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://stardog.com/tutorial/SoloArtist> . }";
        Query q = QueryFactory.create(queryStr);
		
        System.out.println("\nQuerying local model: ");
		
		QueryExecution qe =
				QueryExecutionFactory.create(q, model);
				try {
				ResultSet res = qe.execSelect();
				while( res.hasNext()) {
					QuerySolution soln = res.next();
					RDFNode a = soln.get("?x");
					System.out.println(""+a + " is a SoloArtist");
				}
			    
				} finally {
				qe.close();
				}
        
        
        
    }
	
	public static void main(String[] args) {

		new LoadRDFGraph("beatles.ttl");
		
	}
		

}
