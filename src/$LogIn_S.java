import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
//import com.mongodb.DBObject;
import com.mongodb.Mongo;
//import com.mongodb.util.JSON;

public class $LogIn_S  extends QuadRR {
///This verb is  executed without calling the super  constructor with quaddRr argument.
//	Instead it just accepts the rr input and allows an outcome to be noted in its own rr.
//this way nothing is inherited in its own RR.	
	
	
public $LogIn_S(QuadRR rr) {
	
	     
	  
	   System.out.println("in login$  " + rr.U + ":"+ rr.pwd);
	  try {
		  PrintWriter out =((HttpServletResponse)rr.outref).getWriter();
		  HttpSession session =((HttpServletRequest)rr.inref).getSession(false);
		  rid = -1;
		 if (session != null ) {
		  //for any verb we check firstly if session exists and if it does we take user id 
		  //from session and pass it in the request, if not we check allow, if so we allow it
		  //to proceed without bothering about session.
		  //the user is 
		  U =(String) session.getAttribute("logon.isDone"); 
		  System.out.println("obtained from session" + U);
		  replystatus ="sessionok";rid =0;
		  allowed= true;
		 }
		  else {
		   U=rr.U;
		   System.out.println("inlogin $ v "+ U );
		    if (!allowUser(rr)) {
		    	System.out.println("failedinlogin  not alloed " + rr.U  +rr.pwd);
		      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
		      out.println("<BODY>Your login and password are invalid.<BR>"); 
		      out.println("You may want to <A HREF=\"/login.html\">try again</A>");
		      out.println("</BODY></HTML>");
		      replystatus ="notok"; 
		      rid = -1;
		      allowed = false;
		      
		    } else {
		    	
		    	//this verb does not create session only makes way for it
		      // Valid login. Make a note in the session object.
	//	        session =((HttpServletRequest)rr.inref).getSession(true);
	//	       session.setAttribute("logon.isDone", rr.uid);
		      // Try redirecting the client to the page he first tried to access
		      //we donot do ; ,jquery is receiving the response and decides 
	//	        String target = (String) session.getAttribute("login.target");
	//	        if (target != null) {
	//	        	((HttpServletResponse)rr.outref).sendRedirect(target);
		          replystatus = "ok";
		          rid = 0;
		          allowed = true;
		                      	  }
		  }
		 
	  //session exists---we donot do the cheking for password; however plan is to generate a key to be passed 
	 //	 to the client; key is tied with the verb invocation sequence number;
	 //in case of session hijacking the verb will soon go out of sync with the original user for his to raise an alarm;
		 
	  
	  
	  }
		    
		    catch(Exception e ) { // Couldn't redirect to the target. Redirect to the site's home page.
		    	  try {
					((HttpServletResponse)rr.outref).sendRedirect("/");
					rid = -1;
					e.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					rid = -1;
					e1.printStackTrace();
				} }
		       }


	  protected boolean allowUser(QuadRR rr ) {
		  
		   
		  
		  
		  
		  
		  // connect with mongodb server, get uid pwd matched stat
		  
		   try {
			  
			//	Mongo mongo = new Mongo("localhost", 27017);
				String epwd = CryptWithMD5.cryptWithMD5(rr.pwd);
				
				DB db = rr.mongo.getDB("users");
				DBCollection collection = db.getCollection("users");
				BasicDBObject query = new BasicDBObject();
				
				query.put("name", rr.U);
				query.put("password", epwd);
                
				
				DBCursor cursor = collection.find(query);

				if (cursor.hasNext()) {
					System.out.println("log in  rec found  "  	);
					 
				   return  true ;
				}
				else {
					 
					return false  ;}
				
				
				}
				 
	 			 
	  catch (Exception e) {
	  e.printStackTrace();
	  //return "error";
	  return false;
	  
	  		}
	  
}
}
	   		
	   
 