package net.CCweb;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	public static void main( String[] args ) throws OWLOntologyCreationException, OWLOntologyStorageException, IOException
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


class DLQueryEngine {

    private final OWLReasoner reasoner;
    private final DLQueryParser parser;

    /**
     * Constructs a DLQueryEngine. This will answer "DL queries" using the
     * specified reasoner. A short form provider specifies how entities are
     * rendered.
     * 
     * @param reasoner
     *        The reasoner to be used for answering the queries.
     * @param shortFormProvider
     *        A short form provider.
     */
    public DLQueryEngine(OWLReasoner reasoner,
            ShortFormProvider shortFormProvider) {
        this.reasoner = reasoner;
        OWLOntology rootOntology = reasoner.getRootOntology();
        parser = new DLQueryParser(rootOntology, shortFormProvider);
    }

    /**
     * Gets the superclasses of a class expression parsed from a string.
     * 
     * @param classExpressionString
     *        The string from which the class expression will be parsed.
     * @param direct
     *        Specifies whether direct superclasses should be returned or not.
     * @return The superclasses of the specified class expression If there was a
     *         problem parsing the class expression.
     */
    public Set<OWLClass> getSuperClasses(String classExpressionString,
            boolean direct) {
        if (classExpressionString.trim().length() == 0) {
            return Collections.emptySet();
        }
        OWLClassExpression classExpression = parser
                .parseClassExpression(classExpressionString);
        NodeSet<OWLClass> superClasses = reasoner.getSuperClasses(
                classExpression, direct);
        return superClasses.getFlattened();
       
        
    }

    /**
     * Gets the equivalent classes of a class expression parsed from a string.
     * 
     * @param classExpressionString
     *        The string from which the class expression will be parsed.
     * @return The equivalent classes of the specified class expression If there
     *         was a problem parsing the class expression.
     */
    public Set<OWLClass> getEquivalentClasses(String classExpressionString) {
        if (classExpressionString.trim().length() == 0) {
            return Collections.emptySet();
        }
        OWLClassExpression classExpression = parser
                .parseClassExpression(classExpressionString);
        Node<OWLClass> equivalentClasses = reasoner
                .getEquivalentClasses(classExpression);
        Set<OWLClass> result;
        if (classExpression.isAnonymous()) {
            result = equivalentClasses.getEntities();
        } else {
            result = equivalentClasses.getEntitiesMinus(classExpression
                    .asOWLClass());
        }
        return result;
    }

    /**
     * Gets the subclasses of a class expression parsed from a string.
     * 
     * @param classExpressionString
     *        The string from which the class expression will be parsed.
     * @param direct
     *        Specifies whether direct subclasses should be returned or not.
     * @return The subclasses of the specified class expression If there was a
     *         problem parsing the class expression.
     */
    public Set<OWLClass> getSubClasses(String classExpressionString,
            boolean direct) {
        if (classExpressionString.trim().length() == 0) {
            return Collections.emptySet();
        }
        OWLClassExpression classExpression = parser
                .parseClassExpression(classExpressionString);
        NodeSet<OWLClass> subClasses = reasoner.getSubClasses(classExpression,
                direct);
        return subClasses.getFlattened();
    }

