//import java.sql.Date;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;


public class SetS_S  extends QuadRR {
 public SetS_S(QuadRR rr) {
	 super(rr);
	 if (allowed)
		 
	 {
		   System.out.println("in getS  " + U + ":"+ pwd);
		  try {
			  PrintWriter out =((HttpServletResponse)outref).getWriter();
			  HttpSession session =((HttpServletRequest)inref).getSession(false);
			  if (session != null ) {
				
				  //if session exists we shall let it go with a message
				    session.setAttribute("S",S); 
				   
				  replystatus ="ok";rid =0;
				   
				 }
			  else {
				// make new  session .
			       
			      replystatus = "no session exists";rid =1;
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