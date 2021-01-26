package lab2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;


/**
 * 
 * @author ernesto
 *
 */
public class LoadAndSaveGraph {
	
	public LoadAndSaveGraph(String file) throws FileNotFoundException {
		
		Dataset dataset = RDFDataMgr.loadDataset(file);
		Model model = dataset.getDefaultModel();
				
		       
        
        System.out.println("The graph contains '" + model.listStatements().toSet().size() + "' triples.");
        
        //Storing in RDF/xml
        OutputStream out = new FileOutputStream(file + ".rdf");
        RDFDataMgr.write(out, model, RDFFormat.RDFXML);
		
	}

	public static void main(String[] args) {
		try {
			new LoadAndSaveGraph("beatles.ttl");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
