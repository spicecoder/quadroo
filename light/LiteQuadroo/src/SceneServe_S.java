import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import org.json.JSONException;

public class SceneServe_S  extends QuadRR {
	String verbToAct ;  
   
public SceneServe_S(QuadRR rr) {
	super(rr);
//prejudge
	

	  try {
 		 W= W.trim(); rr.W = W;
 		 if  ( W == "VERB")  { verbToAct = m; verbToAct = verbToAct.trim();
 		
 		 
 		 }  
 		 
 		 else {
 			 rr.W= "VERB";
 		//	 WA.put(0, "VERB") ;
 			 
 			 
 			verbToAct= (new FSceneWord_S(rr)).m  ;
 			 
 		 }
 	QuadRR qr =	 new GPreJudgeWords_S (rr);
 //	Iterator tr = ((Collection)WA).iterator();
 	//while(tr.hasNext()) { WA
 	Boolean bb = Boolean.TRUE;
 	for (int im =0;im < qr.WA.length();im++) { 
 		 if ((String)(qr.mA.get(im))  == "T" || ((String)(qr.mA.get(im))  == "F"  ) ) {
 		Boolean bi = Boolean.FALSE;
 		for(int ci=0; ci < WA.length(); ci++ ) 
 		 		{  
 			     
 			    	 if ((String)(qr.WA.get(im))  == (String)WA.getString(ci))
 			    	 { if ((String)(qr.mA.get(im))  == (String)mA.getString(ci))
 			    	 { bi = Boolean.TRUE;
 			    	   break; }
 			    	 }
 			        if(bi == Boolean.FALSE) {
 			        	
 			        	bb = Boolean.FALSE;
 			        	
 			        }
 		 		} 		
 	} 
 	} //for end im
 		 //If the current object holds the words prejudged 
 	if(bb == Boolean.TRUE)
 	 
 	{ 
 	   //	e shall load the values from  matching words fron run time [for non judgement words }]
 		rr.byD = qr;   //this will carry the db word entries which we shall set values  to those at runtime
 		////////////////////////////////////////////
 		for (int im =0;im < qr.WA.length();im++) { 
 	 		 if ((String)(qr.mA.get(im))  == "T" || ((String)(qr.mA.get(im))  == "F"  ) ) {
 	 		Boolean bi = Boolean.FALSE;
 	 		for(int ci=0; ci < WA.length(); ci++ ) 
 	 		 		{  
 	 			     
 	 			    	 if ((String)(qr.WA.get(im))  == (String)WA.getString(ci))
 	 			    	 { if ((String)(qr.mA.get(im))  == (String)mA.getString(ci))
 	 			    	 { bi = Boolean.TRUE;
 	 			    	   break; }
 	 			    	 }
 	 			        if(bi == Boolean.FALSE) {
 	 			        	
 	 			        	bb = Boolean.FALSE;
 	 			        	
 	 			        }
 	 		 		} 		
 	 	} 
 	 		 
 	 		 else { 
 	 			//populate values from realtime input by  name match
 	 			for(int ci=0; ci < WA.length(); ci++ ) 
	 		 		{  
	 			     
	 			    	 if ((String)(qr.WA.get(im))  == (String)WA.getString(ci))
	 			    	   {qr.mA.put(im, mA.getString(ci)) ;}
	 			    	  
	 			    	 }
	 			         
	 			        }
	 		 	 
 	 		 
 	 		 
 	 	} //for end im
 		
 		
 		/////////////////////////////////////////
 		rr.byD = qr; // the valuues passed for reference in the invoked;
 		
 		reply.put("result", ( new RRVerber(rr,verbToAct )).reply);
 		  
 		 replystatus=" scene executed verb:" + verbToAct; 
 		 rid = 0;}
 	else 
 	{replystatus=" verb failed pre Judge condition:" + verbToAct;  }
 	 rid = -1;
 		 }
 		  
 	   catch (Exception e) {
 		e.printStackTrace();
 		 
			replystatus = "error"+ e.getMessage();
		}
 	   
	} 
 	 
	   

}
 