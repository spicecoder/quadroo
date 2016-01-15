import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
 

public class AFScene_S  extends QuadRR {
 public AFScene_S(QuadRR rr) {
	    super(rr);
 try {
 W= W.trim();
 int  wi = (new FWord_S (rr)).rwid;
 //ResultSet rs = statement.executeQuery("select wid from words where name =" + "'"+W +"'");
 if (wi > 0) {
 	int wid = wi;
 	ResultSet rsc 	= statement.executeQuery("select sid from quadroo where sid = "+  wid + " AND fid=0 AND wid = 0 ");
 	if (rsc.next() )  {
 		ResultSet rsf 	= statement.executeQuery("select sid from quadroo where sid =" + wid + " AND fid= "+ fid );
 			
 		
 		if (rsf.next() )  {	
 		//return "scene exists in flow" ;
 		reply.put("result", "scene exists in flow") ;
 		}
 		else {	
 			ResultSet rsfm 	= statement.executeQuery("select MAX(wid) from quadroo where  fid= " + fid );
 		    int wp = rsfm.getInt(1);
 		    wp = wp + 1;
 			statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + uid +","+wp+","+ wid +","+fid+")");	 
 			reply.put("result","done");
 		} 
 	}

 	else 
 	{ 
 		statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + uid +",0,"+ wid +",0)");	 
 		ResultSet rsfm 	= statement.executeQuery("select MAX(wid) from quadroo where  fid= " + fid );
 	    int wp = rsfm.getInt(1);
 	    wp = wp + 1;
 		statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + uid +","+wp+","+ wid +","+fid+")");	 
 	  		
 		reply.put("result","done");
 	}	
 	
 }
 else 
 {
 	
    statement.executeUpdate("insert into words('uid','name') values(" + uid +",'"+  W+ "')");	 
    wi = (new FWord_S (rr)).rwid;
 	 
 	 // statement.executeUpdate("insert into words values(3,'starter4')");	 
 	statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + uid +",0,"+ wi +",0 )");	 
 	// statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER)");
 	ResultSet rsfm 	= statement.executeQuery("select MAX(wid) from quadroo where  fid= " + fid );
     int wp = rsfm.getInt(1);
     wp = wp + 1;
 	
 	statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + uid +","+wp+","+ wi +","+fid+")");	 
   	  		
 	reply.put("result","done");
 	 
 }




 } catch (Exception e) {
 e.printStackTrace();
 try {
	reply.put("result","error" + e.getMessage());
} catch (JSONException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
 }   

}
}