import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

public class FFlow_S  extends QuadRR {

public FFlow_S(QuadRR rr) {
	 super(rr);

	  try {
		  W= W.trim();
		  int  wi = new FWord_S(rr).rwid;
		  //ResultSet rs = statement.executeQuery("select wid from words where name =" +"'"+W +"'");
		  if (wi > 0) {
		  	int wid = wi;
		  	ResultSet rsc 	= statement.executeQuery("select  qid from quadroo where sid =wid AND fid=0 AND wid = 0 ");
		  	if (rsc.next() )  {rfid = rsc.getInt("qid") ; reply.put("result", "Done");replystatus="done";}
		  	else 
		  	{ 
		  			   		
		  		reply.put("result", "error");;
		  		rid = -1;}	
		  	
		  }
		  else 
		  {reply.put("result", "Error");replystatus="word not found";
			rid = -2;	}

		  } catch (Exception e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		 rid = -9;
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