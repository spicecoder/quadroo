import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

public class ASWord_S extends QuadRR {

public ASWord_S(QuadRR rr) {
		 super(rr);
	 	
try {
W= W.trim();
int  wi = (new FWord_S (rr)).rwid;
//wi sidi
System.out.println("the word id is "+wi);


if (wi > 0) {
	ResultSet rsc 	= statement.executeQuery("select sid from quadroo where sid = " + sid  + " AND fid=0 AND wid ="+wi);
	
	if (!rsc.next() )  {
		statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + uid +","+ wi +","+ sid +","+"0)");	 
		reply.put("result", "Done");
		} 
	

	else 
	{ 
		reply.put("result", "Word+Scene already there");
	}	
	
}
else 
{
	
   statement.executeUpdate("insert into words('uid','name') values(" + uid +",'"+  W+ "')");	 
   wi =(new FWord_S ( rr)).rwid;

   ResultSet rsc 	= statement.executeQuery("select sid from quadroo where sid = " + sid + " AND fid=0 AND wid ="+wi);
	if (!rsc.next() )  {
		
		statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + uid +","+wi+","+ sid +","+"0)");	 
		
		reply.put("result", "Done");
	
	}	 
	
	else {reply.put("result", "Word+Scene already there");}
}

} catch (Exception e) {
e.printStackTrace();
try {
	reply.put("result", "error");
} catch (JSONException e1) {
	e1.printStackTrace();
}

} 
	
}
}