    /**
     * Gets the instances of a class expression parsed from a string.
     * 
     * @param classExpressionString
     *        The string from which the class expression will be parsed.
     * @param direct
     *        Specifies whether direct instances should be returned or not.
     * @return The instances of the specified class expression If there was a
     *         problem parsing the class expression.
     */
    public Set<OWLNamedIndividual> getInstances(String classExpressionString,
            boolean direct) {
        if (classExpressionString.trim().length() == 0) {
            return Collections.emptySet();
        }
        OWLClassExpression classExpression = parser
                .parseClassExpression(classExpressionString);
        NodeSet<OWLNamedIndividual> individuals = reasoner.getInstances(
                classExpression, direct);
        return individuals.getFlattened();
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

class DLQueryPrinter {

    private final DLQueryEngine dlQueryEngine;
    private final ShortFormProvider shortFormProvider;

    /**
     * @param engine
     *        the engine
     * @param shortFormProvider
     *        the short form provider
     */
    public DLQueryPrinter(DLQueryEngine engine,
            ShortFormProvider shortFormProvider) {
        this.shortFormProvider = shortFormProvider;
        dlQueryEngine = engine;
    }

    /**
     * @param classExpression
     *        the class expression to use for interrogation
     */
    public ArrayList<ontologyClass> askQuery(String classExpression) {
    	String value="none";
        ArrayList<ontologyClass> output=new ArrayList<ontologyClass>();

        if (classExpression.length() == 0) {
            System.out.println("No class expression specified");
        } else {
            try {
                StringBuilder sb = new StringBuilder();
                onotogyManager man=new onotogyManager();
                OWLOntology ont=man.loadOntology();
                sb.append("\n--------------------------------------------------------------------------------\n");
                sb.append("QUERY:   ");
                sb.append(classExpression);
                sb.append("\n");
                sb.append("--------------------------------------------------------------------------------\n\n");
                // Ask for the subclasses, superclasses etc. of the specified
                // class expression. Print out the results.
//                ------------------------------Direct super classes--------------------------------------------------------------------
                Set<OWLClass> superClasses = dlQueryEngine.getSuperClasses(
                        classExpression, true);
                for(OWLClass superClass:superClasses) {
                	ontologyClass example=new ontologyClass();
                    example.setIri(superClass.getIRI());
                    example.setClassName_ar(man.getRDFSLabel(superClass, ont, "ar"));
                    example.setClassName_en(man.getRDFSLabel(superClass, ont, "en"));
                    example.setComment(man.getRDFSComment(superClass, ont));
                    example.setDefinition(man.getRDFSDescription(superClass, ont));
                    if(!example.getIri().getShortForm().equals("Nothing")&&!chechContain(output, example)&&!example.getClassName_en().equals("none"))
                    output.add(example);
                }
                Set<OWLClass> superClasses2 = dlQueryEngine.getSuperClasses(
                        classExpression, false);
                for(OWLClass superClass:superClasses2) {
                	ontologyClass example=new ontologyClass();
                    example.setIri(superClass.getIRI());
                    example.setClassName_ar(man.getRDFSLabel(superClass, ont, "ar"));
                    example.setClassName_en(man.getRDFSLabel(superClass, ont, "en"));
                    example.setComment(man.getRDFSComment(superClass, ont));
                    example.setDefinition(man.getRDFSDescription(superClass, ont));
                    if(!example.getIri().getShortForm().equals("Nothing")&&!chechContain(output, example)&&!example.getClassName_en().equals("none"))
                    output.add(example);
                }
                
                
                
//                printEntities("SuperClasses", superClasses, sb);
//                --------------------------------------equivalent classes-----------------------------------------------------------------------
                Set<OWLClass> equivalentClasses = dlQueryEngine
                        .getEquivalentClasses(classExpression);
                for(OWLClass superClass:equivalentClasses) {
                	ontologyClass example=new ontologyClass();
                    example.setIri(superClass.getIRI());
                    example.setClassName_ar(man.getRDFSLabel(superClass, ont, "ar"));
                    example.setClassName_en(man.getRDFSLabel(superClass, ont, "en"));
                    example.setComment(man.getRDFSComment(superClass, ont));
                    example.setDefinition(man.getRDFSDescription(superClass, ont));
                    if(!example.getIri().getShortForm().equals("Nothing")&&!chechContain(output, example)&&!example.getClassName_en().equals("none"))
                    output.add(example);
                }
//                printEntities("EquivalentClasses", equivalentClasses, sb);
//                --------------------------------------direct subclasses-------------------------------------------------------
                Set<OWLClass> subClasses = dlQueryEngine.getSubClasses(
                        classExpression, true);
                for(OWLClass superClass:subClasses) {
                	ontologyClass example=new ontologyClass();
                    example.setIri(superClass.getIRI());
                    example.setClassName_ar(man.getRDFSLabel(superClass, ont, "ar"));
                    example.setClassName_en(man.getRDFSLabel(superClass, ont, "en"));
                    example.setComment(man.getRDFSComment(superClass, ont));
                    example.setDefinition(man.getRDFSDescription(superClass, ont));
                    if(!example.getIri().getShortForm().equals("Nothing")&&!chechContain(output, example)&&!example.getClassName_en().equals("none"))
                    output.add(example);
                }
                Set<OWLClass> subClasses2 = dlQueryEngine.getSubClasses(
                        classExpression, false);
                for(OWLClass superClass:subClasses2) {
                	ontologyClass example=new ontologyClass();
                    example.setIri(superClass.getIRI());
                    example.setClassName_ar(man.getRDFSLabel(superClass, ont, "ar"));
                    example.setClassName_en(man.getRDFSLabel(superClass, ont, "en"));
                    example.setComment(man.getRDFSComment(superClass, ont));
                    example.setDefinition(man.getRDFSDescription(superClass, ont));
                    if(!example.getIri().getShortForm().equals("Nothing")&&!chechContain(output, example)&&!example.getClassName_en().equals("none"))
                    output.add(example);
                }
                
//                printEntities("SubClasses", subClasses, sb);
//                -------------------------------------direct instances---------------------------------------------------------------------
                Set<OWLNamedIndividual> individuals = dlQueryEngine
                        .getInstances(classExpression, true);
                for(OWLNamedIndividual superClass:individuals) {
                	ontologyClass example=new ontologyClass();
                    example.setIri(superClass.getIRI());
                    
                    example.setClassName_ar(man.getRDFSLabel(superClass, ont, "ar"));
                    example.setClassName_en(man.getRDFSLabel(superClass, ont, "en"));
                    example.setComment(man.getRDFSComment(superClass, ont));
                    example.setDefinition(man.getRDFSDescription(superClass, ont));
                    if(!example.getIri().getShortForm().equals("Nothing")&&!chechContain(output, example)&&!example.getClassName_en().equals("none"))
                    output.add(example);
                }
                
                
//                printEntities("Instances", individuals, sb);
                System.out.println(output.toString());
                System.out.println(sb.toString());
                value=sb.toString();
            } catch (ParserException | OWLOntologyCreationException e) {
                System.out.println(e.getMessage());
            }
        }
        return output;
    }
    public boolean chechContain(ArrayList<ontologyClass> list , ontologyClass example) {
    	boolean flag=false;
    	for(int i=0;list.size()>i;i++) {
    		if(list.get(i).getIri().toString().equals(example.getIri().toString()))
    		{
    			flag=true;
    			break;
    		}
    	}
    	return flag;
    }

    private void printEntities(String name, Set<? extends OWLEntity> entities,
            StringBuilder sb) {
        sb.append(name);
        int length = 50 - name.length();
        for (int i = 0; i < length; i++) {
            sb.append(".");
        }
        sb.append("\n\n");
        if (!entities.isEmpty()) {
            for (OWLEntity entity : entities) {
                sb.append("\t");
                sb.append(shortFormProvider.getShortForm(entity));
                sb.append("\n");
            }
        } else {
            sb.append("\t[NONE]\n");
        }
        sb.append("\n");
    }
}
