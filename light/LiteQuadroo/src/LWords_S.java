import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LWords_S  extends QuadRR {

public LWords_S(QuadRR rr) {

	super(rr);
	  
	  
	  System.out.println("LSAWords jsonArray "+WA);
	  
	  //System.out.println("select wid from quadroo where sid = " + W);
	  try {
//	  W= W.trim();
//	  int  wi = (new FWord_S (rr)).rwid;

	  	System.out.println("inside if");
	  	System.out.println("select wid,name from quadroo");
	  	ResultSet rsc 	= statement.executeQuery("select wid,name from words");
	  	
	  	if (rsc.next() )  {
	  		//String jsn = "{AllWords:[{'name':'" + rsc.getString(2) + "','id':'"+rsc.getString(1)+"'},";
	  	    while (rsc.next()){
	  	    	JSONObject re = new JSONObject();
	  	    	re.accumulate("name",rsc.getString(2) );
	  	    	re.accumulate("id",rsc.getString(1) );
	  	    	reply.accumulate("result",re);
	  	    	
            	}
	  	    System.out.println(" rep json" + reply.toString() );
	  		
	  	}
   
	  } catch (Exception e) {
	  e.printStackTrace();
	  //return "error";
	  
	  try {
	  			reply.put("result","error"+ e.getMessage());
	  		} catch (JSONException e1) {
	  			// TODO Auto-generated catch block
	  			e1.printStackTrace();
	  		}
	   		
	  
	  }   
}
}