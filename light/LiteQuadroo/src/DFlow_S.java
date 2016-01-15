import java.sql.ResultSet;
import java.sql.SQLException;

public class DFlow_S  extends QuadRR {

public DFlow_S(QuadRR rr) {
	 super(rr);
	
	  	 try {
	   		W.trim();
	   		ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'");
	   		 if (rs.next() ) {
	   			if(rs.getInt("uid") == uid){
	   				int wi = rs.getInt("wid");
	   				ResultSet rsc 	= statement.executeQuery("select qid from quadroo where fid = " + wi+ "  AND sid=0 AND wid = 0 ");
	   				if (rsc.next() )  {int qi = rsc.getInt("qid");
	   				int rd = statement.executeUpdate("delete from quadroo where qid =" + qi);	
	   				reply.put("result", "Done");
	   				replystatus="done";
	   				}
	   				else   
	   					reply.put("result", "flow not found");	
	   			 // reply = "flow not found";
	   			}
	   			 
	   			else	 
	   			 reply.put("result","usermismatch:"+uid+"/"+rs.getInt("uid")); 
	   			 replystatus = "user mismatch";
	   			}
	   		 else 
	   		 {
	   			 
	   			// statement.executeUpdate("insert into words values(1,W)");	 
	   			reply.put("result", "noword"); 
	   			
	   			 
	   		 }
	   		
	   		
	   	 
	   	 
	   	 } catch (Exception e) {
	   		// TODO Auto-generated catch block
	   		e.printStackTrace();
	   		replystatus = "error";
	   	}

}
}