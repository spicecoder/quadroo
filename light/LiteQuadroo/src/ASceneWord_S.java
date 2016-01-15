import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class ASceneWord_S  extends QuadRR {

public ASceneWord_S(QuadRR rr) {
	  super(rr);
	      
	      
	 
	  wid = (new AWord_S(rr)).rwid;
	  rr.W = S;
	  sid = (new AScene_S(rr)).rwid;
	  
	  try {
			statement.executeUpdate("insert into quadroo('uid','wid','sid','fid','m') values(" + uid +","+wid+","+ sid +",0,'"+m+"')");	
	         replystatus ="done";
	         reply.accumulate("result", "done");
	  } catch (Exception e) {
		  
	  replystatus = "error";	  
	  e.printStackTrace(); 
	  try {
		reply.accumulate("result", e.getMessage());
	} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  	  
	  }   
}
}
 