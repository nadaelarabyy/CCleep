package net.CCweb;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxEditorParser;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.expression.OWLEntityChecker;
import org.semanticweb.owlapi.expression.ParserException;
import org.semanticweb.owlapi.expression.ShortFormEntityChecker;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.util.BidirectionalShortFormProvider;
import org.semanticweb.owlapi.util.BidirectionalShortFormProviderAdapter;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;

import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;

import de.derivo.sparqldlapi.Query;
import de.derivo.sparqldlapi.QueryEngine;
import de.derivo.sparqldlapi.QueryResult;
import de.derivo.sparqldlapi.exceptions.QueryEngineException;
import de.derivo.sparqldlapi.exceptions.QueryParserException;



public class ontologyDLquery {
	public ontologyDLquery() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static QueryEngine engine;
	public static void main( String[] args ) throws OWLOntologyCreationException, OWLOntologyStorageException, IOException, URISyntaxException
    {
		String x = "C:\\Users\\user\\Desktop\\Ontologies\\V11RulesforFacets.owl";

		 OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
//		 OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File(x));
		 IRI pizzaontology = IRI.create("https://raw.githubusercontent.com/nadaelarabyy/Character/master/V11RulesforFacets.owl");
		 OWLOntology ontology=manager.loadOntology(pizzaontology);
		 System.out.println("Loaded: " + ontology.getOntologyID());

		 OWLReasonerFactory reasonerFactory = PelletReasonerFactory.getInstance();
	        OWLReasoner reasoner = reasonerFactory.createReasoner(ontology, new SimpleConfiguration());
//consume value FOOD-999999
			 ShortFormProvider shortFormProvider = new SimpleShortFormProvider();
	            // Create the DLQueryPrinter helper class. This will manage the
	            // parsing of input and printing of results
	            DLQueryPrinter dlQueryPrinter = new DLQueryPrinter(
	                    new DLQueryEngine(reasoner, shortFormProvider),
	                    shortFormProvider);
	            // Enter the query loop. A user is expected to enter class
	            // expression on the command line.
//	            doQueryLoop(dlQueryPrinter);
	            String expression="Human and consume value FOOD-999999";
	            dlQueryPrinter.askQuery(expression);

	        }
	
	public static void processQuery(String q)
	{
		try {
			long startTime = System.currentTimeMillis();
			
			// Create a query object from it's string representation
			Query query = Query.create(q);
			
			System.out.println("Excecute the query:");
			System.out.println(q);
			System.out.println("-------------------------------------------------");
			
			// Execute the query and generate the result set
			QueryResult result = engine.execute(query);
            
			if(query.isAsk()) {
				System.out.print("Result: ");
				if(result.ask()) {
					System.out.println("yes");
				}
				else {
					System.out.println("no");
				}
			}
			else {
				if(!result.ask()) {
					System.out.println("Query has no solution.\n");
				}
				else {
					System.out.println("Results:");
					System.out.print(result);
					
					System.out.println("-------------------------------------------------");
					System.out.println("Size of result set: " + result.size());
				}
			}

			System.out.println("-------------------------------------------------");
			System.out.println("Finished in " + (System.currentTimeMillis() - startTime) / 1000.0 + "s\n");
		}
        catch(QueryParserException e) {
        	System.out.println("Query parser error: " + e);
        }
        catch(QueryEngineException e) {
        	System.out.println("Query engine error: " + e);
        }
	}



	

}




class DLQueryParser {

    private final OWLOntology rootOntology;
    private final BidirectionalShortFormProvider bidiShortFormProvider;

    /**
     * Constructs a DLQueryParser using the specified ontology and short form
     * provider to map entity IRIs to short names.
     * 
     * @param rootOntology
     *        The root ontology. This essentially provides the domain vocabulary
     *        for the query.
     * @param shortFormProvider
     *        A short form provider to be used for mapping back and forth
     *        between entities and their short names (renderings).
     */
    public DLQueryParser(OWLOntology rootOntology,
            ShortFormProvider shortFormProvider) {
        this.rootOntology = rootOntology;
        OWLOntologyManager manager = rootOntology.getOWLOntologyManager();
        Set<OWLOntology> importsClosure = rootOntology.getImportsClosure();
        // Create a bidirectional short form provider to do the actual mapping.
        // It will generate names using the input
        // short form provider.
        bidiShortFormProvider = new BidirectionalShortFormProviderAdapter(
                manager, importsClosure, shortFormProvider);
    }

    /**
     * Parses a class expression string to obtain a class expression.
     * 
     * @param classExpressionString
     *        The class expression string
     * @return The corresponding class expression if the class expression string
     *         is malformed or contains unknown entity names.
     */
    public OWLClassExpression
            parseClassExpression(String classExpressionString) {
        OWLDataFactory dataFactory = rootOntology.getOWLOntologyManager()
                .getOWLDataFactory();
        // Set up the real parser
        ManchesterOWLSyntaxEditorParser parser = new ManchesterOWLSyntaxEditorParser(
                dataFactory, classExpressionString);
        parser.setDefaultOntology(rootOntology);
        // Specify an entity checker that wil be used to check a class
        // expression contains the correct names.
        OWLEntityChecker entityChecker = new ShortFormEntityChecker(
                bidiShortFormProvider);
        parser.setOWLEntityChecker(entityChecker);
        // Do the actual parsing
        return parser.parseClassExpression();
    }
}


