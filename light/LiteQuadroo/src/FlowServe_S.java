import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

public class FlowServe_S  extends QuadRR {
	String verbToAct ;  
   
public FlowServe_S(QuadRR rr) {
	super(rr);
//prejudge
	

	  try   {
		  F= F.trim();
		  System.out.println("flow passed"+F);
		  rr.W =F;
		  
		  int  wi = (new FWord_S (rr)).rwid;
		  if (wi > 0) {
		  	fid = wi;
		  	ResultSet rsf 	= statement.executeQuery("select sid from quadroo where  fid=" +fid + " AND wid = 0 ");
		  	if (rsf.next() )  { 
		  	
		  		while (rsf.next()  ){
		  			rr.S = "";		  		}
		  			rr.sid = rsf.getInt("sid");
		  			 
		             reply.accumulate("result :sid:"+sid,(new RRVerber(rr,"SceneServe" )).replystatus) ;
		  			
		  			
		  		}
		  		
		  		 


		  	else 
		  	{ 
		  		reply.put("result", "no scene to execute");
		  	}	
		  	
		  }
		  else 
		  {
		  	reply.put("result","flow name not in dictionary");
		  	 
		  }

		  } catch (Exception e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		  
		  } 
 	   
	} 
 	 
	   

}
 