import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

public class AScene_S  extends QuadRR {

public AScene_S(QuadRR rr) {
	super(rr);
	   
	   

	  try {
 		 W= W.trim(); rr.W = W;
 		 int  wi = new FWord_S(rr).rwid; 
 		  
 		 System.out.println("The word id is : "+wi);
 		if (wi > 0) {
		  	int wid = wi;
		  	ResultSet rsc 	= statement.executeQuery("select sid from quadroo where sid = " + wid + " AND fid=0 AND wid = 0 ");
		  	if (rsc.next() )
            {rid=  rsc.getInt(1) ;
		  	reply.put("result","exists");
		  	replystatus="Warning"; }
		  	else
		  	{
          
 		statement.executeUpdate("insert into quadroo('uid','wid','sid','fid','m') values(" + uid +",0,"+ wi +",0,'"+m+"')");	
 		replystatus ="done"; }}
 		 else
 		 {
 			 // replystatus="non existant word ";	
 			 
 			 statement.executeUpdate("insert into words('uid','name','m') values(" + uid +",'"+  W+ "','" + "0" + "')");	 
		     wi = new FWord_S(rr).rwid; 
		  	 
		  	 // statement.executeUpdate("insert into words values(3,'starter4')");	 
		  	statement.executeUpdate("insert into quadroo('uid','wid','sid','fid','m') values(" + uid +",0,"+ wi+",0," + ","+m + "')");	 
		  	// statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY 
		  	//KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER)");
		  	   		
		  	rwid = wi;
		  	reply.put("result", "Done");
		  	replystatus=" done,new word added";  
 		 
 		 }
 		  
	  }
 	   catch (Exception e) {
 		e.printStackTrace();
 		 
			replystatus = "error"+ e.getMessage();
		}
 	   
	} 
 	 
	   

}
 