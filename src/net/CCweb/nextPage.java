package net.CCweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.bson.types.ObjectId;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		PrintWriter pw=response.getWriter();
//		pw.print(request.getSession().getAttribute("character"));
		request.getRequestDispatcher("nextPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
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
    
    collection.updateOne(new BasicDBObject("_id", id),new BasicDBObject("$set", new BasicDBObject("personalityRating", personalityRating)));

//----------------------------------------------------------------------------------------------------	

	

        	
        		   out.println("<script type=\"text/javascript\">");
        		   out.println("alert('thank you');");
        		   out.println("location='introServlet';");
        		   out.println("</script>");
        		
        }
	}

}
