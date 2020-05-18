package net.CCweb;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

import org.semanticweb.owlapi.expression.ParserException;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.util.ShortFormProvider;

public class DLQueryPrinter {

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
     * @throws URISyntaxException 
     */
    public ArrayList<ontologyClass> askQuery(String classExpression) throws URISyntaxException {
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
