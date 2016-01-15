import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

public class DScene_S  extends QuadRR {

public DScene_S(QuadRR rr) {
	 //QuadRs repl  =  new QuadRs();
	super(rr);

	 	 try {
	   		W.trim();
	   		ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'");
	   		 if (rs.next() ) {
	   			if(rs.getInt("uid") == uid){
	   				int wi = rs.getInt("wid");
	   				ResultSet rscs 	= statementW.executeQuery("select qid,sid from quadroo where sid = " + wi+ "  AND fid !=0   ");
	   				if (rscs.next() )  {replystatus="scene being used by one or more Flows cannot delete"; }
	   				else 
	   				{
	   					ResultSet rscw 	= statementW.executeQuery("select qid,sid from quadroo where sid = " + wi+ "  AND  wid  !=0   ");
	   					if (rscs.next() )  {replystatus="scene being used by one or more Flows cannot delete"; } 
	   					else  {	
	   				    ResultSet rsc 	= statement.executeQuery("select qid,sid from quadroo where sid = " + wi+ "  AND fid=0 AND wid = 0 ");
	   				    if (rsc.next() )  {int qi = rsc.getInt("qid");
	   				    int rd = statement.executeUpdate("delete from quadroo where qid =" + qi);	
	   				     reply.put("result", "done");
	   				     replystatus="done";}
	   				} 
	   				
	   			}
	   				}
	   			else	 
	   				reply.put("result", "usermismatch:"+uid+"/"+rs.getInt("uid")); }
	   		 else 
	   		 {
	   			 
	   			 
   					reply.put("result", "scene not found");
   				    replystatus="scene not found";
	   		 }
	   	 
	   	 } catch (Exception e) {
	   		// TODO Auto-generated catch block
	   		e.printStackTrace();
	   		//return "error";
	   		try {
				reply.put("result",  "error" + e.getMessage());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	   	}

}
}