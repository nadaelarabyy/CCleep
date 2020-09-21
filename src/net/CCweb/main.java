package net.CCweb;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.lucene.search.ReqExclScorer;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.simple.parser.ParseException;
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
		String consent=request.getParameter("vehicle1");
		String suggestion=request.getParameter("suggestion");
		boolean submitButtonPressed = request.getParameter("submit") != null;
		if(submitButtonPressed) {
			System.out.println("email: "+email);
			System.out.println("gender: "+gender);
			System.out.println("age: "+age);
			ArrayList<String> foodArr=new ArrayList<String>();
			ArrayList<String> activityArr=new ArrayList<String>();
			ArrayList<String> emotionArr=new ArrayList<String>();
			ArrayList<String> behavArr=new ArrayList<String>();
			if(food !=null) {
			String[] fooIRIs=getFoodIri(food);
			for(int i=0;fooIRIs.length>i;i++) {
				foodArr.add(fooIRIs[i]);
			}
			System.out.println("food: "+Arrays.toString(getFoodNames(food)));
			}
			if(activity!=null) {
				String[] ActIRIs=getActivityIri(activity);
				for(int i=0;ActIRIs.length>i;i++) {
					activityArr.add(ActIRIs[i]);
				}
			System.out.println("activity: "+Arrays.toString(getActivityNames(activity)));
			}
			if(emotion!=null) {
				String[] ActIRIs=getEmotionIri(emotion);
				for(int i=0;ActIRIs.length>i;i++) {
					emotionArr.add(ActIRIs[i]);
				}
			System.out.println("emotion: "+Arrays.toString(getEmotionNames(emotion)));
			}
			if(behavior!=null) {
				String[] ActIRIs=getBehaviorIri(behavior);
				for(int i=0;ActIRIs.length>i;i++) {
					behavArr.add(ActIRIs[i]);
				}
			System.out.println("behavior: "+Arrays.toString(getBehaviorNames(behavior)));
			}
			if(sleepDisorder!=null) {
				System.out.println("sleepDisorder: "+Arrays.toString(getSleepDisoderNames(sleepDisorder)));
				}
			  onotogyManager manager=new onotogyManager();
			   OWLOntology ontology=null;
			try {
				ontology = manager.loadOntology();
			} catch (OWLOntologyCreationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   OWLReasoner reasoner=manager.useReasoner(ontology);
			   ArrayList<sleepClass> foodAct = null;
			   ArrayList<sleepClass> behavEmo=null;
			try {
				foodAct = manager.getActivityFoodNums(ontology, reasoner, foodArr, activityArr);
				behavEmo=manager.getBehaviorEmotinsNums(ontology, behavArr, emotionArr);
			} catch (OWLOntologyCreationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("opinion: "+Arrays.toString(opinion));
			System.out.println("suggestion: "+suggestion);
			System.out.println("---------------------------------------------------------");

//			ArrayList<sleepClass> behavEmo=(ArrayList<sleepClass>) request.getSession().getAttribute("behaviorEmotion");
//            ArrayList<sleepClass> foodAct=(ArrayList<sleepClass>) request.getSession().getAttribute("foodActivity");
			String[] foodIRI=null;
			String[] ActivityIRI=null;
			String[] behaviorIRI=null;
			double calories=0;
			double metValues=0;
			int sleepHygieneScore=0;
			int sleepQualityScore=0;
			if(food!=null) {
				foodIRI=getFoodIri(food);
				calories=getCalories(foodIRI, foodAct);
			}
			if(activity!=null) {
				ActivityIRI=getActivityIri(activity);
				metValues=getMETValues(ActivityIRI, foodAct);
				
			}
			if(behavior!=null) {
				behaviorIRI=getBehaviorIri(behavior);
				String[] x=getHygieneScores(behaviorIRI, behavEmo).split(Pattern.quote("$"));
				sleepHygieneScore=Integer.parseInt(x[0]);
				sleepQualityScore=Integer.parseInt(x[1]);
			
			}
			
              String expression="Human and consume some ( Food and ( amountCalories some xsd:double[>= "+calories+" ]) and ( amountCalories some xsd:double[<= "+calories+" ] )) and hasActivity some ( Activity and (hasMETValue some xsd:double[>= "+metValues+" ]) and ( hasMETValue " + "some xsd:double[<= "+metValues+" ])) and hasTaskScore some ( TaskScore and (ofTask value sleepHygieneTask ) and ( hasScore value "+sleepHygieneScore+")) and hasTaskScore some ( TaskScore and (ofTask value sleepQualityTask ) and ( hasScore value "+sleepQualityScore+"))";          	
//            int[] score=getScores(foodIRI, activityIRI, behaviorIRI, emotionIRI, behavEmo, foodAct);			
//			String expression="Human and (hasTaskScore some(TaskScore and ofTask value sleepQualityTask and hasScore value "+score[1]+")) and (hasTaskScore some (TaskScore and ofTask value sleepHygieneTask and hasScore value "+score[0]+"))";

//            if(sleepDisorder!=null)
//            	expression=expression+" and "+hasSleepDisorderQuery(getSleepDisoderIri(sleepDisorder));
            
            request.getSession().setAttribute("characterExpression", expression);
            //		----------------------------------------------------------------------------------------------------------
			String x = "C:\\Users\\user\\Desktop\\Ontologies\\V11RulesforFacets.owl";


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
				    if(age!=null) {
				        person.put("age", age);
				        request.getSession().setAttribute("humanAge", age);
				    }
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

				person.put("consent",consent); 
			    person.put("personalityRating","");
			    person.put("characterInference","");
			    person.put("personalityOfChoice","");
			    person.put("BFI-Q1","");
			    person.put("BFI-Q2","");
			    person.put("BFI-Q3","");
			    person.put("BFI-Q4","");
			    person.put("BFI-Q5","");
			    person.put("BFI-Q6","");
			    person.put("BFI-Q7","");
			    person.put("BFI-Q8","");
			    person.put("BFI-Q9","");
			    person.put("BFI-Q10","");
			
		//----------------------------------------------------------------------------------------------------	
			collection.insertOne(person);
			ObjectId id = (ObjectId)person.get( "_id" );
			System.out.println("id----------: "+id.toString());
			request.getSession().setAttribute("_id", id);
			request.getSession().setAttribute("Expression", expression);

			
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
			foodName[i]=temp[0].replace("_", " ");
			
			
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
			activityName[i]=temp[0].replace("_", " ");
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
			emotionName[i]=temp[0].replace("_", " ");
			
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
			behaviorName[i]=temp[0].replace("_", " ");
			
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
			SDName[i]=temp[0].replace("_", " ");
			
		}
		return SDName;
	}
	
	
	public static String getHygieneScores(String[] behavior , ArrayList<sleepClass>behavEmo) {
		String v="";
		int sleepHygieneScore=0;
		int sleepQualityScore=0;
		for(int i=0;behavior.length>i;i++) {
			for(int j=0;behavEmo.size()>j;j++) {
				if(behavEmo.get(j).getClassName().getShortForm().toString().equals(behavior[i])) {
					sleepHygieneScore+=behavEmo.get(j).getSleepHygieneScore();
					sleepQualityScore+=behavEmo.get(j).getSleepQualityScore();
					break;
				}
			}
		}
		v+=sleepHygieneScore+"$"+sleepQualityScore;
		return v;
	}
	
	
	public static double getCalories(String [] food,ArrayList<sleepClass>foodAct ) {
		double calories=0;
		for(int i=0;food.length>i;i++) {
			for(int j=0;foodAct.size()>j;j++) {
				if(foodAct.get(j).getClassName().getShortForm().toString().equals(food[i])) {
					calories+=foodAct.get(j).getCalories();
				}
			}
		}
		return calories;
	}
	public static double getMETValues(String [] activity,ArrayList<sleepClass>foodAct ) {
		double METV=0;
		for(int i=0;activity.length>i;i++) {
			for(int j=0;foodAct.size()>j;j++) {
				if(foodAct.get(j).getClassName().getShortForm().toString().equals(activity[i])) {
					METV+=foodAct.get(j).getMETValues();
				}
			}
		}
		return METV;
	}
	
	public static int[] getScores(String[] food, String[] activity,String[] behavior,String[] emotion,ArrayList<sleepClass>behavEmo,ArrayList<sleepClass>foodAct) {
		int[] score=new int[2];
		int sleepHygieneScore=0;
		int sleepQualityScore=0;
		if(food!=null) {
		for(int i=0;food.length>i;i++) {
			for(int j=0;foodAct.size()>j;j++) {
				if(foodAct.get(j).getClassName().getShortForm().toString().equals(food[i])) {
					sleepHygieneScore+=foodAct.get(j).getSleepHygieneScore();
					sleepQualityScore+=foodAct.get(j).getSleepQualityScore();
					break;
				}
			}
		}
	}
		if(activity!=null){
		for(int i=0;activity.length>i;i++) {
			for(int j=0;foodAct.size()>j;j++) {
				if(foodAct.get(j).getClassName().getShortForm().toString().equals(activity[i])) {
					sleepHygieneScore+=foodAct.get(j).getSleepHygieneScore();
					sleepQualityScore+=foodAct.get(j).getSleepQualityScore();
					break;
				}
			}
		}
	}
		if(behavior!=null) {
		for(int i=0;behavior.length>i;i++) {
			for(int j=0;behavEmo.size()>j;j++) {
				if(behavEmo.get(j).getClassName().getShortForm().toString().equals(behavior[i])) {
					sleepHygieneScore+=behavEmo.get(j).getSleepHygieneScore();
					sleepQualityScore+=behavEmo.get(j).getSleepQualityScore();
					break;
				}
			}
		}
	}
		if(emotion!=null) {
		for(int i=0;emotion.length>i;i++) {
			for(int j=0;behavEmo.size()>j;j++) {
				if(behavEmo.get(j).getClassName().getShortForm().toString().equals(emotion[i])) {
					sleepHygieneScore+=behavEmo.get(j).getSleepHygieneScore();
					sleepQualityScore+=behavEmo.get(j).getSleepQualityScore();
					break;
				}
			}
		}
	}
		score[0]=sleepHygieneScore;
		score[1]=sleepQualityScore;
		System.out.println("sleepHygieneScore: "+sleepHygieneScore+" sleepQualityScore: "+sleepQualityScore);
		return score;
		
		
		
		
		
		
		
		
	}

}
