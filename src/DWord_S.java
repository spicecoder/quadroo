import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

public class DWord_S  extends QuadRR {

public DWord_S(QuadRR rr) {
	 super(rr);
	 	 try {
	   		W.trim();
	   		ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'");
	   		 if (rs.next() ) {
	   			               if(rs.getInt("uid") == uid){
	   		        
	   			                      int rd = statement.executeUpdate ("delete from words where name ='" + W +"'");	
	   			                  
	   			                   reply.put("result", "Done");
	   			                replystatus = "done";
	   			 
	   			              }
	   			 
	   			                else	
	   			                  {replystatus = "usermismatch";	reply.put("result","usermismatch:"+uid+"/"+rs.getInt("uid"));} }
	   		 else 
	   		 {
	   			 
	   			// statement.executeUpdate("insert into words values(1,W)");	 
	   			replystatus = "noword"; 
	   			reply.put("result", "noword"); 
	   			 
	   		  		
	   		
	   		 
	   	 
	   	 
	   	      } 
	   		 

	   	 }
	   		 
	   		 catch (Exception e) {
	   		// TODO Auto-generated catch block
	   		e.printStackTrace();
	   		try {replystatus = "error";
				reply.put("result", "error;" + e.getMessage());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	   	}

}
}