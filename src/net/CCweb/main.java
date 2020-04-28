package net.CCweb;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;

import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class main
 */
public class main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email=request.getParameter("email");
		String gender=request.getParameter("gender");
		String age=request.getParameter("age");
		String[] food=request.getParameterValues("food");
		String[] activity=request.getParameterValues("activity");
		String[] emotion=request.getParameterValues("emotion");
		String[] behavior=request.getParameterValues("behavior");
		String[]sleepDisorder=request.getParameterValues("sleepDisorder");
		String[] opinion=request.getParameterValues("opinion");
		String suggestion=request.getParameter("suggestion");
		boolean submitButtonPressed = request.getParameter("submit") != null;
		if(submitButtonPressed) {
			System.out.println("email: "+email);
			System.out.println("gender: "+gender);
			System.out.println("age: "+age);
			
			if(food !=null) {
			System.out.println("food: "+Arrays.toString(getFoodNames(food)));
			}
			if(activity!=null) {
			System.out.println("activity: "+Arrays.toString(getActivityNames(activity)));
			}
			if(emotion!=null) {
			System.out.println("emotion: "+Arrays.toString(getEmotionNames(emotion)));
			}
			if(behavior!=null) {
			System.out.println("behavior: "+Arrays.toString(getBehaviorNames(behavior)));
			}
			if(sleepDisorder!=null) {
				System.out.println("sleepDisorder: "+Arrays.toString(getSleepDisoderNames(sleepDisorder)));
				}
			
			System.out.println("opinion: "+Arrays.toString(opinion));
			System.out.println("suggestion: "+suggestion);
			System.out.println("---------------------------------------------------------");
			if(food!=null)
			System.out.println("food query: "+consumeQuery(getFoodIri(food)));
			if(activity!=null)
			System.out.println("activity query: "+hasActivityQuery(getActivityIri(activity)));
			if(emotion!=null)
			System.out.println("emotion query: "+hasEmotionQuery(getEmotionIri(emotion)));
			if(behavior!=null)
			System.out.println("behavior query: "+hasBehaviorQuery(getBehaviorIri(behavior)));
			if(sleepDisorder!=null)
				System.out.println("behavior query: "+hasSleepDisorderQuery(getSleepDisoderIri(sleepDisorder)));
			
			
//		----------------------------------------------------------------------------------------------------------
			String x = "C:\\Users\\user\\Desktop\\Ontologies\\V11RulesforFacets.owl";

			 OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
			 OWLOntology ontology = null;
			try {
//				ontology = manager.loadOntologyFromOntologyDocument(new File(x));
				IRI pizzaontology = IRI.create("https://raw.githubusercontent.com/nadaelarabyy/Character/master/V11RulesforFacets.owl");
				ontology=manager.loadOntology(pizzaontology);
			} catch (OWLOntologyCreationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
//		            doQueryLoop(dlQueryPrinter);
		            String expression="Human";
		            if(food!=null)
		            	expression=expression+" and "+consumeQuery(getFoodIri(food));
		            if(activity!=null)
		            	expression=expression+" and "+hasActivityQuery(getActivityIri(activity));
		            if(emotion!=null)
		            	expression=expression+" and "+hasEmotionQuery(getEmotionIri(emotion));
		            if(behavior!=null)
		            	expression=expression+" and "+hasBehaviorQuery(getBehaviorIri(behavior));
		            if(sleepDisorder!=null)
		            	expression=expression+" and "+hasSleepDisorderQuery(getSleepDisoderIri(sleepDisorder));
		            ArrayList<ontologyClass> finalResult=new ArrayList<ontologyClass>();
		            
		            ArrayList<ontologyClass> value=dlQueryPrinter.askQuery(expression);
		            ArrayList<ontologyClass>personalityList=(ArrayList<ontologyClass>) request.getSession().getAttribute("persoanlityList");
		            for(int i=0;value.size()>i;i++) {
		            	try {
							if(containPersonality(personalityList,value.get(i))) {
								finalResult.add(value.get(i));
							}
						} catch (OWLOntologyCreationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		            
		            if(age!=null || !age.equals(""))
		            {
		            	String name="Human and hasAge value "+Integer.parseInt(age);
		            	ArrayList<ontologyClass> value2=dlQueryPrinter.askQuery(name);
		            	for(int i=0;value2.size()>i;i++) {
		            		try {
								if(!chechContain(value, value2.get(i))&&containPersonality(personalityList,value2.get(i))) {
								value.add(value2.get(i));
								finalResult.add(value2.get(i));
								}
							} catch (OWLOntologyCreationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		            	}
		            	
		            }
		            Document person=new Document();
			         MongoClient mongoClient = MongoClients.create(
						        "mongodb+srv://nada:luck1997@cluster0-wcdyb.mongodb.net/sleepFeedback?retryWrites=true&w=majority"
						);
				     MongoDatabase database = mongoClient.getDatabase("wellnessFeedback");


				    MongoCollection<Document> collection=database.getCollection("sleepWellness");

			//----------------------------------------------------------------------------------------------------
				    
				    if(email!=null)
				        person.put("email", email);
				    if(gender!=null)
				        person.put("gender", gender);
				    if(age!=null)
				        person.put("age", age);
				    if(food!=null)
				        person.put("food",Arrays.asList(getFoodNames(food)));
				    if(activity!=null)
				        person.put("activity",Arrays.asList(getActivityNames(activity)));
				    if(behavior!=null)
				        person.put("behavior",Arrays.asList(getBehaviorNames(behavior)));
				    if(emotion!=null)
				        person.put("emotion",Arrays.asList(getEmotionNames(emotion)));
				    if(sleepDisorder!=null)
				        person.put("sleepDisorder",Arrays.asList(getSleepDisoderNames(sleepDisorder)));
				    if(opinion!=null)
				        person.put("opinion",Arrays.asList(opinion));
				    if(suggestion!=null)
				        person.put("suggestion",suggestion);
		     if(finalResult.size()<personalityList.size()) {       
			     request.getSession().setAttribute("character", finalResult);
			     String[] characterArray=new String[finalResult.size()];
			     for(int i=0;finalResult.size()>i;i++) {
			    	 characterArray[i]=finalResult.get(i).getClassName_en();
			     }

			    if(characterArray!=null)
			    	person.put("characterInference",Arrays.asList(characterArray));
			    person.put("personalityRating","");
			
		//----------------------------------------------------------------------------------------------------	

			collection.insertOne(person);
			ObjectId id = (ObjectId)person.get( "_id" );
			System.out.println("id----------: "+id.toString());
			request.getSession().setAttribute("_id", id);
			
			
		     }
		     else {
		    	 
		    	 ArrayList<ontologyClass> test=new ArrayList<ontologyClass>();
		    	 ontologyClass xTest=new ontologyClass();
		    	 xTest.setClassName_en("we could not define your personality based on your input");
		    	 xTest.setClassName_ar("لم نستطع التبين من صفاتك بناءا علي بياناتك");
		    	 test.add(xTest);
		    	 String[] array= {"we could not define your personality based on your input"};
		    	 person.put("characterInference",Arrays.asList(array));
		    	 person.put("personalityRating","");
		    	 request.getSession().setAttribute("character", test);
		    	    collection.insertOne(person);
					ObjectId id = (ObjectId)person.get( "_id" );
					System.out.println("id----------: "+id.toString());
					request.getSession().setAttribute("_id", id);
		    	 
		     }
			response.sendRedirect(request.getContextPath() + "/nextPage");
			
			
			
		}
		
	}
	public static boolean chechContain(ArrayList<ontologyClass> list , ontologyClass example) {
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
	public static boolean containPersonality(ArrayList<ontologyClass>personalities ,ontologyClass example) throws OWLOntologyCreationException 
	{
		boolean flag=false;
		for(int i=0;personalities.size()>i;i++) {
			if(personalities.get(i).getIri().toString().equals(example.getIri().toString())) {
				flag=true;
				break;
			}
		}
		
		
		return flag;
	}
public static String consumeQuery(String [] food) {
	String query="consume value "+food[0];
	for(int i=1;food.length>i;i++) {
		query=query+" and "+"consume value "+food[i];
		
	}
	
	return query;
}
public static String hasActivityQuery(String[] activity) {
	String query="hasActivity value "+activity[0];
	for(int i=1;activity.length>i;i++) {
		query=query+" and "+"hasActivity value "+activity[i];
	}
	
	return query;
}
public static String hasEmotionQuery(String[] emotion) {
	String query="hasEmotion some "+emotion[0];
	for(int i=1;emotion.length>i;i++) {
		query=query+" and "+"hasEmotion some "+emotion[i];
	}
	
	return query;
}
public static String hasBehaviorQuery(String[] behavior) {
	String query="hasBehavior some "+behavior[0];
	for(int i=1;behavior.length>i;i++) {
		query=query+" and "+"hasBehavior some "+behavior[i];
	}
	
	return query;
}

public static String hasSleepDisorderQuery(String[] sleepDisorder) {
	String query="sufferFrom some "+sleepDisorder[0];
	for(int i=1;sleepDisorder.length>i;i++) {
		query=query+" and "+"sufferFrom some "+sleepDisorder[i];
	}
	
	return query;
}


public static String[] getFoodIri(String[] food) {
	String[] foodIri=new String[food.length];
	for(int i=0;food.length>i;i++) {
		String[] temp=food[i].split(Pattern.quote("$"));
		foodIri[i]=temp[1];
	}
	return foodIri;
}

	public static String[] getFoodNames(String[] food) {
		String[] foodName=new String[food.length];
		for(int i=0;food.length>i;i++) {
			String[] temp=food[i].split(Pattern.quote("$"));
			foodName[i]=temp[0];
			
			
		}
		return foodName;
	}
	public static String[] getActivityIri(String[] activity) {
		String[] ActivityIri=new String[activity.length];
		for(int i=0;activity.length>i;i++) {
			String[] temp=activity[i].split(Pattern.quote("$"));
			ActivityIri[i]=temp[1];
		}
		return ActivityIri;
	}
	public static String[] getActivityNames(String[] activity) {
		String[] activityName=new String[activity.length];
		for(int i=0;activity.length>i;i++) {
			String[] temp=activity[i].split(Pattern.quote("$"));
			activityName[i]=temp[0];
		}
		return activityName;
	}
	public static String[] getEmotionIri(String[] emotion) {
		String[] EmotionIri=new String[emotion.length];
		for(int i=0;emotion.length>i;i++) {
			String[] temp=emotion[i].split(Pattern.quote("$"));
			EmotionIri[i]=temp[1];
		}
		return EmotionIri;
	}
	public static String[] getEmotionNames(String[] emotion) {
		String[] emotionName=new String[emotion.length];
		for(int i=0;emotion.length>i;i++) {
			String[] temp=emotion[i].split(Pattern.quote("$"));
			emotionName[i]=temp[0];
			
		}
		return emotionName;
	}
	public static String[] getBehaviorIri(String[] behavior) {
		String[] behaviorIri=new String[behavior.length];
		for(int i=0;behavior.length>i;i++) {
			String[] temp=behavior[i].split(Pattern.quote("$"));
			behaviorIri[i]=temp[1];
		}
		return behaviorIri;
	}
	public static String[] getBehaviorNames(String[] behavior) {
		
		String[] behaviorName=new String[behavior.length];
		for(int i=0;behavior.length>i;i++) {
			String[] temp=behavior[i].split(Pattern.quote("$"));
			behaviorName[i]=temp[0];
			
		}
		return behaviorName;
	}
	
	public static String[] getSleepDisoderIri(String[] sleepDisoder) {
		String[] sleepDisoderIri=new String[sleepDisoder.length];
		for(int i=0;sleepDisoder.length>i;i++) {
			String[] temp=sleepDisoder[i].split(Pattern.quote("$"));
			sleepDisoderIri[i]=temp[1];
		}
		return sleepDisoderIri;
	}
	public static String[] getSleepDisoderNames(String[] sleepDisoder) {
		String[] SDName=new String[sleepDisoder.length];
		for(int i=0;sleepDisoder.length>i;i++) {
			String[] temp=sleepDisoder[i].split(Pattern.quote("$"));
			SDName[i]=temp[0];
			
		}
		return SDName;
	}

}
