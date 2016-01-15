//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.json.JSONException;


public class SetVF_S  extends QuadRR {
 public SetVF_S(QuadRR rr) {
	//parameter expected "UIdata={fid:nnn,m:{xf:y}}" 
	super(rr);
	
 	 try {
 		 
 		
 		 System.out.println("in SetVF:" +m+ "fid:" + fid);

 	ResultSet		 rs = statement.executeQuery("select * from quadroo where  wid= 0 AND sid =  0  AND fid = " + fid );
 				
 		 if ((rs  == null) && !(rs.next())  ) {
 			
 			reply.put("result", "flow does not Exist");
 			replystatus = "error";
 			  }
 		 else 
 		 {  // System.out.println("just 2 values");
 		// rid = rs.getInt("qid");
 		 statement.executeUpdate("update quadroo  set m = '" + m+ "' where wid= 0 AND sid = 0 AND fid = " + fid   );	
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
