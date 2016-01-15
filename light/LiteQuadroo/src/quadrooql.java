import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.Statement;
//todo AFScene
//todo DFScene
//todo ASWord
//todo DSWord
// LFScene, LSWord 
public class quadrooql {
	String url = "jdbc:sqlite:sample.db";	
	String driver = "org.sqlite.JDBC"; 
	



//new ConnectionPool();;	
Statement statement = null; 
Connection connection = null;
//String url = "jdbc:sqlite:sample.db";
//quadrooql constructor defines three tables:
//users,words and quadroo
	quadrooql(String url) throws ClassNotFoundException {
		// load the sqlite-JDBC driver using the current class loader
	   // Class.forName("org.sqlite.JDBC");
	    
	    
	    
	    String luser = null;
	   
	    try
	    {
	      // create a database connection
	     // connection = DriverManager.getConnection(url);
	    		ConnectionPool connectionPool = 
	    			new ConnectionPool(driver,url,null,null,2,10,true);

	       connection = connectionPool.getConnection();
	       statement = connection.createStatement(); 
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	      
	      //users hold the user password, user is in the form of email
	   /*   
	      statement.executeUpdate("drop table if exists users");
	      statement.executeUpdate("create table users ( user string primary KEY, passwd string )");
	      
	      //uid is the rowid of the user, both tables words and quadroo records the creator in  uid .
	      //rule is only creator can update or delete the word or any quadroo entry,administrator can always come in as any user
	      statement.executeUpdate("drop table if exists nspace");
	      statement.executeUpdate("create table nspace (nid INTEGER PRIMARY KEY AUTOINCREMENT ,uid integer ,name string Unique )");
	    
	      statement.executeUpdate("drop table if exists words");
	      statement.executeUpdate("create table words (wid INTEGER PRIMARY KEY AUTOINCREMENT ,uid integer,name string Unique )");
	      statement.executeUpdate("drop table if exists quadroo");
	      statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER,m varchar(1))");
	     // statement.executeUpdate("insert into words values(2,'starter')");	 
			 
	      
	     */
	      
	    
	    }
	    catch(SQLException e)
	    {
	      // if the error message is "out of memory", 
	      // it probably means no database file is found
	      System.err.println(e.getMessage());
	    }
	   /* finally
	    {
	      try
	      {
	        if(connection != null)
	        connection.close();
	      }
	      catch(SQLException e)
	      {
	        // connection close failed.
	        System.err.println(e);
	      }  
	    }*/
	  }
public String  extractNS(String W) 	{int nsi = W.lastIndexOf(":");
if (nsi > 0) {String ns = W.substring(0,nsi+1);  return ns ;}
else return null;
}    
public String ANSFromWord(int luid, String W ){
	String ans = extractNS( W);
	if (ans != null ) { 
	if	(FNSpace(ans) == -1 ) {
		ANspace(luid, ans);	
		
	}	
	}
	return ans ;
}
public String ANspace(int  luid, String s)     {
	
	 try {
		s.trim();
		

				ResultSet rs = statement.executeQuery("select * from nspace where name =" + "'"+s +"'" );
		 if (rs.next() ) {
			 return "nspace exists"; }
		 else 
		 {  // System.out.println("just 2 values");
		 statement.executeUpdate("insert into nspace('uid','name') values(" + luid +",'"+  s+ "')");	 
				return "done"; 
			 
		 }
		
		
	 
	 
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
	
	
}

public int FNSpace(String W )     {

try {
W= W.trim();

ResultSet rs = statement.executeQuery("select * from nspace where name =" + "'"+W +"'");
 if (rs.next() ) {
	 	
		return rs.getInt("nid");
 
	 }

 else 
 {
	 
	 return -1 ;	 
 
 }

} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
return -1;
}   

} 
	
public  String DNspace(int luid,  String W )     {

try {
	W.trim();
	ResultSet rs = statement.executeQuery("select * from nspace where name =" + "'"+W +"'");
	 if (rs.next() ) {
		if(rs.getInt("uid") == luid){
		ResultSet rn = statement.executeQuery("select * from words where name like " + W +'%');  
		if ( !rn.next()) {
		 int rd = statement.executeUpdate("delete from nspace where name ='" + W +"'");	
		 return "done";
		}
		else { return "inUse"; } 
		}
		else	 
		 return ("usermismatch:"+luid+"/"+rs.getInt("uid")); }
	 else 
	 {
		 
		// statement.executeUpdate("insert into words values(1, W)");	 
		 
		return "no namespace"; 
		 
	 }
	
	


} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	return "error";
}


}






