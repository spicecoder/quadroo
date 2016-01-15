//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.json.JSONException;


public class SetVFSW_S  extends QuadRR {
 public SetVFSW_S(QuadRR rr) {
	super(rr);
 	 try {
 		W.trim();
 		
 		 System.out.println("in AWord:" +W);

 				ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'" );
 		 if ((rs != null) &&  (rs.next()) ) {
 			
 			reply.put("result", "word exists");
 			  }
 		 else 
 		 {  // System.out.println("just 2 values");
 		 statement.executeUpdate("insert into words('uid','name','m') values( " + uid +",'" +  W +"','"+ m +" ') " );	
 		// ANSFromWord( );
 		 
 		 // statement.executeUpdate("insert into words values(3,'starter4')");	 
 			 //System.out.println("jinserted 2 values");
 		reply.put("result","done");
 		ResultSet rsw = statement.executeQuery("select wid from words where name =" + "'"+W +"'" );
 		if ((rsw != null) &&  (rsw.next()) ) {
			 rwid = rsw.getInt("wid");
			  }
		 else 
 		replystatus="Error";
 			
 			 
 		 }
 		
 
 	 } catch (Exception e) {
 		// TODO Auto-generated catch block
 		e.printStackTrace();
 		try {
			reply.put("result","error:" + e.getMessage());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 		
	}

}
}