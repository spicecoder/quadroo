import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

public class FWord_S  extends QuadRR {

	
 
public FWord_S(QuadRR rr) {
	 super(rr);
	  
 	 try {
 		W=W.trim();
 		  System.out.println("in FWord:" +W);
 		 System.out.println("sql stmnt  "+statement);
 				ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'");
 		  		 if (rs.next() ) {
 	  			 	
 	  				rwid =  rs.getInt(1);
 	  				System.out.println("test rwid "+rwid);
 	  				replystatus = "done";
 	  		 
 	  			 }
 	  		
 	  		 else 
 	  		 {
 	  			System.out.println("not found test  "+W); 
 	  			rwid = -1 ;	 
 	  			replystatus = "notfound";
 	  		 }
 		
 
 	 } catch (Exception e) {
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