public  String AWord(int  luid, String W)     {
	
	 try {
		W.trim();
		

				ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'" );
		 if (rs.next() ) {
			 return "word exists"; }
		 else 
		 {  // System.out.println("just 2 values");
		 statement.executeUpdate("insert into words('uid','name') values(" + luid +",'"+  W+ "')");	 
		 ANSFromWord(luid, W );
		 
		 // statement.executeUpdate("insert into words values(3,'starter4')");	 
			 //System.out.println("jinserted 2 values");	
			return "done"; 
			 
		 }
		
		
	 
	 
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
	
	
}

public  String CWord(int luid, String W, String nW)     {
	
	 try {
		W.trim();
		ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'" );
		 if (rs.next() ) {
			 if(rs.getInt("uid") == luid){
			        
			 
			 statement.executeUpdate("update words  set name = '" + nW+ "' where  name = '" + W + "'");
			 ANSFromWord(luid, nW );
			 return "done"; }
		 		 else {
		 			 
		 			return ("usermismatch:"+luid+"/"+rs.getInt("uid")); }	 
		 			 
		 			 
		 		 }
		 
		
		 
		 
		 else 		 {
			 
			
			return "noword"; 
			 
		 }
		
		
	 
	 
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
	
	
}
		
public  String DWord(int luid,  String W )     {
	
	 try {
		W.trim();
		ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'");
		 if (rs.next() ) {
			if(rs.getInt("uid") == luid){
		        
			 int rd = statement.executeUpdate("delete from words where name ='" + W +"'");	
			 return "done";
			}
			 
			else	 
			 return ("usermismatch:"+luid+"/"+rs.getInt("uid")); }
		 else 
		 {
			 
			// statement.executeUpdate("insert into words values(1, W)");	 
			 
			return "noword"; 
			 
		 }
		
		
	 
	 
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
	
	
}
		
public int FWord(String W )     {
	
	 try {
		W= W.trim();
		
		ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'");
		 if (rs.next() ) {
			 	
				return rs.getInt("wid");
		 
			 }
		
		 else 
		 {
			 
			 return -1 ;	 
		 
		 }
		
		
	 
	 
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return -1;
	}   
   
} 

public String GWord(int widi )     {

try {


ResultSet rs = statement.executeQuery("select name from words where wid =" + widi);
 if (rs.next() ) {
	 	
		return rs.getString("name");
 
	 }

 else 
 {
	 
	 return null;	 
 
 }




} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
return e.getMessage();
}   

} 

public  int AScene(int luid, String W )     {

try {
W= W.trim();
int  wi = FWord (W);
//ResultSet rs = statement.executeQuery("select wid from words where name =" + "'"+W +"'");
if (wi > 0) {
	int wid = wi;
	ResultSet rsc 	= statement.executeQuery("select sid from quadroo where sid =" + wid + " AND fid=0 AND wid = 0 ");
	if (rsc.next() )  {return rsc.getInt(1);}
	else 
	{ 
		statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +",0,"+ wid +",0)");	 
		// statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER)");
		   		
		return wid;
	}	
	
}
else 
{
	
   statement.executeUpdate("insert into words('uid','name') values(" + luid +",'"+  W+ "')");	 
   wi = FWord (W);
	 
	 // statement.executeUpdate("insert into words values(3,'starter4')");	 
	statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +",0,"+ wi +",0 )");	 
	// statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER)");
	   		
	return wi;
	 
}





} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
return -1;
}   

} 
public  String DScene(int luid,  String W )     {
	
	 try {
		W.trim();
		ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'");
		 if (rs.next() ) {
			if(rs.getInt("uid") == luid){
				int wi = rs.getInt("wid");
				ResultSet rsc 	= statement.executeQuery("select qid,sid from quadroo where sid = " + wi+ "  AND fid=0 AND wid = 0 ");
				if (rsc.next() )  {int qi = rsc.getInt("qid");
				int rd = statement.executeUpdate("delete from quadroo where qid =" + qi);	
				 return "done";
				}
				else   
			  return "scene not found";
			}
			 
			else	 
			 return ("usermismatch:"+luid+"/"+rs.getInt("uid")); }
		 else 
		 {
			 
			// statement.executeUpdate("insert into words values(1, W)");	 
			 
			return "noword"; 
			 
		 }
		
		
	 
	 
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
	
	
}
		

public int FScene(String W )     {

try {
W= W.trim();
int  wi = FWord (W);
//ResultSet rs = statement.executeQuery("select wid from words where name =" + "'"+W +"'");
if (wi > 0) {
	int wid = wi;
	ResultSet rsc 	= statement.executeQuery("select  qid from quadroo where sid =wid AND fid=0 AND wid = 0 ");
	if (rsc.next() )  {return  rsc.getInt("qid") ;}
	else 
	{ 
			   		
		return  -1 ;	}	
	
}
else 
{return  -1 ;	}


} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
return -1;
}   

} 
public  int AFlow(int luid, String W )     {

try {
W= W.trim();
int  wi = FWord (W);
//ResultSet rs = statement.executeQuery("select wid from words where name =" + "'"+W +"'");
if (wi > 0) {
	int wid = wi;
	ResultSet rsc 	= statement.executeQuery("select fid from quadroo where fid = " + wid + " AND sid=0 AND wid = 0 ");
	if (rsc.next() )  {return rsc.getInt(1) ;}
	else 
	{ 
		statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +",0,0,"+ wid +")");	 
		// statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER)");
		   		
		return wid;	}	
	
}
else 
{
	
   statement.executeUpdate("insert into words('uid','name') values(" + luid +",'"+  W+ "')");	 
   wi = FWord (W);
	 
	 // statement.executeUpdate("insert into words values(3,'starter4')");	 
	statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +",0,0,"+ wi +")");	 
	// statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER)");
	   		
	return wi;
	 
}





} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
return -1;
}   

} 
public  String DFlow(int luid,  String W )     {
	
	 try {
		W.trim();
		ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'");
		 if (rs.next() ) {
			if(rs.getInt("uid") == luid){
				int wi = rs.getInt("wid");
				ResultSet rsc 	= statement.executeQuery("select qid from quadroo where fid = " + wi+ "  AND sid=0 AND wid = 0 ");
				if (rsc.next() )  {int qi = rsc.getInt("qid");
				int rd = statement.executeUpdate("delete from quadroo where qid =" + qi);	
				 return "done";
				}
				else   
			  return "flow not found";
			}
			 
			else	 
			 return ("usermismatch:"+luid+"/"+rs.getInt("uid")); }
		 else 
		 {
			 
			// statement.executeUpdate("insert into words values(1, W)");	 
			 
			return "noword"; 
			 
		 }
		
		
	 
	 
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
	
	
}
		
public int FFlow(String W )     {

try {
W= W.trim();
int  wi = FWord (W);
//ResultSet rs = statement.executeQuery("select wid from words where name =" + "'"+W +"'");
if (wi > 0) {
	int wid = wi;
	ResultSet rsc 	= statement.executeQuery("select  qid from quadroo where fid = " + wid  + " AND sid=0 AND wid = 0 ");
	if (rsc.next() )  {return  rsc.getInt("qid") ;}
	else 
	{ 
			   		
		return  -1 ;	}	
	
}
else 
{return  -1 ;	}


} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
return -1;
}   

} 
public  String AFlowScene(int luid,String F, String W )     {

	int fidi = AFlow(luid,F) ;
return 	AFScene(luid,fidi, W );
	
}



