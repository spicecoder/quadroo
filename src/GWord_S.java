import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

public class GWord_S  extends QuadRR {

public GWord_S(QuadRR rr) {
	  
	super(rr);
	  try {


		  ResultSet rs = statement.executeQuery("select name from words where wid =" +wid);
		   if (rs.next() ) {
		  	 	
		  		rW=  rs.getString("name");
		  		reply.put("result", "Done");
		   
		  	 }

		   else 
		   {
			   reply.put("result", "not found");	 
		   
		   }



		  } catch (Exception e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		  try {
			reply.put("result","error"+ e.getMessage());
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		  try {
		  			reply.put("result","error"+ e.getMessage());
		  		} catch (JSONException e1) {
		  			// TODO Auto-generated catch block
		  			e1.printStackTrace();
		  		}
		   		
		  }   

}
}