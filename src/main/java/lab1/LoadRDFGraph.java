package lab1;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;

/**
 * 
 * @author ernesto
 *
 */
public class LoadRDFGraph {
	
	public LoadRDFGraph(String file) {
		
		FileManager.get().addLocatorClassLoader(LoadRDFGraph.class.getClassLoader());
        Model model = FileManager.get().loadModel(file, null, "TURTLE");

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
    }
	
	public static void main(String[] args) {

		new LoadRDFGraph("beatles.ttl");
		
	}
		

}
