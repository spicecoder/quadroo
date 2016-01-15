import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LFlowScenes_S  extends QuadRR {

public LFlowScenes_S(QuadRR rr) {
	 super(rr);
try {
W= W.trim();
System.out.println("word passed"+ rr.W);
int  wi = (new FWord_S (rr)).rwid;
if (wi > 0) {
	int wid = wi;
	ResultSet rsf 	= statement.executeQuery("select sid from quadroo where  fid=" +wi + " AND wid = 0 ");
	if (rsf.next() )  { String xml = "<flow name = " + W + ">";
						JSONObject re = new JSONObject();
						re.accumulate("flow", W);
	                     wi = rsf.getInt("sid");
	                     System.out.println("scene id :" + wi);
	                     rr.wid = wi; String wrd = "" ;
	                   //  String wrd = (new GWord_S(rr)).rW;
	                  //   xml = xml + "<scene name=" + wrd+ "/>";  
	
		while (rsf.next()  ){
			wi = rsf.getInt("sid");
			rr.wid = wi;
            wrd = (new GWord_S(rr)).rW;
            xml = xml + "<scene name=" + wrd+ "/>";  	
            JSONObject rs = new JSONObject();
  	    	rs.accumulate("scene",wrd );
  	    	rs.accumulate("sid",wi );
  	    	re.accumulate("scenes", rs);}
		
  	    	reply.accumulate("result",re);
  	    	
			
			
		}
		
		 


	else 
	{ 
		reply.put("result", "no flow with that name");
	}	
	
}
else 
{
	reply.put("result","flow name not in dictionary");
	 
}

} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
try {
	reply.put("result","error"+ e.getMessage());
} catch (JSONException e2) {
	// TODO Auto-generated catch block
	e2.printStackTrace();
}
try {
			reply.put("result","error"+ e.getMessage());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 		
}   
}
}