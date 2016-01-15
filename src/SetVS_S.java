//import java.sql.Date;
import java.sql.ResultSet;
import org.json.JSONException;


public class SetVS_S  extends QuadRR {
 public SetVS_S(QuadRR rr) {
	 //QuadRs repl  =  new QuadRs();\
	 //parameter expected "UIdata={sid:nnn,m:{x:y}}"
	 
	super(rr);
	  ResultSet rs ;
 	 try {
 		W.trim();
 		
 		 System.out.println("in SetVS:" +m+ "sid:" + sid);

 			 rs = statement.executeQuery("select * from quadroo where  wid=0 AND sid = " + sid + "  AND fid = 0 "  );
 				
 		 if ((rs  == null) && !(rs.next())  ) {
 			
 			reply.put("result", "Scene does not Exist");
 			replystatus = "error";
 			  }
 		 else 
 		 {  // System.out.println("just 2 values");
 		// rid = rs.getInt("qid");
 		 statement.executeUpdate("update quadroo  set m = '" + m+ "' where wid= 0 AND sid =" +  sid+ " AND fid = 0" );	
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