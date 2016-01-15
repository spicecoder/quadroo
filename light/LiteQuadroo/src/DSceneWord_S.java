import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class DSceneWord_S  extends QuadRR {

public DSceneWord_S(QuadRR rr) {
	  super(rr);
	 
	 	  
	  statement = rr.statement;	
	  statementW = rr.statementW;	
	  wid = (new FWord_S(rr)).rwid;
	  rr.W = S;
	  sid = (new FScene_S(rr)).rwid;
	  
	  try {
	   		W.trim();
	   ResultSet  rs = statement.executeQuery("select * from quadroo where sid = " + sid  + " AND wid = " + wid + " AND fid = 0 ");
	   		 if (rs.next() ) {
	   			if(rs.getInt("uid") == uid){
	   				  int qi = rs.getInt("qid");
	   				int rd = statement.executeUpdate("delete from quadroo where qid =" + qi);	
	   				reply.put("result", "Done");
	   				replystatus="done";
	   				}
	   				else
	   					reply.put("result","usermismatch:"+uid+"/"+rs.getInt("uid")); 
		   			 replystatus = "user mismatch";  
	   				
	   			 // reply = "flow not found";
	   			}
	   			 
	   			else	{ 	reply.put("result", "scne word not found");	
	   		              replystatus = "scne word not found";  
	   			
	   			} 
	   			 
	   		
	   		
	   	 
	   	 } catch (Exception e) {
	   		// TODO Auto-generated catch block
	   		e.printStackTrace();
	   		replystatus = "error";
	   	}
	  	  
	  }   
}
 
 