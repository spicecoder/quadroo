import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class GPreJudgeWords_S  extends QuadRR {

public GPreJudgeWords_S(QuadRR rr) {
	
	 super(rr);
	  
	   
	  try {
	  if(S != "") {	  
	  S= S.trim();
	  rr.W = S;
	     sid = (new FWord_S (rr)).rwid;}
	  
	  if (sid > 0) {
	  	  
	  	 
	  	ResultSet rsc 	= statement.executeQuery("select wid, m, afford  from quadroo where sid = " + sid + " AND fid = 0 AND wid != 0");
	  
	  	if (rsc.next()){
	  	
	  int ii = 0 ;
	  	while (rsc.next())  {  
	  		
	  		
	  		  
	  	                  int   wi = rsc.getInt("wid");
	  	                     m = rsc.getString("m");
	  	                     rr.wid = wi;
	  	                     String nW = (new GWord_S(rr)).rW ;
	  	                     if(rsc.getString("afford") == "") {
	  		                 WA.put(ii,nW) ;
	  		                 mA.put(ii,m);}
	  	                     ii = ii + 1;
	  		                   }
	  	
	  	
	  	
	 
	  	replystatus ="done";
	  	
	  		
	
	  	}
	 	  	else 
	  	{   rid = -2;
	  		reply.put("result", "no scene with that name");
	  		replystatus ="no scene with that name";
	  	}
	  	
	  }
	  
	  else {
		  rid = -1;
	  		reply.put("result", "scene name not in dictionary");
	  		replystatus ="scene name not in dictionary ";
		  
		  
	  }

	  } catch (Exception e) {
	  e.printStackTrace();
	  //return "error";
	  try {
		reply.put("result", "error:" + e.getMessage());
	} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  
	  }   
}
}