public  String AFScene(int luid,int fidi, String W )     {

try {
W= W.trim();
int  wi = FWord (W);
//ResultSet rs = statement.executeQuery("select wid from words where name =" + "'"+W +"'");
if (wi > 0) {
	int wid = wi;
	ResultSet rsc 	= statement.executeQuery("select sid from quadroo where sid = "+  wid + " AND fid=0 AND wid = 0 ");
	if (rsc.next() )  {
		ResultSet rsf 	= statement.executeQuery("select sid from quadroo where sid =" + wid + " AND fid= "+ fidi );
			
		
		if (rsf.next() )  {	
		return "scene exists in flow" ;}
		else {	
			ResultSet rsfm 	= statement.executeQuery("select MAX(wid) from quadroo where  fid= " + fidi );
		    int wp = rsfm.getInt(1);
		    wp = wp + 1;
			statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +","+wp+","+ wid +","+fidi+")");	 
			return "done";
		} 
	}

	else 
	{ 
		statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +",0,"+ wid +",0)");	 
		ResultSet rsfm 	= statement.executeQuery("select MAX(wid) from quadroo where  fid= " + fidi );
	    int wp = rsfm.getInt(1);
	    wp = wp + 1;
		statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +","+wp+","+ wid +","+fidi+")");	 
	  		
		return "done";
	}	
	
}
else 
{
	
   statement.executeUpdate("insert into words('uid','name') values(" + luid +",'"+  W+ "')");	 
   wi = FWord (W);
	 
	 // statement.executeUpdate("insert into words values(3,'starter4')");	 
	statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +",0,"+ wi +",0 )");	 
	// statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER)");
	ResultSet rsfm 	= statement.executeQuery("select MAX(wid) from quadroo where  fid= " + fidi );
    int wp = rsfm.getInt(1);
    wp = wp + 1;
	
	statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +","+wp+","+ wi +","+fidi+")");	 
  	  		
	return "done";
	 
}





} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
return "error";
}   

} 		

