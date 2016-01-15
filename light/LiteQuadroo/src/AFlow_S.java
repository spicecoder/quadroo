import java.sql.ResultSet;
import java.sql.SQLException;

public class AFlow_S  extends QuadRR {

public AFlow_S(QuadRR rr) {
	  super(rr);
	  String W =  rr.W;
	  int luid =rr.uid;
	  String m = rr.m;
	  statement = rr.statement;
	  if (allowed)
	  try {
		  W= W.trim();
		  int  wi = new FWord_S(rr).rwid; 
		  //ResultSet rs = statement.executeQuery("select wid from words where name =" + "'"+W +"'");
		  if (wi > 0) {
		  	int wid = wi;
		  	ResultSet rsc 	= statement.executeQuery("select fid from quadroo where fid = " + wid + " AND sid=0 AND wid = 0 ");
		  	if (rsc.next() )  {rid=  rsc.getInt(1) ;
		  	reply.put("result","exists");
		  	replystatus="flow exists"; }
		  	else 
		  	{ 
		  		statement.executeUpdate("insert into quadroo ('uid','wid','sid','fid','m') values(" + luid +",0,0,"+ wid +"'" + m + "')");	 
		  		// statement.executeUpdate("create table quadroo (qid INTEGER 
		//PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid 
		//INTEGER)");
		  		   		
		  		rwid = wid; reply.put("result","Done");replystatus="done";	}	
		  	
		  }
		  else 
		  {
			  //replystatus="Non Existant Word";
		     statement.executeUpdate("insert into words('uid','name','m') values(" + luid +",'"+  W+ "','" + m + "')");	 
		     wi = new FWord_S(rr).rwid; 
		  	 
		  	 // statement.executeUpdate("insert into words values(3,'starter4')");	 
		  	statement.executeUpdate("insert into quadroo('uid','wid','sid','fid','m') values(" + luid +",0,0,"+ wi + ",'"+m+"')");	 
		  	// statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY 
		  	//KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER)");
		  	   		
		  	rwid = wi;
		  	reply.put("result", "Done");
		  	replystatus=" done,new word added";  
		  }



		  } catch (Exception e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		 rid = -1;
		  }  

}
}