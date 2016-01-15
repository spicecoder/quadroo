import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

public class DSWord_S  extends QuadRR {

public DSWord_S(QuadRR rr) {
	  super(rr);
try {
W= W.trim();
int  wi = (new FWord_S (rr)).rwid;
if (wi > 0) {
	int wid = wi;
	ResultSet rsc 	= statement.executeQuery("select qid from quadroo where sid =" + sid+ " AND fid=0 AND wid = " + wi );
	//can check for sceneid
	if (rsc.next() )  {
		
			int qi = rsc.getInt("qid");
			
			int rd = statement.executeUpdate("delete from quadroo where qid =" + qi);
			
		//reply =  "done" ;
			reply.put("result", "Done"); 
		
	}
		else {	
			
			//reply = "no word in scene";
			reply.put("result", "no word in scene");  
			
		} 
	}

	
else 
{
	reply.put("result", "no word");
	 
}

} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
try {
	reply.put("result", "error");
} catch (JSONException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}

}   
	
}
}