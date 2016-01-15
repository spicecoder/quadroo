import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

public class MakeScene_S  extends QuadRR {
 // not using this one , a scene in the db is called makeMood that has verb ProduceModd 
   
public MakeScene_S(QuadRR rr) {
	super(rr);
//prejudge
	

	  try {
 		 S= S.trim();  
 		 String  verbToAct = "LSceneWords";
 		
 		 
 		  WA =  ( new RRVerber(rr,verbToAct )).WA;
 		  
 		System.out.println("scene executed verb:" + verbToAct + "words"+ WA.toString()) ;
 		int ll = WA.length();
 		S="";
 		for (int ii = 0 ;ii <ll;ii++) {
 			
 			S = S +mA.getString(ii);
 		
 		}
 		    rr.S = S;
 		
 		 }
 		  
 	   catch (Exception e) {
 		e.printStackTrace();
 		 
			replystatus = "error"+ e.getMessage();
		}
	  String  verbToAct = "ProduceMood";
		
		 
		  replystatus =  ( new RRVerber(rr,verbToAct )).replystatus;
		  
	} 
 	 
	   

}
 