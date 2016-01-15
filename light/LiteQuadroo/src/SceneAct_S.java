import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

public class SceneAct_S  extends QuadRR {
	String verbToAct ;  
   
public SceneAct_S(QuadRR rr) {
	super(rr);
//prejudge
	

	  try {
 		 W= W.trim(); rr.W = W;
 		 if  ( W == "VERB")  { verbToAct = m; verbToAct = verbToAct.trim();
 		
 		 
 		 }  
 		 
 		 else {
 			 
 			verbToAct= (new FSceneWord_S(rr)).m  ;
 			 
 		 }
 		  reply.put("result from"+S, ( new RRVerber(rr,verbToAct )).reply);
 		  
 		 replystatus=" scene executed verb:" + verbToAct;  
 		 }
 		  
 	   catch (Exception e) {
 		e.printStackTrace();
 		 
			replystatus = "error"+ e.getMessage();
		}
 	   
	} 
 	 
	   

}
 