public  String DFScene(int luid,int fidi, String W)     {

try {
W= W.trim();
int  wi = FWord (W);
if (wi > 0) {
	int wid = wi;
	ResultSet rsc 	= statement.executeQuery("select sid from quadroo where sid = " + wid+ " AND fid=0 AND wid = 0 ");
	if (rsc.next() )  {
		ResultSet rsf 	= statement.executeQuery("select qid from quadroo where sid = " + wid + " AND fid= " + fidi);
		
		if (rsf.next() )  {	
			int qi = rsf.getInt("qid");
			
			int rd = statement.executeUpdate("delete from quadroo where qid =" + qi);
			
		return "done" ;}
		else {	
			
			return "no scene in flow";
		} 
	}

	else 
	{ 
		return "no scene";
	}	
	
}
else 
{
		return "no word";
	 
}

} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
return "error";
}   

} 		
public  String DFSid(int luid,int fidi, int wi)     {

try {
 {
	int wid = wi;
	ResultSet rsc 	= statement.executeQuery("select sid from quadroo where sid = " + wid + " AND fid=0 AND wid = 0 ");
	if (rsc.next() )  {
		ResultSet rsf 	= statement.executeQuery("select qid from quadroo where sid = " + wid + " AND fid= " + fidi );
			if (rsf.next() )  {	
			int qi = rsf.getInt("qid");
			
			int rd = statement.executeUpdate("delete from quadroo where qid =" + qi);
			
		return "done" ;}
		else {	
			
			return "no scene in flow";
		} 
	}

	else 
	{ 
		return "no scene";
	}	
}
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
return "error";
}   

} 	

public  String ASceneWord(int luid,String S, String W )     {

	int sidi = AScene(luid,S) ;
return 	ASWord(luid,sidi, W );
	
}


public  String ASWord(int luid,int sidi, String W )     {

try {
W= W.trim();
int  wi = FWord (W);
//ResultSet rs = statement.executeQuery("select wid from words where name =" + "'"+W +"'");
if (wi > 0) {
	int wid = wi;
	ResultSet rsc 	= statement.executeQuery("select sid from quadroo where sid = " + sidi + " AND fid=0 AND wid = 0 ");
	if (rsc.next() )  {
		
		statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +","+wid+","+ sidi +","+"0)");	 
		
		
		return "done";
		} 
	

	else 
	{ return "no scene id";
		
	}	
	
}
else 
{
	
   statement.executeUpdate("insert into words('uid','name') values(" + luid +",'"+  W+ "')");	 
   wi = FWord (W);
	 
	 // statement.executeUpdate("insert into words values(3,'starter4')");	 
	statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +","+wi+","+sidi+",0 )");	 
	// statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER)");
	   		
	return "done";
	 
}

} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
return "error";
}   

} 		

public  String DSWord(int luid,int sidi, String W)     {

try {
W= W.trim();
int  wi = FWord (W);
if (wi > 0) {
	int wid = wi;
	ResultSet rsc 	= statement.executeQuery("select qid from quadroo where sid =" + sidi+ " AND fid=0 AND wid = " + wi );
	//can check for sceneid
	if (rsc.next() )  {
		
			int qi = rsc.getInt("qid");
			
			int rd = statement.executeUpdate("delete from quadroo where qid =" + qi);
			
		return "done" ;}
		else {	
			
			return "no word in scene";
		} 
	}

	
else 
{
		return "no word";
	 
}

} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
return "error";
}   

} 		

