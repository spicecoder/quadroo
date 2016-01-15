import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class FSceneWord_S  extends QuadRR {

public FSceneWord_S(QuadRR rr) {
	
	 super(rr);
	   
	  try {
	  W= W.trim();
	  S= S.trim();
	   int  wi = (new FWord_S (rr)).rwid;
	   int  si = (new FScene_S (rr)).rsid;
	   
	  if (wi > 0 && si >0 ) {
	  	int wid = wi;
	  	System.out.println("inside if wi si");
	  	System.out.println("select wid from quadroo where sid = '" + wid+"'");
	  	ResultSet rsc 	= statement.executeQuery("select m from quadroo where sid = " + si + " AND fid = 0 AND wid = " + wi);
	  	 
	  	if (rsc.next()){
	  	 
	   
	  		
	  		
	  	                     m = rsc.getString("m");
	  	                     rid = 0;
	  	}
	  	
	  	else {
	  	                rid =  -1;

	  	}
	 	  
	  }
	  else rid= -2; //scene or word not found
	  }
	   
	   catch (Exception e) {
	  e.printStackTrace();
	  //return "error";
	  
	}
	  
	  }   
}
 