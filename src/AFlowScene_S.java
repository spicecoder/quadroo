import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

public class AFlowScene_S  extends QuadRR {
	
	
	
	public AFlowScene_S(QuadRR rr) {
	super(rr);
		  WA = rr.WA;
		  mA = rr.mA;
		  int luid = rr.uid;
		  statement = rr.statement;
		  m = rr.m;
		  int mm;
		  if (WA.length()  > 0) {
			  
			  
		  } 
		  
		  
		 
		  try {
		   mm = Integer.parseInt(m);
		  }
		  catch (Exception e) {
			mm = 0;  
			//log 
			  
		  } 
		  fid = new AFlow_S(rr).rfid ;
		  sid = new AScene_S(rr).rsid;
		  if (fid > 0 && sid > 0 )  {
			  try {
				statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +","+ 0 +","+ wid +","+ fid +")");
			  replystatus = "done";
			  reply.accumulate("result", "done");
			  
			  
			  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	 
	 			replystatus="error";
			  try {
				reply.put("result","done");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		} 
		  }
	  	
	 	}
 