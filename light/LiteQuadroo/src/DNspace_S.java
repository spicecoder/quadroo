import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

public class DNspace_S  extends QuadRR {

public DNspace_S(QuadRR rr) {
	 super(rr);
	  try {
		  	W.trim();
		  	ResultSet rs = statement.executeQuery("select * from nspace where name=" + "'"+W +"'");
		  	 if (rs.next() ) {
		  		if(rs.getInt("uid") == uid){
		  		ResultSet rn = statement.executeQuery("select * from words where name like " + W +'%');  
		  		if ( !rn.next()) {
		  		 int rd = statement.executeUpdate("delete from nspace where name='" + W +"'");	
		  		 rid = rd;
		  		 reply.put("result", "done");
		  		}
		  		else { reply.put("result","inUse"); } 
		  		}
		  		else	 
		  			reply.put("result", "usermismatch:"+uid+"/"+rs.getInt("uid")); }
		  	 else 
		  	 {
		  		 
		  		// statement.executeUpdate("insert into words values(1, W)");	 
		  		 
		  		reply.put("result", "no namespace"); 
		  		 
		  	 }


		  } catch (Exception e) {
		  	// TODO Auto-generated catch block
		  	e.printStackTrace();
		  	try {
				reply.put("result", "error" + e.getMessage());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  }

}
}