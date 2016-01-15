import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

/*
word_id under the scene
words from the word id
reusing the result set
*/

public class DFLowScene_S  extends QuadRR {
public DFLowScene_S(QuadRR rr) {
	     super(rr);
try {
W= W.trim();
int  wi = (new FWord_S (rr)).rwid;
if (wi > 0) {
	int wid = wi;
	ResultSet rsc 	= statement.executeQuery("select sid from quadroo where sid = " + wid+ " AND fid=0 AND wid = 0 ");
	if (rsc.next() )  {
		ResultSet rsf 	= statement.executeQuery("select qid from quadroo where sid = " + wid + " AND fid= " + fid);
		
		if (rsf.next() )  {	
			int qi = rsf.getInt("qid");
			
			int rd = statement.executeUpdate("delete from quadroo where qid =" + qi);
			reply.put("result", "Done"); }
		//return "done" ;}
		else {	
			reply.put("result", "no scene in flow");
			
		} 
	}

	else 
	{  reply.put("result", "no scene");
		
		
	}	
	
}
else 
{      reply.put("result", "no word");
			 
}

} catch (Exception e) {
// TODO Auto-generated catch block
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