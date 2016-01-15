import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

/*
word_id under the scene
words from the word id
reusing the result set
*/

public class DFSid_S  extends QuadRR {
	public DFSid_S(QuadRR rr) {
		     super(rr);
	try {
	{
		 
		ResultSet rsc 	= statement.executeQuery("select sid from quadroo where sid = " + wid + " AND fid=0 AND wid = 0 ");
		if (rsc.next() )  {
			ResultSet rsf 	= statement.executeQuery("select qid from quadroo where sid = " + wid + " AND fid= " + fid );
				if (rsf.next() )  {	
				int qi = rsf.getInt("qid");
				
				int rd = statement.executeUpdate("delete from quadroo where qid =" + qi);
				
				reply.put("result", "Done");}
			else {	
				reply.put("result", "no scene in flow");
				
			} 
		}
	
		else 
		{ 
			reply.put("result", "no scene");
		}	
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