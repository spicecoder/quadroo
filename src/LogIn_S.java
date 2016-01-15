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

public class LogIn_S  extends QuadRR {
//the input is uid pwd in rr;
//if it matches what in db 
	
	
public LogIn_S(QuadRR rr) {
	
	    super(rr);
	  if (allowed) {
	   System.out.println("in login  " + U + ":"+ pwd);
	  try {
		  PrintWriter out =((HttpServletResponse)outref).getWriter();
		  HttpSession session =((HttpServletRequest)inref).getSession(false);
		  if (session != null ) {
			
			  //if session exists we shall let it go with a message
			  U =(String) session.getAttribute("logon.isDone"); 
			  System.out.println("session exists  " + U + ":"+ pwd);
			  replystatus ="already logged in";rid =0;
			  allowed= true;
			 }
		  else {
			// make new  session .
			  System.out.println("Setting session for"+ U );
		       session =((HttpServletRequest)inref).getSession(true);
		      session.setAttribute("logon.isDone", U);
		      System.out.println("new session:set:" + session.getAttribute("logon.isDone")); 
		      allowed = true;
		      replystatus = "ok";rid =0;
		  }
		   
		                      	  
		        
		           
		            }
		        
	 
		    
		    catch(Exception e ) { // Couldn't redirect to the target. Redirect to the site's home page.
		    	  try {
					((HttpServletResponse)outref).sendRedirect("/");
					rid = -1;
					replystatus="notok";
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					rid = -1;
					e1.printStackTrace();
				} }
		       }

	  else
	  {replystatus="not allowed"; rid = -1; }
	  
}
}

	   		
	   
 