public  String LSWords( String W)     {

try {
W= W.trim();
int  wi = FWord (W);
if (wi > 0) {
	int wid = wi;
	ResultSet rsc 	= statement.executeQuery("select wid from quadroo where sid = " + wid + " AND fid=0 AND wid = 0 ");
	if (rsc.next() )  { String xml = "<scene name = " + W + ">";
	                     wi = rsc.getInt("wid");
	                     String wrd = GWord(wi);
	                     xml = xml + "<word name=" + wrd+ ">";  
	
		while (rsc.next()  ){
			wi = rsc.getInt("wid");
            wrd = GWord(wi);
            xml = xml + "<word name=" + wrd+ ">";  	
			
			
		}
		xml = xml + "</xml>" ;
		
		return xml ;
	}

	else 
	{ 
		return "no scene with that name";
	}	
	
}
else 
{
		return "scene name not in dictionary";
	 
}

} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
return "error";
}   

} 	
public  String LFScenes( String W)     {

try {
W= W.trim();
int  wi = FWord (W);
String wrd;
if (wi > 0) {
	int wid = wi;
	//System.out.println(" flow word:"+ wi);
	//select sid from quadroo where  fid=" +wi + " AND sid > 0 ; 
	ResultSet rsf 	= statement.executeQuery("select sid  from quadroo where  fid=" +wi );
	if (rsf.next() )  { String xml = "<flow name = " + W + ">";
	                     wi = rsf.getInt("sid");
	                     System.out.println(" for sid:"+ wi);
	                     wrd = GWord(wi);
	                     xml = xml + "<scene name=" + wrd+ ">";  
	
		while (rsf.next()  ){
			 System.out.println(" for2 sid:"+ wi);
			wi = rsf.getInt("sid");
            wrd = GWord(wi);
            xml = xml + "<scene name=" + wrd+ ">";  	
			
			
		}
		xml = xml + "</flow>" ;
		
		return xml ;
	}

	else 
	{ 
		return "no flow with that name";
	}	
	
}
else 
{
		return "flow name not in dictionary";
	 
}

} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
return "error";
}   

} 	

public static void main(String[] args) throws ClassNotFoundException { 
	String url = "jdbc:sqlite:qudtest.db";
	quadrooql ql = new quadrooql(url);
	//ql.AWord(1, "firstword");
	ql.AWord(1, "morning");
	ql.AWord(1, "goodmorning");
	System.out.println("first time:"+ql.AWord(1, "firstword"));
	System.out.println("second time:"+ql.AWord(1, "firstword"));
	System.out.println("Change result:"+ql.CWord(1,"morning","evening"));
	System.out.println("Change result 2nd time:"+ql.CWord(1,"morning","evening"));
	System.out.println("Delete result :"+ql.DWord(4,"evening"));
	System.out.println("Find result :"+ql.FWord("evening"));
	System.out.println("Find result :"+ql.FWord("sianara"));
	System.out.println("Scene intro:"+ql.AScene(1, "firstword"));
	System.out.println("Scene intro:"+ql.AScene(1, "secondword"));
	System.out.println("Scene virgin:"+ql.AScene(1, "virginword"));
	System.out.println("Scene find:"+ql.FScene( "virginword"));
	System.out.println("Scene delete firstword:"+ql.DScene(1, "firstword"));
	System.out.println("namespace from word:"+ql.extractNS("a:b:c:monkey"));
	System.out.println("Flow intro:"+ql.AFlow(1, "secondword"));
	System.out.println("Flow virgin:"+ql.AFlow(1, "virginword1"));
	System.out.println("Flow find fail:"+ql.FFlow( "virginword"));
	System.out.println("Flow find success:"+ql.FFlow( "virginword1"));
	System.out.println("Flow list scenes:"+ql.LFScenes( "virginword1"));
	System.out.println("Flow delete  no firstword:"+ql.DFlow(1, "firstword"));
	System.out.println("Flow delete  secondword:"+ql.DFlow(1, "secondword"));
	System.out.println("Flow find after delete  secondword:"+ql.FFlow("secondword"));

	System.out.println("Scene Add to flow virginword1:"+ql.AFScene(1, 6,"secondword"));
	System.out.println("Scene Add to flow new word for scene:"+ql.AFScene(1, 6,"newword"));
	System.out.println("Word Add to scene new pie:"+ql.ASWord(1, 7,"pie"));
	System.out.println("Scene Add to scene virgin first:"+ql.ASWord(1, 4,"firstword"));
	System.out.println("Flow list scenes:"+ql.LFScenes( "virginword1"));

	
	}
}