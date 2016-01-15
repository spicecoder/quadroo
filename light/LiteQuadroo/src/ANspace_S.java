import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

public class ANspace_S  extends QuadRR {

public ANspace_S(QuadRR rr) {
	 
	  //only ns is not a word
	  int strno = W.lastIndexOf( '.' );
	  //System.out.println("last dot in : "+strno);
	  W= W.substring(0,strno+1);
	  System.out.println("The Namespace is : "+W);
	 try {
		W.trim();
		ResultSet rs = statement.executeQuery("select * from nspace where name =" + "'"+W +"'" );
		 if (rs.next() ) {
			 reply.put("result","nspace exists");
					  }
		 else 
		 {  // System.out.println("just 2 values");
		 statement.executeUpdate("insert into nspace('uid','name') values(" + uid +",'"+  W+ "')");	 
		 reply.put("result", "Done");
		 }

	 } catch (Exception e) {
		e.printStackTrace();
		try {
			reply.put("result","error");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
}