import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class LSceneWords_S  extends QuadRR {

public LSceneWords_S(QuadRR rr) {
	
	 super(rr);
	  
	   
	  try {
	  S= S.trim();
	  rr.W = S;
	  System.out.println("scene for list word:"+ S);
	int wi =  ( new RRVerber(rr,"FWord" )).byD.rwid;
	 //  int  wi = (new FWord_S (rr)).rwid;
	  if (wi > 0) {
	  //	int wid = wi;
	  	System.out.println("scene id found for listword :"+ S+":"+wi);	 
	  	ResultSet rsc 	= statement.executeQuery("select wid, m  from quadroo where sid = " + wi + " AND fid = 0 AND wid != 0  AND afford=''");
		  int ii = 0 ;
	  	if (rsc.next())  {  
              wid = rsc.getInt("wid");
              m = rsc.getString("m");
              rr.wid = wid;
             String nW = (new GWord_S(rr)).rW ;
              WA.put(ii,nW) ;
              mA.put(ii,m);
                 
	  	

	  	while (rsc.next())  { 
	  	                           System.out.println("in word list loop" + ii)	;
	  		
	  		      
	  		                 ii =  ii + 1;
	  	                     wid = rsc.getInt("wid");
	  	                     m = rsc.getString("m");
	  	                     rr.wid = wid;
	  	                     nW = (new GWord_S(rr)).rW ;
	  		                 WA.put(ii,nW) ;
	  		                 mA.put(ii,m);
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
	  	
	 // }
/*	  else 
	  {     rid = -2;
	  		reply.put("result", "scene name not in dictionary");
	  	 
	  }
	     */
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