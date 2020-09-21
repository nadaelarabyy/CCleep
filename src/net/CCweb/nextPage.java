package net.CCweb;

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
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class nextPage
 */
public class nextPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public nextPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    static String[] characterArray= {"we could not define your personality based on your input"};
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("nextPage.jsp").forward(request, response);
		 
		 }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String Q1=request.getParameter("Q1");
	    String Q2=request.getParameter("Q2");
	    String Q3=request.getParameter("Q3");
	    String Q4=request.getParameter("Q4");
	    String Q5=request.getParameter("Q5");
	    String Q6=request.getParameter("Q6");
	    String Q7=request.getParameter("Q7");
	    String Q8=request.getParameter("Q8");
	    String Q9=request.getParameter("Q9");
	    String Q10=request.getParameter("Q10");	   
		
		boolean submitButtonPressed = request.getParameter("submit") != null;
        if(submitButtonPressed) {
//        	mongodb+srv://nada:<password>@cluster0-wcdyb.mongodb.net/test?retryWrites=true&w=majority
        	MongoClient mongoClient = MongoClients.create(
			        "mongodb+srv://nada:luck1997@cluster0-wcdyb.mongodb.net/sleepFeedback?retryWrites=true&w=majority"
			);
	MongoDatabase database = mongoClient.getDatabase("wellnessFeedback");


	MongoCollection<Document> collection=database.getCollection("sleepWellness");

//----------------------------------------------------------------------------------------------------
    Document person=new Document();
    ObjectId id=(ObjectId) request.getSession().getAttribute("_id");
    person.put("_id", id);	
    
    Document person2=new Document();
    String personalityRating=request.getParameter("rating");
//    person2.put("personalityRating", personalityRating);
//    Document person3=new Document();
//    person3.put("$set",person2);
////	collection.findOneAndUpdate(person, person3);
//   collection.updateOne(person, person3);
    String[] persona=request.getParameterValues("persona");
    if(persona!=null) {
    	String[] personaNames=getPersonaNames(persona);
    	collection.updateOne(new BasicDBObject("_id", id),new BasicDBObject("$set", new BasicDBObject("personalityOfChoice", Arrays.asList(personaNames))));
    }
    ArrayList<ontologyClass> test=(ArrayList<ontologyClass>) request.getSession().getAttribute("Result");
    if(test!=null)
        collection.updateOne(new BasicDBObject("_id", id),new BasicDBObject("$set", new BasicDBObject("characterInference", Arrays.asList(convertToArr(test)))));
    else
    	collection.updateOne(new BasicDBObject("_id", id),new BasicDBObject("$set", new BasicDBObject("characterInference", "you have a contradicting pre-sleep routine you do not have the best routine nor the worst")));	
    collection.updateOne(new BasicDBObject("_id", id),new BasicDBObject("$set", new BasicDBObject("personalityRating", personalityRating)));
    if(Q1!=null)
    	collection.updateOne(new BasicDBObject("_id", id),new BasicDBObject("$set", new BasicDBObject("BFI-Q1", Q1)));
    if(Q2!=null)
    	collection.updateOne(new BasicDBObject("_id", id),new BasicDBObject("$set", new BasicDBObject("BFI-Q2", Q2)));
    if(Q3!=null)
    	collection.updateOne(new BasicDBObject("_id", id),new BasicDBObject("$set", new BasicDBObject("BFI-Q3", Q3)));
    if(Q4!=null)
    	collection.updateOne(new BasicDBObject("_id", id),new BasicDBObject("$set", new BasicDBObject("BFI-Q4", Q4)));
    if(Q5!=null)
    	collection.updateOne(new BasicDBObject("_id", id),new BasicDBObject("$set", new BasicDBObject("BFI-Q5", Q5)));
    if(Q6!=null)
    	collection.updateOne(new BasicDBObject("_id", id),new BasicDBObject("$set", new BasicDBObject("BFI-Q6", Q6)));
    if(Q7!=null)
    	collection.updateOne(new BasicDBObject("_id", id),new BasicDBObject("$set", new BasicDBObject("BFI-Q7", Q7)));
    if(Q8!=null)
    	collection.updateOne(new BasicDBObject("_id", id),new BasicDBObject("$set", new BasicDBObject("BFI-Q8", Q8)));
    if(Q9!=null)
    	collection.updateOne(new BasicDBObject("_id", id),new BasicDBObject("$set", new BasicDBObject("BFI-Q9", Q9)));
    if(Q10!=null)
    	collection.updateOne(new BasicDBObject("_id", id),new BasicDBObject("$set", new BasicDBObject("BFI-Q10", Q10)));
    	
//----------------------------------------------------------------------------------------------------	

	

        	
        		   out.println("<script type=\"text/javascript\">");
        		   out.println("alert('thank you');");
        		   out.println("location='introServlet';");
        		   out.println("</script>");
        		
        }
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
	public static String[] convertToArr(ArrayList<ontologyClass> test) {
		String[] arr=new String[test.size()];
		for(int i=0;test.size()>i;i++) {
			
				arr[i]=test.get(i).getClassName_en();
			
		}
		return arr;
	}
	public static String[] getPersonaNames(String[] persona) {
		String[] personaName=new String[persona.length];
		for(int i=0;persona.length>i;i++) {
			String[] temp=persona[i].split(Pattern.quote("$"));
			personaName[i]=temp[0].replace("_", " ");
			
		}
		return personaName;
	}


}
