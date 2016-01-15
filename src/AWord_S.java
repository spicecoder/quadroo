import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

public class AWord_S  extends QuadRR {

public AWord_S(QuadRR rr) {
	   super(rr);
 	 try {
 		W.trim();
 		ResultSet rs = statement.executeQuery("select wid from words where name =" + "'"+W +"'" );
 		 if ((rs != null) &&  (rs.next()) ) {
 			 rr.rwid = rs.getInt(1);
 			 System.out.println("Existing word id is " + rr.rtrnWd);
 			 replystatus="word exists";
 			 reply.put("result", "word exists");
 			  }
 		 else 
 		 {  // System.out.println("just 2 values");
 		 //statement.executeUpdate("insert into words('uid','name') values( " +uid +",'" +  W +"') " );	
 		 
 			 try {
 				statement.execute("insert into words('uid','name','m') values( " + uid +",'" +  W +"','"+ m +" ') " );	
 				ResultSet pq = statement.getGeneratedKeys();
 				rr.rwid = pq.getInt(1);
 				replystatus="done";
 				System.out.println("Existing word id is " + rr.rtrnWd);
			} catch(SQLException e) {
				System.out.println("There is an error"+ e.getMessage());
				reply.put("result",e.getMessage());
			}
 		 // ANSFromWord( );
 		 
 		 // statement.executeUpdate("insert into words values(3,'starter4')");	 
 			 //System.out.println("jinserted 2 values");
 		reply.put("result","done");
 			
 			 
 		 }
 		
 
 	 } catch (Exception e) {
 		e.printStackTrace();
 		try {
			reply.put("result","error"+ e.getMessage());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
}