//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.json.JSONException;


public class SetVFSS_S  extends QuadRR {
 public SetVFSS_S(QuadRR rr) {
	super(rr);
	  ResultSet rs ;
 	 try {
 		
 		
 		 System.out.println("in SetVFSS:" +"fid:"+fid+ "sid:" + sid);

 			 rs = statement.executeQuery("select * from quadroo where  wid=0 AND sid = " + sid + "  AND fid = " + fid  );
 				
 		 if ((rs  == null) && !(rs.next())  ) {
 			
 			reply.put("result", "Scene in flow does not Exist");
 			replystatus = "error";
 			  }
 		 else 
 		 {  // System.out.println("just 2 values");
 		// rid = rs.getInt("qid");
 		 statement.executeUpdate("update quadroo  set m = '" + m + "' where wid= 0 AND sid =" +  sid+ " AND fid " + fid );	
 		// ANSFromWord( );
 		 
 		 // statement.executeUpdate("insert into words values(3,'starter4')");	 
 			 //System.out.println("jinserted 2 values");
 		reply.put("result","done");
 		replystatus = "ok";
 			
 			 
 		 }
 		
 
 	 } catch (Exception e) {
 		// TODO Auto-generated catch block
 		e.printStackTrace();
 		try {
			reply.put("result","error:" + e.getMessage());
			replystatus="error";
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 		
	}
 
	 
}
}