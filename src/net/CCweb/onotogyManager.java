package net.CCweb;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.search.EntitySearcher;

public class onotogyManager {
	
	public onotogyManager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws OWLOntologyCreationException {
	   onotogyManager manager=new onotogyManager();
	   OWLOntology ontology=manager.loadOntology();
	   OWLReasoner reasoner=manager.useReasoner(ontology);
	   reasoner.precomputeInferences();
//	   System.out.println(manager.getSleepDisorder(ontology, reasoner));
	   
//	   System.out.println(manager.getVegetables(ontology, reasoner).toString());
//	   System.out.println(manager.getSweetFood(ontology, reasoner).toString());
//	   System.out.println(manager.getSausages(ontology, reasoner).toString());
//	   System.out.println(manager.getNutsAndSeeds(ontology, reasoner).toString());
//	   System.out.println(manager.getDairyProducts(ontology, reasoner).toString());
//	   System.out.println(manager.getActivity(ontology, reasoner).toString());
//	   System.out.println(manager.getFruits(ontology, reasoner).toString());
//	   System.out.println(manager.getFish(ontology, reasoner).toString());
//	   System.out.println(manager.getEggs(ontology, reasoner).toString());
//	   System.out.println(manager.getCereal(ontology, reasoner).toString());
//	   System.out.println(manager.getBread(ontology, reasoner).toString());
//	   System.out.println(manager.getBiscuitsAndCracker(ontology, reasoner).toString());
//	   System.out.println(manager.getAlcoholicBeverages(ontology, reasoner).toString());
//	   System.out.println(manager.getNonAlcoholicDrinks(ontology, reasoner).toString());
//	   System.out.println(manager.getCoffeeDrinks(ontology, reasoner).toString());
//	   System.out.println(manager.getConsumptionBehavior(ontology, reasoner).toString());
//	   System.out.println(manager.getEntertainmentBehavior(ontology, reasoner).toString());
//	   System.out.println(manager.getSleepBehavior(ontology, reasoner).toString());
//	   System.out.println(manager.getEmotionProcess(ontology, reasoner).toString());
//	   System.out.println(manager.getBodilyProcess(ontology, reasoner).toString());
	}
public  ArrayList<ontologyClass> getVegetables(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException{
//		FreshVegetables

		 ArrayList<ontologyClass> coffeeBeverages=new ArrayList<ontologyClass>();
		 
			for(OWLClass allclasses:ontology.getClassesInSignature()){
				if(allclasses.getIRI().getShortForm().toString().equals("FreshVegetables") ){
					ontologyClass coffeeBeverage=new ontologyClass();
					coffeeBeverage.setIri(allclasses.getIRI());
					coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
					coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
					coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
					coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
					coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
					coffeeBeverages.add(coffeeBeverage);
				 
			}
//				ProcessedVegetables
				if(allclasses.getIRI().getShortForm().toString().equals("ProcessedVegetables") ){
					ontologyClass coffeeBeverage=new ontologyClass();
					coffeeBeverage.setIri(allclasses.getIRI());
					coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
					coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
					coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
					coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
					coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
					coffeeBeverages.add(coffeeBeverage);
				 
			}
				if(allclasses.getIRI().getShortForm().toString().equals("FreshStarchyVegetables") ){
					ontologyClass coffeeBeverage=new ontologyClass();
					coffeeBeverage.setIri(allclasses.getIRI());
					coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
					coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
					coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
					coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
					coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
					coffeeBeverages.add(coffeeBeverage);
				 
			}
				if(allclasses.getIRI().getShortForm().toString().equals("ProcessedStarchyVegetables") ){
					ontologyClass coffeeBeverage=new ontologyClass();
					coffeeBeverage.setIri(allclasses.getIRI());
					coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
					coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
					coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
					coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
					coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
					coffeeBeverages.add(coffeeBeverage);
				 
			}
				
				
				
				
				
				
				
				
				
				
			}
			return coffeeBeverages;
//			System.out.println(coffeeBeverages.toString());

			
	}

public  ArrayList<ontologyClass> getSweetFood(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException{
		
		 ArrayList<ontologyClass> breadExamples=new ArrayList<ontologyClass>();
		 
			for(OWLClass allclasses:ontology.getClassesInSignature()){
				if(allclasses.getIRI().getShortForm().toString().equals("Candy") ){
					ontologyClass breadClass=new ontologyClass();
					breadClass.setIri(allclasses.getIRI());
					breadClass.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
					breadClass.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
					breadClass.setComment(getRDFSComment(allclasses, ontology));
					breadClass.setDefinition(getRDFSDescription(allclasses, ontology));
					breadClass.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
					breadExamples.add(breadClass);
					   
			}
				if(allclasses.getIRI().getShortForm().toString().equals("CannedOrCookedFruit") ){
					ontologyClass breadClass=new ontologyClass();
					breadClass.setIri(allclasses.getIRI());
					breadClass.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
					breadClass.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
					breadClass.setComment(getRDFSComment(allclasses, ontology));
					breadClass.setDefinition(getRDFSDescription(allclasses, ontology));
					breadClass.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
					breadExamples.add(breadClass);
					   
			}
//				ChocolateAndCocoa
				if(allclasses.getIRI().getShortForm().toString().equals("ChocolateAndCocoa") ){
					ontologyClass breadClass=new ontologyClass();
					breadClass.setIri(allclasses.getIRI());
					breadClass.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
					breadClass.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
					breadClass.setComment(getRDFSComment(allclasses, ontology));
					breadClass.setDefinition(getRDFSDescription(allclasses, ontology));
					breadClass.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
					breadExamples.add(breadClass);
					   
			}
//				Dessert
				if(allclasses.getIRI().getShortForm().toString().equals("Dessert") ){
					ontologyClass breadClass=new ontologyClass();
					breadClass.setIri(allclasses.getIRI());
					breadClass.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
					breadClass.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
					breadClass.setComment(getRDFSComment(allclasses, ontology));
					breadClass.setDefinition(getRDFSDescription(allclasses, ontology));
					breadClass.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
					breadExamples.add(breadClass);
					   
			}	
//				IceCream
				if(allclasses.getIRI().getShortForm().toString().equals("IceCream") ){
					ontologyClass breadClass=new ontologyClass();
					breadClass.setIri(allclasses.getIRI());
					breadClass.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
					breadClass.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
					breadClass.setComment(getRDFSComment(allclasses, ontology));
					breadClass.setDefinition(getRDFSDescription(allclasses, ontology));
					breadClass.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
					breadExamples.add(breadClass);
					   
			}
//				Pastry
				if(allclasses.getIRI().getShortForm().toString().equals("Pastry") ){
					ontologyClass breadClass=new ontologyClass();
					breadClass.setIri(allclasses.getIRI());
					breadClass.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
					breadClass.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
					breadClass.setComment(getRDFSComment(allclasses, ontology));
					breadClass.setDefinition(getRDFSDescription(allclasses, ontology));
					breadClass.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
					breadExamples.add(breadClass);
					   
			}
//				SweetSnacksAndBrioches
				if(allclasses.getIRI().getShortForm().toString().equals("SweetSnacksAndBrioches") ){
					ontologyClass breadClass=new ontologyClass();
					breadClass.setIri(allclasses.getIRI());
					breadClass.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
					breadClass.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
					breadClass.setComment(getRDFSComment(allclasses, ontology));
					breadClass.setDefinition(getRDFSDescription(allclasses, ontology));
					breadClass.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
					breadExamples.add(breadClass);
					   
			}
//				SweetSpreads
				if(allclasses.toStringID().indexOf("SweetSpreads")>0 ){
					   for(Node<OWLClass> node:reasoner.getSubClasses(allclasses, true)) {
						   for(OWLClass classtest:node.getEntities()) {
							   ontologyClass activityClass=new ontologyClass();
							   activityClass.setIri(classtest.getIRI());
							   activityClass.setClassName_ar(getRDFSLabel(classtest, ontology, "ar"));
							   activityClass.setClassName_en(getRDFSLabel(classtest, ontology,"en"));
							   activityClass.setComment(getRDFSComment(classtest, ontology));
							   activityClass.setDefinition(getRDFSDescription(classtest, ontology));
							   activityClass.setInstances(getInstances(reasoner, classtest.getIRI(), ontology));
							   if(!classtest.getIRI().getShortForm().toString().equals("Nothing"))
							    breadExamples.add(activityClass);
							   
							   
							   	
					   }	
				}
			}
				
				
				
				
				
				
			}
//			System.out.println(breadExamples.toString());
			return breadExamples;
		
	}
	
public  ArrayList<ontologyClass> getSausages(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException{
//		Sausages
	
		 ArrayList<ontologyClass> coffeeBeverages=new ArrayList<ontologyClass>();
		
			for(OWLClass allclasses:ontology.getClassesInSignature()){
				if(allclasses.getIRI().getShortForm().toString().equals("Sausages") ){
					ontologyClass coffeeBeverage=new ontologyClass();
					coffeeBeverage.setIri(allclasses.getIRI());
					coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
					coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
					coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
					coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
					coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
					coffeeBeverages.add(coffeeBeverage);
					break;   
			}	
			}
//			System.out.println(coffeeBeverages.toString());
			return coffeeBeverages;
		

		
	}
	
public  ArrayList<ontologyClass> getNutsAndSeeds(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException{
	
		 ArrayList<ontologyClass> alcoholicBeverages=new ArrayList<ontologyClass>();
		 
			for(OWLClass allclasses:ontology.getClassesInSignature()){
				if(allclasses.toStringID().indexOf("NutSeedAndOliveProducts")>0 ){
					   for(Node<OWLClass> node:reasoner.getSubClasses(allclasses, true)) {
						   for(OWLClass classtest:node.getEntities()) {
							   ontologyClass alcoholicBeverageClass=new ontologyClass();
							   alcoholicBeverageClass.setIri(classtest.getIRI());
							   alcoholicBeverageClass.setClassName_ar(getRDFSLabel(classtest, ontology, "ar"));
							   alcoholicBeverageClass.setClassName_en(getRDFSLabel(classtest, ontology,"en"));
							   alcoholicBeverageClass.setComment(getRDFSComment(classtest, ontology));
							   alcoholicBeverageClass.setDefinition(getRDFSDescription(classtest, ontology));
							   alcoholicBeverageClass.setInstances(getInstances(reasoner, classtest.getIRI(), ontology));
							   if(!classtest.getIRI().getShortForm().toString().equals("Nothing"))
								   alcoholicBeverages.add(alcoholicBeverageClass);
							   
							   
							   	
					   }	
				}
			}	
			}
//			System.out.println(alcoholicBeverages.toString());
			return alcoholicBeverages;
		
	}
	
public  ArrayList<ontologyClass> getDairyProducts(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException{
		
		 ArrayList<ontologyClass> alcoholicBeverages=new ArrayList<ontologyClass>();
		 
			
			for(OWLClass allclasses:ontology.getClassesInSignature()){
				if(allclasses.toStringID().indexOf("Cheese")>0 ){
					   for(Node<OWLClass> node:reasoner.getSubClasses(allclasses, true)) {
						   for(OWLClass classtest:node.getEntities()) {
							   ontologyClass alcoholicBeverageClass=new ontologyClass();
							   alcoholicBeverageClass.setIri(classtest.getIRI());
							   alcoholicBeverageClass.setClassName_ar(getRDFSLabel(classtest, ontology, "ar"));
							   alcoholicBeverageClass.setClassName_en(getRDFSLabel(classtest, ontology,"en"));
							   alcoholicBeverageClass.setComment(getRDFSComment(classtest, ontology));
							   alcoholicBeverageClass.setDefinition(getRDFSDescription(classtest, ontology));
							   alcoholicBeverageClass.setInstances(getInstances(reasoner, classtest.getIRI(), ontology));
							   if(!classtest.getIRI().getShortForm().toString().equals("Nothing"))
								   alcoholicBeverages.add(alcoholicBeverageClass);
							   
							   
							   	
					   }	
				}
			}
				if(allclasses.toStringID().indexOf("MilkAndYogurt")>0 ){
					   for(Node<OWLClass> node:reasoner.getSubClasses(allclasses, true)) {
						   for(OWLClass classtest:node.getEntities()) {
							 
							   ontologyClass alcoholicBeverageClass=new ontologyClass();
							   alcoholicBeverageClass.setIri(classtest.getIRI());
							   alcoholicBeverageClass.setClassName_ar(getRDFSLabel(classtest, ontology, "ar"));
							   alcoholicBeverageClass.setClassName_en(getRDFSLabel(classtest, ontology,"en"));
							   alcoholicBeverageClass.setComment(getRDFSComment(classtest, ontology));
							   alcoholicBeverageClass.setDefinition(getRDFSDescription(classtest, ontology));
							   alcoholicBeverageClass.setInstances(getInstances(reasoner, classtest.getIRI(), ontology));
							   if(!classtest.getIRI().getShortForm().toString().equals("Nothing"))
								   alcoholicBeverages.add(alcoholicBeverageClass);
							   
							   
							   	
					   }	
				}
			}
				
				
				
				
				
				
			}
//			System.out.println(alcoholicBeverages.toString());
			return alcoholicBeverages;
		
	}
	
public  ArrayList<ontologyClass> getFruits(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException{
	
	 ArrayList<ontologyClass> coffeeBeverages=new ArrayList<ontologyClass>();
	 
		boolean in1=false;
		boolean in2=false;
		boolean in3=false;
		boolean in4=false;
		
		for(OWLClass allclasses:ontology.getClassesInSignature()){			   
			if(allclasses.getIRI().getShortForm().toString().equals("DryFruits") ){
				ontologyClass coffeeBeverage=new ontologyClass();
				coffeeBeverage.setIri(allclasses.getIRI());
				coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
				coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
				coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				coffeeBeverages.add(coffeeBeverage);
				in1=true;
		}
			if(allclasses.getIRI().getShortForm().toString().equals("FreshFruits") ){
				ontologyClass coffeeBeverage=new ontologyClass();
				coffeeBeverage.setIri(allclasses.getIRI());
				coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
				coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
				coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				coffeeBeverages.add(coffeeBeverage);
				in2=true;
				
		}
			if(allclasses.getIRI().getShortForm().toString().equals("FruitPeel") ){
				ontologyClass coffeeBeverage=new ontologyClass();
				coffeeBeverage.setIri(allclasses.getIRI());
				coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
				coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
				coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				coffeeBeverages.add(coffeeBeverage);
				in3=true;
		}
			if(allclasses.getIRI().getShortForm().toString().equals("FruitBasedBeverages") ){
				ontologyClass coffeeBeverage=new ontologyClass();
				coffeeBeverage.setIri(allclasses.getIRI());
				coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
				coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
				coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				coffeeBeverages.add(coffeeBeverage);
				in4=true;
		}
			if(in1==true && in2==true && in3==true && in4==true)
				break;
			
		}
//		System.out.println(coffeeBeverages.toString());
		return coffeeBeverages;
	
}
	
public  ArrayList<ontologyClass> getFish(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException{
	
	 ArrayList<ontologyClass> coffeeBeverages=new ArrayList<ontologyClass>();
	
		boolean in1=false;
		boolean in2=false;
		boolean in3=false;
		boolean in4=false;
		boolean in5=false;
		boolean in6=false;
		boolean in7=false;
		for(OWLClass allclasses:ontology.getClassesInSignature()){
//			1
			if(allclasses.getIRI().getShortForm().toString().equals("FreshFish") ){
				ontologyClass coffeeBeverage=new ontologyClass();
				coffeeBeverage.setIri(allclasses.getIRI());
				coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
				coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
				coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				coffeeBeverages.add(coffeeBeverage);
				in1=true;
				   
		}
//			2
			if(allclasses.getIRI().getShortForm().toString().equals("ProcessedFish") ){
				ontologyClass coffeeBeverage=new ontologyClass();
				coffeeBeverage.setIri(allclasses.getIRI());
				coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
				coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
				coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				coffeeBeverages.add(coffeeBeverage);
				in2=true;
				   
		}
//			3
			if(allclasses.getIRI().getShortForm().toString().equals("MarinatedFish") ){
				ontologyClass coffeeBeverage=new ontologyClass();
				coffeeBeverage.setIri(allclasses.getIRI());
				coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
				coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
				coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				coffeeBeverages.add(coffeeBeverage);
				in3=true;
				   
		}
//		    4
			
			if(allclasses.getIRI().getShortForm().toString().equals("FreshMollusc") ){
				ontologyClass coffeeBeverage=new ontologyClass();
				coffeeBeverage.setIri(allclasses.getIRI());
				coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
				coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
				coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				coffeeBeverages.add(coffeeBeverage);
				in4=true;
				   
		}
//			5
			if(allclasses.getIRI().getShortForm().toString().equals("ProcessedMollusc") ){
				ontologyClass coffeeBeverage=new ontologyClass();
				coffeeBeverage.setIri(allclasses.getIRI());
				coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
				coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
				coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				coffeeBeverages.add(coffeeBeverage);
				in5=true;
				   
		}
//			6
			if(allclasses.getIRI().getShortForm().toString().equals("FreshShellfish") ){
				ontologyClass coffeeBeverage=new ontologyClass();
				coffeeBeverage.setIri(allclasses.getIRI());
				coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
				coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
				coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				coffeeBeverages.add(coffeeBeverage);
				in6=true;
				   
		}
//			7
			if(allclasses.getIRI().getShortForm().toString().equals("ProcessedShellfish") ){
				ontologyClass coffeeBeverage=new ontologyClass();
				coffeeBeverage.setIri(allclasses.getIRI());
				coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
				coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
				coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				coffeeBeverages.add(coffeeBeverage);
				in7=true;
				   
		}
			
		if(in1==true && in2==true && in3==true && in4==true && in5==true && in6==true && in7==true)
			break;
			
		}
//		System.out.println(coffeeBeverages.toString());
	return coffeeBeverages;
}	
	
public  ArrayList<ontologyClass> getEggs(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException{
	
	 ArrayList<ontologyClass> coffeeBeverages=new ArrayList<ontologyClass>();
	 
		for(OWLClass allclasses:ontology.getClassesInSignature()){
			if(allclasses.getIRI().getShortForm().toString().equals("Eggs") ){
				ontologyClass coffeeBeverage=new ontologyClass();
				coffeeBeverage.setIri(allclasses.getIRI());
				coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
				coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
				coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				coffeeBeverages.add(coffeeBeverage);
				   
		}	
		}
//		System.out.println(coffeeBeverages.toString());
		return coffeeBeverages;
	
}
public  ArrayList<ontologyClass> getCereal(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException{
	
	 ArrayList<ontologyClass> cerealExamples=new ArrayList<ontologyClass>();
	 
		for(OWLClass allclasses:ontology.getClassesInSignature()){
			if(allclasses.getIRI().getShortForm().toString().equals("FreshCereals") ){
				ontologyClass cerealClass=new ontologyClass();
				cerealClass.setIri(allclasses.getIRI());
				cerealClass.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				cerealClass.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				cerealClass.setComment(getRDFSComment(allclasses, ontology));
				cerealClass.setDefinition(getRDFSDescription(allclasses, ontology));
				cerealClass.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				cerealExamples.add(cerealClass);
				   
		}
			if(allclasses.getIRI().getShortForm().toString().equals("ProcessedCereals") ){
				ontologyClass cerealClass=new ontologyClass();
				cerealClass.setIri(allclasses.getIRI());
				cerealClass.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				cerealClass.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				cerealClass.setComment(getRDFSComment(allclasses, ontology));
				cerealClass.setDefinition(getRDFSDescription(allclasses, ontology));
				cerealClass.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				cerealExamples.add(cerealClass);
				   
		}
			if(allclasses.getIRI().getShortForm().toString().equals("Pasta") ){
				ontologyClass cerealClass=new ontologyClass();
				cerealClass.setIri(allclasses.getIRI());
				cerealClass.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				cerealClass.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				cerealClass.setComment(getRDFSComment(allclasses, ontology));
				cerealClass.setDefinition(getRDFSDescription(allclasses, ontology));
				cerealClass.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				cerealExamples.add(cerealClass);
				   
		}
			if(allclasses.getIRI().getShortForm().toString().equals("PizzaAndFocacciaBread") ){
				ontologyClass cerealClass=new ontologyClass();
				cerealClass.setIri(allclasses.getIRI());
				cerealClass.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				cerealClass.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				cerealClass.setComment(getRDFSComment(allclasses, ontology));
				cerealClass.setDefinition(getRDFSDescription(allclasses, ontology));
				cerealClass.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				cerealExamples.add(cerealClass);
				   
		}
			if(allclasses.getIRI().getShortForm().toString().equals("Rice") ){
				ontologyClass cerealClass=new ontologyClass();
				cerealClass.setIri(allclasses.getIRI());
				cerealClass.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				cerealClass.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				cerealClass.setComment(getRDFSComment(allclasses, ontology));
				cerealClass.setDefinition(getRDFSDescription(allclasses, ontology));
				cerealClass.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				cerealExamples.add(cerealClass);
				   
		}
			
		}
//		System.out.println(cerealExamples.toString());
		return cerealExamples;
	
}
	
public  ArrayList<ontologyClass> getBread(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException{
	
	 ArrayList<ontologyClass> breadExamples=new ArrayList<ontologyClass>();
	 
		for(OWLClass allclasses:ontology.getClassesInSignature()){
			if(allclasses.getIRI().getShortForm().toString().equals("SpecialBread") ){
				ontologyClass breadClass=new ontologyClass();
				breadClass.setIri(allclasses.getIRI());
				breadClass.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				breadClass.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				breadClass.setComment(getRDFSComment(allclasses, ontology));
				breadClass.setDefinition(getRDFSDescription(allclasses, ontology));
				breadClass.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				breadExamples.add(breadClass);
				   
		}
			if(allclasses.getIRI().getShortForm().toString().equals("WhiteBread") ){
				ontologyClass breadClass=new ontologyClass();
				breadClass.setIri(allclasses.getIRI());
				breadClass.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				breadClass.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				breadClass.setComment(getRDFSComment(allclasses, ontology));
				breadClass.setDefinition(getRDFSDescription(allclasses, ontology));
				breadClass.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				breadExamples.add(breadClass);
				   
		}
		}
//		System.out.println(breadExamples.toString());
		return breadExamples;
	
}
public  ArrayList<ontologyClass> getBiscuitsAndCracker(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException{
	
	 ArrayList<ontologyClass> biscuits=new ArrayList<ontologyClass>();
	 
		for(OWLClass allclasses:ontology.getClassesInSignature()){
			if(allclasses.getIRI().getShortForm().toString().equals("BiscuitsAndCrackers") ){
				ontologyClass biscuitClass=new ontologyClass();
				biscuitClass.setIri(allclasses.getIRI());
				biscuitClass.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				biscuitClass.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				biscuitClass.setComment(getRDFSComment(allclasses, ontology));
				biscuitClass.setDefinition(getRDFSDescription(allclasses, ontology));
				biscuitClass.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				biscuits.add(biscuitClass);
				   
		}	
		}
//		System.out.println(biscuits.toString());
return biscuits;	
	
}
public  ArrayList<ontologyClass> getNonAlcoholicDrinks(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException{
//	https://perkapp.fbk.eu/helis/ontology/core#NonAlcoholicBeverages
	
	 ArrayList<ontologyClass> nonAlcoholicDrinks=new ArrayList<ontologyClass>();
	 
		for(OWLClass allclasses:ontology.getClassesInSignature()){
			if(allclasses.toStringID().indexOf("NonAlcoholicBeverages")>0 ){
				   for(Node<OWLClass> node:reasoner.getSubClasses(allclasses, true)) {
					   for(OWLClass classtest:node.getEntities()) {
						   ontologyClass nonAlcoholicDrink=new ontologyClass();
						   nonAlcoholicDrink.setIri(classtest.getIRI());
						   nonAlcoholicDrink.setClassName_ar(getRDFSLabel(classtest, ontology, "ar"));
						   nonAlcoholicDrink.setClassName_en(getRDFSLabel(classtest, ontology,"en"));
						   nonAlcoholicDrink.setComment(getRDFSComment(classtest, ontology));
						   nonAlcoholicDrink.setDefinition(getRDFSDescription(classtest, ontology));
						   nonAlcoholicDrink.setInstances(getInstances(reasoner, classtest.getIRI(), ontology));
						   if(!classtest.getIRI().getShortForm().toString().equals("Nothing"))
							   nonAlcoholicDrinks.add(nonAlcoholicDrink);
						   
						   
						   	
				   }	
			}
				 
				   
		}

			if(allclasses.getIRI().getShortForm().toString().equals("FruitJuices") ) {
				ontologyClass nonAlcoholicDrink=new ontologyClass();
				nonAlcoholicDrink.setIri(allclasses.getIRI());
				nonAlcoholicDrink.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				nonAlcoholicDrink.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				nonAlcoholicDrink.setComment(getRDFSComment(allclasses, ontology));
				nonAlcoholicDrink.setDefinition(getRDFSDescription(allclasses, ontology));
				nonAlcoholicDrink.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				nonAlcoholicDrinks.add(nonAlcoholicDrink);
			
			}

			if(allclasses.getIRI().getShortForm().toString().equals("WaterBeverage") ) {
				ontologyClass nonAlcoholicDrink=new ontologyClass();
				nonAlcoholicDrink.setIri(allclasses.getIRI());
				nonAlcoholicDrink.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				nonAlcoholicDrink.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				nonAlcoholicDrink.setComment(getRDFSComment(allclasses, ontology));
				nonAlcoholicDrink.setDefinition(getRDFSDescription(allclasses, ontology));
				nonAlcoholicDrink.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				nonAlcoholicDrinks.add(nonAlcoholicDrink);
			
			}
		
		}
//		System.out.println(nonAlcoholicDrinks.toString());
		return nonAlcoholicDrinks;

	
}
public  ArrayList<ontologyClass> getCoffeeDrinks(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException{
	
	 ArrayList<ontologyClass> coffeeBeverages=new ArrayList<ontologyClass>();
	 
		for(OWLClass allclasses:ontology.getClassesInSignature()){
			if(allclasses.getIRI().getShortForm().toString().equals("CoffeeAndSubstitutes") ){
				ontologyClass coffeeBeverage=new ontologyClass();
				coffeeBeverage.setIri(allclasses.getIRI());
				coffeeBeverage.setClassName_ar(getRDFSLabel(allclasses, ontology, "ar"));
				coffeeBeverage.setClassName_en(getRDFSLabel(allclasses, ontology, "en"));
				coffeeBeverage.setComment(getRDFSComment(allclasses, ontology));
				coffeeBeverage.setDefinition(getRDFSDescription(allclasses, ontology));
				coffeeBeverage.setInstances(getInstances(reasoner, allclasses.getIRI(), ontology));
				coffeeBeverages.add(coffeeBeverage);
				   
		}	
		}
//		System.out.println(coffeeBeverages.toString());

		return coffeeBeverages;
}
public  ArrayList<ontologyClass> getAlcoholicBeverages(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException{
	
	 ArrayList<ontologyClass> alcoholicBeverages=new ArrayList<ontologyClass>();
	 
		for(OWLClass allclasses:ontology.getClassesInSignature()){
			if(allclasses.toStringID().indexOf("AlcoholicBeverages")>0 ){
				   for(Node<OWLClass> node:reasoner.getSubClasses(allclasses, true)) {
					   for(OWLClass classtest:node.getEntities()) {
						   ontologyClass alcoholicBeverageClass=new ontologyClass();
						   alcoholicBeverageClass.setIri(classtest.getIRI());
						   alcoholicBeverageClass.setClassName_ar(getRDFSLabel(classtest, ontology, "ar"));
						   alcoholicBeverageClass.setClassName_en(getRDFSLabel(classtest, ontology,"en"));
						   alcoholicBeverageClass.setComment(getRDFSComment(classtest, ontology));
						   alcoholicBeverageClass.setDefinition(getRDFSDescription(classtest, ontology));
						   alcoholicBeverageClass.setInstances(getInstances(reasoner, classtest.getIRI(), ontology));
						   if(!classtest.getIRI().getShortForm().toString().equals("Nothing"))
							   alcoholicBeverages.add(alcoholicBeverageClass);
						   
						   
						   	
				   }	
			}
		}	
		}
//		System.out.println(alcoholicBeverages.toString());
		return alcoholicBeverages;
	
}
public  ArrayList<ontologyClass> getConsumptionBehavior(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException {
	
	 ArrayList<ontologyClass> consumptionBehaviors=new ArrayList<ontologyClass>();
	 
		for(OWLClass allclasses:ontology.getClassesInSignature()){
			if(allclasses.toStringID().indexOf("consumption_behavior")>0 ){
				   for(Node<OWLClass> node:reasoner.getSubClasses(allclasses, false)) {
					   for(OWLClass classtest:node.getEntities()) {
						   ontologyClass consumptionBehavior=new ontologyClass();
						   consumptionBehavior.setIri(classtest.getIRI());
						   consumptionBehavior.setClassName_ar(getRDFSLabel(classtest, ontology, "ar"));
						   consumptionBehavior.setClassName_en(getRDFSLabel(classtest, ontology,"en"));
						   consumptionBehavior.setComment(getRDFSComment(classtest, ontology));
						   consumptionBehavior.setDefinition(getRDFSDescription(classtest, ontology));
						   if(!classtest.getIRI().getShortForm().toString().equals("Nothing"))
							   consumptionBehaviors.add(consumptionBehavior);
						   
						   
						   	
				   }	
			}
				   
		}	
		}
//		System.out.println(consumptionBehaviors.toString());
		return consumptionBehaviors;

	
}
public  ArrayList<ontologyClass> getEntertainmentBehavior(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException {
	 
	 ArrayList<ontologyClass> entertainmentBehaviors=new ArrayList<ontologyClass>();
	 
		for(OWLClass allclasses:ontology.getClassesInSignature()){
			if(allclasses.toStringID().indexOf("entertainment_behavior")>0 ){
				   for(Node<OWLClass> node:reasoner.getSubClasses(allclasses, false)) {
					   for(OWLClass classtest:node.getEntities()) {
						   ontologyClass entertainmentBehavior=new ontologyClass();
						   entertainmentBehavior.setIri(classtest.getIRI());
						   entertainmentBehavior.setClassName_ar(getRDFSLabel(classtest, ontology, "ar"));
						   entertainmentBehavior.setClassName_en(getRDFSLabel(classtest, ontology,"en"));
						   entertainmentBehavior.setComment(getRDFSComment(classtest, ontology));
						   entertainmentBehavior.setDefinition(getRDFSDescription(classtest, ontology));
						   if(!classtest.getIRI().getShortForm().toString().equals("Nothing"))
							   entertainmentBehaviors.add(entertainmentBehavior);
						   
						   
						   	
				   }	
			}
				   
		}
			
		}
//		System.out.println(entertainmentBehaviors.toString());
		return entertainmentBehaviors;


	
	
}
public  ArrayList<ontologyClass> getSleepBehavior(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException {
	
	 ArrayList<ontologyClass> sleepBehaviors=new ArrayList<ontologyClass>();
	 
		for(OWLClass allclasses:ontology.getClassesInSignature()){
			if(allclasses.toStringID().indexOf("sleep_behaviors")>0 ){
				   for(Node<OWLClass> node:reasoner.getSubClasses(allclasses, false)) {
					   for(OWLClass classtest:node.getEntities()) {
						   ontologyClass sleepBehavior=new ontologyClass();
						   sleepBehavior.setIri(classtest.getIRI());
						   sleepBehavior.setClassName_ar(getRDFSLabel(classtest, ontology, "ar"));
						   sleepBehavior.setClassName_en(getRDFSLabel(classtest, ontology,"en"));
						   sleepBehavior.setComment(getRDFSComment(classtest, ontology));
						   sleepBehavior.setDefinition(getRDFSDescription(classtest, ontology));
						   if(!classtest.getIRI().getShortForm().toString().equals("Nothing"))
							   sleepBehaviors.add(sleepBehavior);
						   
						   
						   	
				   }	
			}
				   break;
		}	
		}
//		System.out.println(sleepBehaviors.toString());
		
        return sleepBehaviors;

	
	
}
public  ArrayList<ontologyClass> getPersonality(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException {

	 ArrayList<ontologyClass> emotions=new ArrayList<ontologyClass>();
		for(OWLClass allclasses:ontology.getClassesInSignature()){
			if(allclasses.toStringID().indexOf("Human")>0 ){
				   for(Node<OWLClass> node:reasoner.getSubClasses(allclasses, false)) {
					   for(OWLClass classtest:node.getEntities()) {
						   ontologyClass emotionClass=new ontologyClass();
						   emotionClass.setIri(classtest.getIRI());
						   emotionClass.setClassName_ar(getRDFSLabel(classtest, ontology, "ar"));
						   emotionClass.setClassName_en(getRDFSLabel(classtest, ontology,"en"));
						   emotionClass.setComment(getRDFSComment(classtest, ontology));
						   emotionClass.setDefinition(getRDFSDescription(classtest, ontology));
						   if(!classtest.getIRI().getShortForm().toString().equals("Nothing"))
						   emotions.add(emotionClass);
						   
						   
						   	
				   }	
			}
		}		
			
		}
//		System.out.println(emotions.toString());
		return emotions;
		
	
}

public  ArrayList<ontologyClass> getActivity(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException {
	 
	 ArrayList<ontologyClass> activities=new ArrayList<ontologyClass>();
	 
		for(OWLClass allclasses:ontology.getClassesInSignature()){
			if(allclasses.toStringID().indexOf("Activity")>0 ){
				   for(Node<OWLClass> node:reasoner.getSubClasses(allclasses, true)) {
					   for(OWLClass classtest:node.getEntities()) {
						   ontologyClass activityClass=new ontologyClass();
						   activityClass.setIri(classtest.getIRI());
						   activityClass.setClassName_ar(getRDFSLabel(classtest, ontology, "ar"));
						   activityClass.setClassName_en(getRDFSLabel(classtest, ontology,"en"));
						   activityClass.setComment(getRDFSComment(classtest, ontology));
						   activityClass.setDefinition(getRDFSDescription(classtest, ontology));
						   activityClass.setInstances(getInstances(reasoner, classtest.getIRI(), ontology));
						   if(!classtest.getIRI().getShortForm().toString().equals("Nothing"))
						    activities.add(activityClass);
						   
						   
						   	
				   }	
			}
		}	
		}
//		System.out.println(activities.toString());
		
return activities;
	
	
	
}	
public  ArrayList<ontologyClass> getEmotionProcess(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException {

		 ArrayList<ontologyClass> emotions=new ArrayList<ontologyClass>();
			for(OWLClass allclasses:ontology.getClassesInSignature()){
				if(allclasses.toStringID().indexOf("MFOEM_000001")>0 ){
					   for(Node<OWLClass> node:reasoner.getSubClasses(allclasses, true)) {
						   for(OWLClass classtest:node.getEntities()) {
							   ontologyClass emotionClass=new ontologyClass();
							   emotionClass.setIri(classtest.getIRI());
							   emotionClass.setClassName_ar(getRDFSLabel(classtest, ontology, "ar"));
							   emotionClass.setClassName_en(getRDFSLabel(classtest, ontology,"en"));
							   emotionClass.setComment(getRDFSComment(classtest, ontology));
							   emotionClass.setDefinition(getRDFSDescription(classtest, ontology));
							   if(!classtest.getIRI().getShortForm().toString().equals("Nothing"))
							   emotions.add(emotionClass);
							   
							   
							   	
					   }	
				}
			}		
				
			}
//			System.out.println(emotions.toString());
			return emotions;
			
		
	}

public ArrayList<ontologyClass> getSleepDisorder(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException{
	ArrayList<ontologyClass> sleepDisorder=new ArrayList<ontologyClass>();
	for(OWLClass allclasses:ontology.getClassesInSignature()){
		if(allclasses.toStringID().indexOf("DOID_535")>0 || allclasses.toStringID().indexOf("MFOMD_0000024")>0){
			   for(Node<OWLClass> node:reasoner.getSubClasses(allclasses, false)) {
				   for(OWLClass classtest:node.getEntities()) {
					   ontologyClass sleepIssueClass=new ontologyClass();
					   sleepIssueClass.setIri(classtest.getIRI());
					   sleepIssueClass.setClassName_ar(getRDFSLabel(classtest, ontology, "ar"));
					   sleepIssueClass.setClassName_en(getRDFSLabel(classtest, ontology,"en"));
					   sleepIssueClass.setComment(getRDFSComment(classtest, ontology));
					   sleepIssueClass.setDefinition(getRDFSDescription(classtest, ontology));
					   if(!classtest.getIRI().getShortForm().toString().equals("Nothing"))
					   sleepDisorder.add(sleepIssueClass);
					   
					   
					   	
			   }	
		}
	}		
		
	}
	return sleepDisorder;
}
public  ArrayList<ontologyClass> getBodilyProcess(OWLOntology ontology,OWLReasoner reasoner) throws OWLOntologyCreationException {
	 ArrayList<ontologyClass> emotions=new ArrayList<ontologyClass>();
		for(OWLClass allclasses:ontology.getClassesInSignature()){
			if(allclasses.toStringID().indexOf("MFOEM_000202")>0 ){
				   for(Node<OWLClass> node:reasoner.getSubClasses(allclasses, false)) {
					   for(OWLClass classtest:node.getEntities()) {
						   ontologyClass emotionClass=new ontologyClass();
						   emotionClass.setIri(classtest.getIRI());
						   emotionClass.setClassName_ar(getRDFSLabel(classtest, ontology, "ar"));
						   emotionClass.setClassName_en(getRDFSLabel(classtest, ontology,"en"));
						   emotionClass.setComment(getRDFSComment(classtest, ontology));
						   emotionClass.setDefinition(getRDFSDescription(classtest, ontology));
						   if(!classtest.getIRI().getShortForm().toString().equals("Nothing"))
						   emotions.add(emotionClass);
						   
						   
						   	
				   }	
			}
		}		
			
		}
//		System.out.println(emotions.toString());
		return emotions;
		
	
}

	
	public  String getRDFSLabel(OWLEntity owlclass,OWLOntology owlontology,String lang) {
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		ArrayList<String> oneLabel=new ArrayList<String>();
		OWLDataFactory dataFactory = man.getOWLDataFactory();
			EntitySearcher.getAnnotations(owlclass, owlontology, dataFactory.getRDFSLabel()).forEach(annotation -> {
				
	        OWLLiteral literal = (OWLLiteral) annotation.getValue();
	        if(literal.hasLang(lang))
	        oneLabel.add(literal.getLiteral());
	        
	    });
		if(oneLabel.size()>0)
		    return oneLabel.get(0);
		else
			return "none";
	}
	public  String getRDFSComment(OWLEntity owlclass,OWLOntology owlontology) {
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		ArrayList<String> oneLabel=new ArrayList<String>();
		OWLDataFactory dataFactory = man.getOWLDataFactory();
			EntitySearcher.getAnnotations(owlclass, owlontology, dataFactory.getRDFSComment()).forEach(annotation -> {			                    
	        OWLLiteral literal = (OWLLiteral) annotation.getValue();
	        oneLabel.add(literal.getLiteral());
	        
	    });
		if(oneLabel.size()>0)
		    return oneLabel.get(0);
		else
			return "none";
		
	}
	public  String getRDFSDescription(OWLEntity owlclass,OWLOntology owlontology) {
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		ArrayList<String> oneLabel=new ArrayList<String>();
		OWLDataFactory dataFactory = man.getOWLDataFactory();
			EntitySearcher.getAnnotations(owlclass, owlontology, dataFactory.getRDFSIsDefinedBy()).forEach(annotation -> {			                    
	        OWLLiteral literal = (OWLLiteral) annotation.getValue();
	        oneLabel.add(literal.getLiteral());
	        
	    });
		if(oneLabel.size()>0)
		    return oneLabel.get(0);
		else
			return "none";
	} 


public  ArrayList<ontologyClass> getInstances(OWLReasoner reasoner,IRI owlIRI,OWLOntology ontology) {
	OWLOntologyManager man = OWLManager.createOWLOntologyManager();
	OWLDataFactory dataFactory = man.getOWLDataFactory();
	
	OWLClass country = dataFactory.getOWLClass(IRI
            .create(owlIRI.toString()));
    // Ask the reasoner for the instances of pet
    NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(country,
            true);
    // The reasoner returns a NodeSet again. This time the NodeSet contains
    // individuals. Again, we just want the individuals, so get a flattened
    // set.
    Set<OWLNamedIndividual> individuals = individualsNodeSet.getFlattened();
//    System.out.println("Instances of pet: "+ owlIRI.getShortForm());
    ArrayList<ontologyClass> classIndividuals=new ArrayList<ontologyClass>();
    for (OWLNamedIndividual ind : individuals) {
    	ontologyClass ont=new ontologyClass();
    	ont.setIri(ind.getIRI());
    	ont.setClassName_en(getRDFSLabel(ind, ontology, "en"));
    	ont.setClassName_ar(getRDFSLabel(ind, ontology, "ar"));
    	ont.setComment(getRDFSComment(ind, ontology));
    	ont.setDefinition(getRDFSDescription(ind, ontology));
    	classIndividuals.add(ont);
    	
    }
//    System.out.println(classIndividuals.toString());
//    System.out.println("\n");
    return classIndividuals;
}

public OWLOntology loadOntology() throws OWLOntologyCreationException {
	String x = "C:\\Users\\user\\Desktop\\Ontologies\\V11RulesforFacets.owl";
//	https://perkapp.fbk.eu/helis/ontology/core#CoffeeAndSubstitutes
//	 IRI iri = IRI.create(x);
	
	 OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	 IRI pizzaontology = IRI.create("https://raw.githubusercontent.com/nadaelarabyy/Character/master/V11RulesforFacets.owl");
		OWLOntology ontology=manager.loadOntology(pizzaontology);
//	 OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File(x));
	 System.out.println("Loaded: " + ontology.getOntologyID());
	 return ontology;
}
public OWLReasoner useReasoner(OWLOntology ontology) {
	OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
	ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor();
	OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
	OWLReasoner reasoner = reasonerFactory.createReasoner(ontology, config);
	
	return reasoner;
}

}
