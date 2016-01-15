import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

public class SendWordS2S_S  extends QuadRR {

public SendWordS2S_S(QuadRR rr) {
	 //QuadRs repl  =  new QuadRs();
	 super(rr);
 	 try {
 		W=W.trim();
 		 //System.out.println("in AWord:" +W);

 				ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'");
 		  		 if (rs.next() ) {
 	  			 	
 	  				rwid =  rs.getInt(1);
 	  				System.out.println("test rwid "+rwid);
 	  				reply.put("result",  "Done");
 	  		 
 	  			 }
 	  		
 	  		 else 
 	  		 {
 	  			 
 	  			rid = -1 ;	 
 	  			reply.put("res8ult", "error");
 	  		 }
 		
 
 	 } catch (Exception e) {
 		e.printStackTrace();
 		try {
			reply.put("result",  "error:" + e.getMessage());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 		
	}

}
}