//package coreservlets;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONObject;

import java.sql.*;
import java.util.Iterator;

/*quadrroql servlet is able to invoke all methods depending on the URL */

public class QuadrooqlS extends HttpServlet {
	
 public  ConnectionPool connectionPool;
  Statement statement = null; 
 Connection connection = null;
  Quadroopl reqparms;
  int rr = -9;  String verb;
  // all methods we use  have no argument
  Class params[] = {};
  Object paramsObj[] = {};
  
 // doget invokes methods matching the verb extracted from URI
  
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    // a request parameter holder and the result reply holder
	  Quadroopl reqp = new Quadroopl();
	  Quadroorl repl = new Quadroorl();
	  
	  
    try {     
      
    	//extract verb and parameters:
    	//the last part of URI is the verb or the method name
        String ruri =   request.getRequestURI();
        int ms = ruri.lastIndexOf("/");
        
        if ((ms > 0) && (ms < ruri.length()))  {
         verb = ruri.substring(ms+1);}
               
        System.out.println("encounterd"+ verb+ ":"+request.getParameter("UIdata"));
        
        //next we extract parameters and hold that in the request scope
        
        //the input parameter UIdata  holds an json string 
        //http://localhost:8080/quadrooServer/quadrooql/extractNS?UIdata={W:a.b.c.aword}
        JSONObject jObj = new JSONObject(request.getParameter("UIdata")); // this parses the json
        Iterator it = jObj.keys();
        //gets all the keys

    
  	 while(it.hasNext())
     {
         String key = (String)it.next(); // get key
         Object o = jObj.get(key); // get value
        // session.putValue(key, o); // store in session
         System.out.println("json"+ key + "val:" + o.toString());
       
         
         if (key.equalsIgnoreCase("F"))
             reqparms.F = o.toString();
         
         if (key.equalsIgnoreCase("N"))
             reqp.N = o.toString();
         if (key.equalsIgnoreCase("S"))
             reqp.W = o.toString();
         if (key.equalsIgnoreCase("U"))
             reqp.W = o.toString();
         if (key.equalsIgnoreCase("uid"))
             reqp.uid =Integer.parseInt(o.toString());
         if (key.equalsIgnoreCase("W"))
        	 reqp.W = o.toString();
         if (key.equalsIgnoreCase("nW"))
             reqp.nW = o.toString();
         if (key.equalsIgnoreCase("m"))
             reqp.m = o.toString();
     } 

  	 //
  	 
   	System.out.println("Verb Is"+ verb+"invokedby:"+reqp.W);   
    //invoke the method	
 /*     	 switch (verb) {
         case  "AWord":
        	 repl = AWord(reqp);  break;
         case  "clearTables":
       	  repl.reply = clearTables(); break;
     	 case  "extractNS":
 	          repl = extractNS(reqp); break;
	     case  "ANSFromWord":
 	          repl = ANSFromWord(reqp); break;
	     case  "FNSpace":
 	          repl = FNSpace(reqp); break;
	     case  "ANspace":
 	          repl = ANspace(reqp); break;
	     case  "DNspace":
 	          repl = DNspace(reqp); break;
		 case  "CWord":
 	          repl = CWord(reqp); break;
		case  "DWord":
 	          repl = DWord(reqp); break;
		case  "FWord":
 	          repl = FWord(reqp);  break;
		case  "GWord":
 	          repl = GWord(reqp);  break;
	    case  "AScene":
 	          repl = AScene(reqp); break;
	    case  "DScene":
 	          repl = DScene(reqp);  break;
	    case  "FScene":
 	          repl = FScene(reqp); break;
	    case  "AFlow":
 	          repl = AFlow(reqp); break;
		case  "DFlow":
 	          repl.reply = DFlow(reqp); break;
		case  "AFlowScene":
 	          repl = AFlowScene(reqp); break;
		case  "AFScene":
 	          repl = AFScene(reqp); break;
		case  "DFScene":
 	          repl = DFScene(reqp);  break;
		case  "DFSid":
 	          repl= DFSid(reqp);  break;
		case  "ASceneWord":
 	          repl = ASceneWord(reqp); break;
 	 
		case  "ASWord":
 	          repl = ASWord(reqp);  break;
		case  "DSWord":
 	          repl = DSWord(reqp); break;
		case  "LSWords":
 	          repl = LSWords(reqp); break;
		case  "LFScenes":
 	          repl.reply = LFScenes(reqp); break;
		case  "initialConnections":
 	          repl.rid = initialConnections();
		case  "maxConnections":
 	          repl.rid = maxConnections();      
 	  }
  	  
    */      	
      //having executed the sql with the request, we can release the connection so that
     // other connections can use it.
 // String rt =  makeResponse(repl);
      	 
      connectionPool.free(connection);
      System.out.println("why error");  
    } catch(Exception e) {e.printStackTrace();System.out.println("error x :"+ e.getMessage());
     
    }
    String rt =  makeResponse(repl);
    System.out.println("progressing:"+reqp.W);  
    //Time to Set up the reply Text
    response.setContentType("text/xml");
    // Prevent the browser from caching the response. See
    // Section 7.2 of Core Servlets and JSP for details.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
    PrintWriter out = response.getWriter();
 //   String title = "Connection Pool Test";
    
 //   String rt = "<reply>" + repl.reply + "</reply> ";
    System.out.println("put out:"+rt); 
    out.write(rt);
    //return(rt);
  }

public String makeResponse(Quadroorl rl) {
	String rt = 
	"<Qreply>" + 
	"<replyint>"+rl.rid+ 
	"</replyint>" + 
	"<reply>"+rl.reply+ 
	"</reply>" + 
	"<rfid>"+rl.rfid+ 
	"</rfid>" + 
	"<rf>"+rl.rf+ 
	"</rf>"+ 
	"<rs>"+rl.rs+ 
	"</rs>"+ 
	"<rsid>"+rl.rsid+ 
	"</rsid>"+ 
	"<rw>"+rl.rw+ 
	"</rw>"+ 
	"<rwid>"+ rl.rwid +
	"</rwid>"+ 
	"<rnw>"+ rl.rnw +
	"</rnw>"+ 
	"<rnwid>"+ rl.rnwid +
	"</rnwid>"+ 
	
	"</Qreply> ";
	
	
	
	return rt ; } 
  
  public void init() {
	  //init is the place to set up url and connection details
	  String url = "jdbc:sqlite:/qudtes2t.db";	
		String driver = "org.sqlite.JDBC"; 
		

	    try
	    {
	      // create a database connection
	     // connection = DriverManager.getConnection(url);
	    		 connectionPool = 
	    			new ConnectionPool(driver,url,null,null,2,10,true);

	    //   connection = connectionPool.getConnection();
	
} catch(SQLException sqle) {
  System.err.println("Error making pool: " + sqle);
  getServletContext().log("Error making pool: " + sqle);
  connectionPool = null;
}
	    catch(Exception e) {  System.out.println(e.getMessage()) ;
	    e.printStackTrace();
	    }
		try {
			connection = connectionPool.getConnection();
		statement = connection.createStatement(); 
	    statement.setQueryTimeout(30);  // set timeout to 30 sec.
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    

   // reqparms = new Quadroopl();
	//new ConnectionPool();;	
	
	//String url = "jdbc:sqlite:sample.db";
	//quadrooql constructor defines four tables:
	//users,words and quadroo,nspace
		
    clearTables()	;   
		
  }

  public void destroy() {
    connectionPool.closeAllConnections();
  }

  /** Override this in subclass to change number of initial
   *  connections.
   */
  
  protected int initialConnections() {
    return(10);
  }

  /** Override this in subclass to change maximum number of 
   *  connections.
   */

  protected int maxConnections() {
    return(50);
  }
  
  //here all the quadroo methods defined
  //clear all tables and rebuild
  public String  clearTables() { 
 //users hold the user password, user is in the form of email
   try    
   {
      statement.executeUpdate("drop table if exists users");
      statement.executeUpdate("create table users ( user string primary KEY, passwd string )");
      System.out.println("users");
    	
      //uid is the rowid of the user, both tables words and quadroo have the creator in  uid .
      //rule is only creator can update or delete the word or any quadroo entry,administrator can always come in as any user
      statement.executeUpdate("drop table if exists nspace");
      statement.executeUpdate("create table nspace (nid INTEGER PRIMARY KEY AUTOINCREMENT ,uid integer ,name string Unique )");
      System.out.println("nspace");
      statement.executeUpdate("drop table if exists words");
      statement.executeUpdate("create table words (wid INTEGER PRIMARY KEY AUTOINCREMENT ,uid integer,name string Unique, m varchar(1) )");
      statement.executeUpdate("drop table if exists quadroo");
      //quadroo is the measure we  are storing in the data base;only word by itself measure is just the description of the word,significance of its name;
      //word in scene has the measure as the value of the word; in that sense word can not have value without being in a scene.
      //flow is the story about how the value changes through scenes.
      //only flow and only scene may have measure where they have description about the scene and the flow resp.
      statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER,m varchar(1))");
     rr =  statement.executeUpdate("insert into words(uid,name) values(2,'starter')");	 
		 
     System.out.println("inserted words");
    
      return ("tables recreated");
     
  } 
   catch(Exception e)
   {System.out.println("error x :"+ e.getMessage());
   
	  
	  
	  
	  return e.getMessage() ; } }
  
 // public String  extractNS(String W)
  public Quadroorl  extractNS(Quadroopl reqparms)
  { String W = reqparms.W;
  Quadroorl repl = new Quadroorl();
  System.out.println("invoked extractns:"+W);
	  int nsi = W.lastIndexOf(".");
  if (nsi > 0) {String ns = W.substring(0,nsi+1); repl.rn = ns; repl.reply= ns ;}
  
  else  repl.reply =  "nonamespace";
	return repl;	 
  }    
  public Quadroorl ANSFromWord(Quadroopl reqparms){
  	int luid  = reqparms.uid;
  	String W =   reqparms.W;
  	Quadroorl ans = extractNS( reqparms);
  	reqparms.N = ans.rnw;
  	if (ans != null ) { 
  	if	((FNSpace(reqparms)).rid == -1 ) {
  		ANspace(reqparms);	
  		
  	}	
  	}
  	return ans ;
  }
  public Quadroorl ANspace(Quadroopl reqparms)     {
	  Quadroorl repl = new Quadroorl();
	  int  luid = reqparms.uid;
	  String s =  reqparms.S;
	  //only ns is not a word
  	 try {
  		s.trim();
  		

  				ResultSet rs = statement.executeQuery("select * from nspace where name =" + "'"+s +"'" );
  		 if (rs.next() ) {
  			 repl.reply = "nspace exists";
  			 
  			 
  		//	 return
  					 
  					  }
  		 else 
  		 {  // System.out.println("just 2 values");
  		 statement.executeUpdate("insert into nspace('uid','name') values(" + luid +",'"+  s+ "')");	 
  				repl.reply = "done"; 
  			 
  		 }
  		
  		return repl;
  	 
  	 
  	 } catch (SQLException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  		repl.reply  ="error";
  		return repl;
  	}
  	
  	
  }

  public Quadroorl  FNSpace(Quadroopl reqparms )     {
	  
	  Quadroorl repl = new Quadroorl();
	  String W =  reqparms.W;
  try {
  W= W.trim();

  ResultSet rs = statement.executeQuery("select * from nspace where name =" + "'"+W +"'");
   if (rs.next() ) {
  	 	
  		repl.rid = rs.getInt("nid");
  		repl.reply ="Done";
   
  	 }

   else 
   {
	   repl.rid = -1;
 		repl.reply ="Error";
  		 
   
   }
    return repl;

  } catch (SQLException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
  repl.rid = -1;
  return repl;
  
  }   

  } 
  	
  public  Quadroorl DNspace(Quadroopl reqparms)     {
	  Quadroorl repl = new Quadroorl();
	  String W =  reqparms.W;
	  int luid = reqparms.uid;
  try {
  	W.trim();
  	ResultSet rs = statement.executeQuery("select * from nspace where name =" + "'"+W +"'");
  	 if (rs.next() ) {
  		if(rs.getInt("uid") == luid){
  		ResultSet rn = statement.executeQuery("select * from words where name like " + W +'%');  
  		if ( !rn.next()) {
  		 int rd = statement.executeUpdate("delete from nspace where name ='" + W +"'");	
  		 repl.rid = rd;
  		 repl.reply = "done";
  		}
  		else { repl.reply = "inUse"; } 
  		}
  		else	 
  		  repl.reply = "usermismatch:"+luid+"/"+rs.getInt("uid"); }
  	 else 
  	 {
  		 
  		// statement.executeUpdate("insert into words values(1, W)");	 
  		 
  		repl.reply  = "no namespace"; 
  		 
  	 }
  	
  	return repl;


  } catch (SQLException e) {
  	// TODO Auto-generated catch block
  	e.printStackTrace();
  	repl.reply ="error" + e.getMessage();
  	return repl;
  }


  }






  public  Quadroorl AWord(Quadroopl reqparms)     {
	  Quadroorl repl  =  new Quadroorl();
	  String W =  reqparms.W;
	  int luid = reqparms.uid;
	  String m = reqparms.m;
  	 try {
  		W.trim();
  		 System.out.println("in AWord:" +W);
 
  				ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'" );
  		 if ((rs != null) &&  (rs.next()) ) {
  			 repl.reply = "word exists";
  			 return repl; }
  		 else 
  		 {  // System.out.println("just 2 values");
  		 statement.executeUpdate("insert into words('uid','name','m') values( " + luid +",'" +  W +"','"+ m +" ') " );	
  		// ANSFromWord( );
  		 
  		 // statement.executeUpdate("insert into words values(3,'starter4')");	 
  			 //System.out.println("jinserted 2 values");
  		repl.reply = "done";
  			return repl; 
  			 
  		 }
  		
  
  	 } catch (SQLException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  		repl.reply = "error:" + e.getMessage();
  		return repl;
  	}
  	
  	
  }

  public  Quadroorl CWord(Quadroopl reqparms)     {
	  Quadroorl repl  =  new Quadroorl();
	  String W =  reqparms.W;
	  String nW =reqparms.nW;
	 int luid = reqparms.uid;
  	 try {
  		W.trim();
  		ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'" );
  		 if (rs.next() ) {
  			 if(rs.getInt("uid") == luid){
  			        
  			 
  			 statement.executeUpdate("update words  set name = '" + nW+ "' where  name = '" + W + "'");
  			 ANSFromWord(reqparms );
  			repl.reply = "done";
  			 return repl; }
  		 		 else {
  		 			 repl.reply = "usermismatch:"+luid+"/"+rs.getInt("uid");
  		 			return repl; }	 
  		 			 
  		 			 
  		 		 }
  		 
  		
  		 else 		 {
  			 
  			 repl.reply = "noword";
  			return repl; 
  			 
  		 }
  		
  		
  	 
  	 
  	 } catch (SQLException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  		repl.reply = "error" + e.getMessage();
  		return repl;
  	}
  	
  	
  }
  		
  public  Quadroorl DWord(Quadroopl reqparms )     {
	  Quadroorl repl = new Quadroorl();
	  String W =  reqparms.W;
	 	 int luid = reqparms.uid;
  	 try {
  		W.trim();
  		ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'");
  		 if (rs.next() ) {
  			               if(rs.getInt("uid") == luid){
  		        
  			                      int rd = statement.executeUpdate("delete from words where name ='" + W +"'");	
  			 
  			                       repl.reply = "done";
  			 
  			              }
  			 
  			                else	
  			                  {	repl.reply ="usermismatch:"+luid+"/"+rs.getInt("uid");} 
  		                          }
  		 else 
  		 {
  			 
  			// statement.executeUpdate("insert into words values(1, W)");	 
  			 
  			repl.reply =  "noword"; 
  			 
  		  		
  		
  		 
  	 
  	 
  	      } 
  		 
  		return repl; 
  		 
  	 }
  		 
  		 catch (SQLException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  		repl.reply = "error;" + e.getMessage();
  		return repl;
  	}
  	
  	
  }
  		
  public Quadroorl  FWord(Quadroopl reqparms)     {
	  String W =  reqparms.W;
	  Quadroorl repl = new Quadroorl();
	 	// int luid = reqparms.uid;
  	 try {
  		W= W.trim();
  		
  		ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'");
  		 if (rs.next() ) {
  			 	
  				repl.rwid =  rs.getInt("wid");
  				repl.reply = "Done";
  		 
  			 }
  		
  		 else 
  		 {
  			 
  			 repl.rid = -1 ;	 
  			repl.reply = "error";
  		 }
  		
  		return repl;
  	 
  	 
  	 } catch (SQLException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  		repl.reply = e.getMessage();
  		return repl;
  	}   
     
  } 

  public Quadroorl  GWord(Quadroopl reqparms )     {
	  Quadroorl repl = new Quadroorl();
	 	 int widi = reqparms.wid;;
  try {


  ResultSet rs = statement.executeQuery("select name from words where wid =" + widi);
   if (rs.next() ) {
  	 	
  		repl.rw=  rs.getString("name");
  		repl.reply = "Done";
   
  	 }

   else 
   {
	   repl.reply = "not found";
  	 return null;	 
   
   }

 return repl;


  } catch (SQLException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
  repl.reply = "error:" + e.getMessage();
  return repl;
  }   

  } 

  public Quadroorl  AScene( Quadroopl reqparms)     {
	 Quadroorl repl = new Quadroorl();
	  String W =  reqparms.S;
	 	 int luid = reqparms.uid;
	 	 String m = reqparms.m;
  try {
  W= W.trim();reqparms.W = W;
  int  wi = (FWord ( reqparms)).rwid;
  //ResultSet rs = statement.executeQuery("select wid from words where name =" + "'"+W +"'");
  if (wi > 0) {
  	int wid = wi;
  	ResultSet rsc 	= statement.executeQuery("select sid from quadroo where sid =" + wid + " AND fid=0 AND wid = 0 ");
  	if (rsc.next() )  {repl.rsid = rsc.getInt(1); repl.reply = "Done";}
  	else 
  	{ 
  		statement.executeUpdate("insert into quadroo('uid','wid','sid','fid','m') values(" + luid +",0,"+ wid +",0,'"+m+"')");	 
  		// statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER)");
  		repl.rsid   = wid; 	 	
  		//return wid;
  	}	
  	
  }
  else 
  {
  	
     statement.executeUpdate("insert into words('uid','name','m') values(" + luid +",'"+  W+ "','" +m + "')");	 
     wi = (FWord (reqparms)).rwid;
  	 
  	 // statement.executeUpdate("insert into words values(3,'starter4')");	 
  	statement.executeUpdate("insert into quadroo('uid','wid','sid','fid','m') values(" + luid +",0,"+ wi +",0,'"+m+"')");	 
  	// statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER)");
  	   		
  repl.rwid = wi;
  	 
  }
 return repl;
  } catch (SQLException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
  repl.rid = -1;
  repl.reply = "error:" + e.getMessage();
  return repl;
  }   

  } 
  public Quadroorl DScene(Quadroopl reqparms )     {
	  Quadroorl repl = new Quadroorl();
	  String W =  reqparms.S;
	 	 int luid = reqparms.uid;
  	 try {
  		W.trim();
  		ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'");
  		 if (rs.next() ) {
  			if(rs.getInt("uid") == luid){
  				int wi = rs.getInt("wid");
  				ResultSet rsc 	= statement.executeQuery("select qid,sid from quadroo where sid = " + wi+ "  AND fid=0 AND wid = 0 ");
  				if (rsc.next() )  {int qi = rsc.getInt("qid");
  				int rd = statement.executeUpdate("delete from quadroo where qid =" + qi);	
  				repl.reply = "Done";
  				//return "done";
  				}
  				else   
  					repl.reply =  "scene not found";
  			}
  			 
  			else	 
  				repl.reply  = "usermismatch:"+luid+"/"+rs.getInt("uid"); }
  		 else 
  		 {
  			 
  			// statement.executeUpdate("insert into words values(1, W)");	 
  			 
  			repl.reply =  "noword"; 
  			 
  		 }
  		
  		return repl;
  	 
  	 
  	 } catch (SQLException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  		//return "error";
  		repl.reply = "error" + e.getMessage();
  		return repl;
  	}
  	
  	
  }
  		

  public Quadroorl FScene(Quadroopl reqparms )     {
	  Quadroorl repl = new Quadroorl();
	  String W =  reqparms.W;
	 	 int luid = reqparms.uid;
  try {
  W= W.trim();
  int  wi = (FWord (reqparms)).rwid;
  //ResultSet rs = statement.executeQuery("select wid from words where name =" + "'"+W +"'");
  if (wi > 0) {
  	int wid = wi;
  	ResultSet rsc 	= statement.executeQuery("select  qid from quadroo where sid =wid AND fid=0 AND wid = 0 ");
  	if (rsc.next() )  {repl.rsid = rsc.getInt("qid") ; repl.reply="Done";}
  	else 
  	{ 
  			   		
  		repl.reply="error" ;
  		repl.rid = -1;}	
  	
  }
  else 
  {repl.reply="error" ;
	repl.rid = -2;	}

return repl;
  } catch (SQLException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
 repl.rid = -9;
 repl.reply = "error" + e.getMessage();
 return repl;
  }   

  } 
  public  Quadroorl  AFlow(Quadroopl reqparms )     {
	  
	 Quadroorl repl = new Quadroorl();
	  String W =  reqparms.W;
	 	 int luid = reqparms.uid;
	 String m = reqparms.m;	 
  try {
  W= W.trim();
  int  wi = (FWord (reqparms)).rwid;
  //ResultSet rs = statement.executeQuery("select wid from words where name =" + "'"+W +"'");
  if (wi > 0) {
  	int wid = wi;
  	ResultSet rsc 	= statement.executeQuery("select fid from quadroo where fid = " + wid + " AND sid=0 AND wid = 0 ");
  	if (rsc.next() )  {repl.rid=  rsc.getInt(1) ; repl.reply = "exists"; }
  	else 
  	{ 
  		statement.executeUpdate("insert into quadroo('uid','wid','sid','fid','m') values(" + luid +",0,0,"+ wid +"'" + m + "')");	 
  		// statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER)");
  		   		
  		repl.rwid = wid; repl.reply="Done";	}	
  	
  }
  else 
  {
  	
     statement.executeUpdate("insert into words('uid','name','m') values(" + luid +",'"+  W+ "','" + m + "')");	 
     wi = (FWord (reqparms)).rwid;
  	 
  	 // statement.executeUpdate("insert into words values(3,'starter4')");	 
  	statement.executeUpdate("insert into quadroo('uid','wid','sid','fid', 'm') values(" + luid +",0,0,"+ wi + ",'"+m+"')");	 
  	// statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER)");
  	   		
  	repl.rwid = wi;
  	repl.reply = "Done";
  	
  	 
  }


return repl;


  } catch (SQLException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
 repl.rid = -1;
 return repl;
  }   

  } 
  public  String DFlow(Quadroopl reqparms )     {
	   String W =  reqparms.W;
	 	 int luid = reqparms.uid;
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
  		
  public Quadroorl  FFlow(Quadroopl reqparms )     {
	  Quadroorl repl = new Quadroorl(); 
	  String W =  reqparms.W;
	//	 int luid = reqparms.uid;
  try {
  W= W.trim();
  int  wi = (FWord (reqparms)).rwid;
  //ResultSet rs = statement.executeQuery("select wid from words where name =" + "'"+W +"'");
  if (wi > 0) {
  	int wid = wi;
  	ResultSet rsc 	= statement.executeQuery("select  qid from quadroo where fid = " + wid  + " AND sid=0 AND wid = 0 ");
  	if (rsc.next() )  {repl.rfid =  rsc.getInt("qid") ; }
  	else 
  	{ 
  			   		
  		repl.rid =-1; repl.reply = "error";	}	
  	
  }
  else 
  {repl.rid = -1; repl.reply ="error";	}

return repl;
  } catch (SQLException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
  repl.rid = -1; repl.reply="error:" + e.getMessage();
  return repl;
  }   

  } 
  public Quadroorl  AFlowScene(Quadroopl reqparms )     {
	  String F =  reqparms.F;
	  String W = reqparms.W;
	  int luid = reqparms.uid;
  	int fidi = ( AFlow(reqparms)).rfid ;
  return 	(AFScene(reqparms) );
  	
  }



  public  Quadroorl AFScene(Quadroopl reqparms)     {
	  Quadroorl repl = new Quadroorl();
	  String W =  reqparms.W;
	 	 int luid = reqparms.uid;
	 	 int fidi = reqparms.fid;
  try {
  W= W.trim();
  int  wi = (FWord (reqparms)).rwid;
  //ResultSet rs = statement.executeQuery("select wid from words where name =" + "'"+W +"'");
  if (wi > 0) {
  	int wid = wi;
  	ResultSet rsc 	= statement.executeQuery("select sid from quadroo where sid = "+  wid + " AND fid=0 AND wid = 0 ");
  	if (rsc.next() )  {
  		ResultSet rsf 	= statement.executeQuery("select sid from quadroo where sid =" + wid + " AND fid= "+ fidi );
  			
  		
  		if (rsf.next() )  {	
  		//return "scene exists in flow" ;
  		repl.reply = "scene exists in flow" ;
  		}
  		else {	
  			ResultSet rsfm 	= statement.executeQuery("select MAX(wid) from quadroo where  fid= " + fidi );
  		    int wp = rsfm.getInt(1);
  		    wp = wp + 1;
  			statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +","+wp+","+ wid +","+fidi+")");	 
  			repl.reply =  "done";
  		} 
  	}

  	else 
  	{ 
  		statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +",0,"+ wid +",0)");	 
  		ResultSet rsfm 	= statement.executeQuery("select MAX(wid) from quadroo where  fid= " + fidi );
  	    int wp = rsfm.getInt(1);
  	    wp = wp + 1;
  		statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +","+wp+","+ wid +","+fidi+")");	 
  	  		
  		repl.reply =  "done";
  	}	
  	
  }
  else 
  {
  	
     statement.executeUpdate("insert into words('uid','name') values(" + luid +",'"+  W+ "')");	 
     wi = (FWord (reqparms)).rwid;
  	 
  	 // statement.executeUpdate("insert into words values(3,'starter4')");	 
  	statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +",0,"+ wi +",0 )");	 
  	// statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER)");
  	ResultSet rsfm 	= statement.executeQuery("select MAX(wid) from quadroo where  fid= " + fidi );
      int wp = rsfm.getInt(1);
      wp = wp + 1;
  	
  	statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +","+wp+","+ wi +","+fidi+")");	 
    	  		
  	repl.reply =  "done";
  	 
  }


return repl;


  } catch (SQLException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
  repl.reply =  "error" + e.getMessage();
  return repl;
  }   

  } 		

  public  Quadroorl  DFScene(Quadroopl reqparms)     {
	 
	  Quadroorl repl = new Quadroorl();
	  String W =  reqparms.W;
	 	 int luid = reqparms.uid;
	 	 int fidi = reqparms.fid;
  try {
  W= W.trim();
  int  wi = (FWord (reqparms)).rwid;
  if (wi > 0) {
  	int wid = wi;
  	ResultSet rsc 	= statement.executeQuery("select sid from quadroo where sid = " + wid+ " AND fid=0 AND wid = 0 ");
  	if (rsc.next() )  {
  		ResultSet rsf 	= statement.executeQuery("select qid from quadroo where sid = " + wid + " AND fid= " + fidi);
  		
  		if (rsf.next() )  {	
  			int qi = rsf.getInt("qid");
  			
  			int rd = statement.executeUpdate("delete from quadroo where qid =" + qi);
  			repl.reply = "Done"; }
  		//return "done" ;}
  		else {	
  			repl.reply =  "no scene in flow";
  		} 
  	}

  	else 
  	{ 
  		repl.reply =  "no scene";
  		
  	}	
  	
  }
  else 
  {
  		repl.reply = "no word";
  	 
  }
  return repl;
  } catch (SQLException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
  repl.reply =  "error" + e.getMessage();
  return repl;
  }   

  } 		
  public  Quadroorl  DFSid(Quadroopl reqparms)     {
	  int wi =  reqparms.wid;
	 	 int luid = reqparms.uid;
	 	 int fidi = reqparms.fid;
	 	 Quadroorl repl = new Quadroorl();
  try {
   {
  	int wid = wi;
  	ResultSet rsc 	= statement.executeQuery("select sid from quadroo where sid = " + wid + " AND fid=0 AND wid = 0 ");
  	if (rsc.next() )  {
  		ResultSet rsf 	= statement.executeQuery("select qid from quadroo where sid = " + wid + " AND fid= " + fidi );
  			if (rsf.next() )  {	
  			int qi = rsf.getInt("qid");
  			
  			int rd = statement.executeUpdate("delete from quadroo where qid =" + qi);
  			
  		repl.reply =  "done" ;}
  		else {	
  			
  			repl.reply = "no scene in flow";
  		} 
  	}

  	else 
  	{ 
  		repl.reply = "no scene";
  	}	
  }
   return repl;
   
  } catch (SQLException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
  repl.reply = "errorr";
  
  return repl;
  }   

  } 	

  public Quadroorl ASceneWord(Quadroopl reqparms)     {
	  Quadroorl repl;
	  String W =  reqparms.W;
	 	 int luid = reqparms.uid;
	 	 String  S = reqparms.S;
  	int sidi = (AScene( reqparms)).rsid ;
  repl = ASWord(reqparms);
  return repl;
  	
  }


  public  Quadroorl  ASWord(Quadroopl reqparms )     {
	  Quadroorl repl = new  Quadroorl();
	  String W =  reqparms.W;
	 	 int luid = reqparms.uid;
	 	 int sidi = reqparms.sid;
  try {
  W= W.trim();
  int  wi = (FWord (reqparms)).rwid;
  //ResultSet rs = statement.executeQuery("select wid from words where name =" + "'"+W +"'");
  if (wi > 0) {
  	int wid = wi;
  	ResultSet rsc 	= statement.executeQuery("select sid from quadroo where sid = " + sidi + " AND fid=0 AND wid = 0 ");
  	if (rsc.next() )  {
  		
  		statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +","+wid+","+ sidi +","+"0)");	 
  		
  		repl.reply = "Done";
  		return repl;
  		} 
  	

  	else 
  	{ 
  		repl.reply = "no scene id";
  		return repl;
  	}	
  	
  }
  else 
  {
  	
     statement.executeUpdate("insert into words('uid','name') values(" + luid +",'"+  W+ "')");	 
     wi =(FWord ( reqparms)).rwid;
  	 
  	 // statement.executeUpdate("insert into words values(3,'starter4')");	 
  	statement.executeUpdate("insert into quadroo('uid','wid','sid','fid') values(" + luid +","+wi+","+sidi+",0 )");	 
  	// statement.executeUpdate("create table quadroo (qid INTEGER PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER)");
  	 repl.reply  = "reply";		
  	return repl;
  	 
  }

  } catch (SQLException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
  repl.reply = "error";
  return repl;
  }   

  } 		

  public Quadroorl DSWord(Quadroopl reqparms)     {
	  
	  Quadroorl repl = new  Quadroorl();
	  
	  String W =  reqparms.W;
	 	 int luid = reqparms.uid;
	 	 int sidi = reqparms.sid;
  try {
  W= W.trim();
  int  wi = (FWord (reqparms)).rwid;
  if (wi > 0) {
  	int wid = wi;
  	ResultSet rsc 	= statement.executeQuery("select qid from quadroo where sid =" + sidi+ " AND fid=0 AND wid = " + wi );
  	//can check for sceneid
  	if (rsc.next() )  {
  		
  			int qi = rsc.getInt("qid");
  			
  			int rd = statement.executeUpdate("delete from quadroo where qid =" + qi);
  			
  		repl.reply =  "done" ;}
  		else {	
  			
  			repl.reply = "no word in scene";
  		} 
  	}

  	
  else 
  {
  		repl.reply  = "no word";
  	 
  }
return repl;
  } catch (SQLException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
  repl.reply =  "error";
  return repl;
  }   

  } 		

  public  Quadroorl  LSWords( Quadroopl reqparms)     {
  Quadroorl repl = new Quadroorl();	  
  String  W = reqparms.W;
  try {
  W= W.trim();
  int  wi = (FWord (reqparms)).rwid;
  if (wi > 0) {
  	int wid = wi;
  	ResultSet rsc 	= statement.executeQuery("select wid from quadroo where sid = " + wid + " AND fid=0 AND wid = 0 ");
  	if (rsc.next() )  { String xml = "<scene name = " + W + ">";
  	                     wi = rsc.getInt("wid");reqparms.wid = wi;
  	                     String wrd = (GWord(reqparms)).rw;
  	                     xml = xml + "<word name=" + wrd+ ">";  
  	
  		while (rsc.next()  ){
  			wi = rsc.getInt("wid");
  			reqparms.wid = wi;
              wrd = (GWord(reqparms)).rw;
              xml = xml + "<word name=" + wrd+ ">";  	
  			
  			
  		}
  		xml = xml + "</xml>" ;
  		repl.rid = 0 ;
  		repl.reply = xml;
  		//return xml ;
  	}

  	else 
  	{   repl.rid = -2;
  		repl.reply =  "no scene with that name";
  	}	
  	
  }
  else 
  {     repl.rid = -2;
  		repl.reply = "scene name not in dictionary";
  	 
  }
     return repl;
  } catch (SQLException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
  //return "error";
  repl.reply = "error:" + e.getMessage();
  return repl;
  }   

  } 	
  public  String LFScenes(Quadroopl reqparms)     {
	  String W =  reqparms.W;
	// 	 int luid = reqparms.uid;
	 //	 int fidi = reqparms.fid;
  try {
  W= W.trim();
  int  wi = (FWord (reqparms)).rwid;
  if (wi > 0) {
  	int wid = wi;
  	ResultSet rsf 	= statement.executeQuery("select sid from quadroo where  fid=" +wi + "AND wid = 0 ");
  	if (rsf.next() )  { String xml = "<flow name = " + W + ">";
  	                     wi = rsf.getInt("sid");reqparms.wid = wi;
  	                     String wrd = (GWord(reqparms)).rw;
  	                     xml = xml + "<scene name=" + wrd+ ">";  
  	
  		while (rsf.next()  ){
  			wi = rsf.getInt("sid");
  			reqparms.wid = wi;
              wrd = (GWord(reqparms)).rw;
              xml = xml + "<scene name=" + wrd+ ">";  	
  			
  			
  		}
  		xml = xml + "</xml>" ;
  		
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
//	quadrooql ql = new quadrooql(url);
	
	QuadrooqlS qs = new QuadrooqlS();
	
 	
	 
	  int rr = -9;  String verb;
	  // all methods we use  have no argument
	  Class params[] = {};
	  Object paramsObj[] = {};
	
	
	
	 Class thisClass = Class.forName("QuadrooqlS");
  	 
  	 
	//ql.AWord(1, "firstword");
	//ql.AWord(1, "morning");
	qs.init() ;
	verb = "AWord";
	qs.reqparms.W = "Bright mind";
	qs.reqparms.m = "Atest field";
	qs.reqparms.uid= 7;
	
	Method thisMethod;
	try {
		thisMethod = thisClass.getDeclaredMethod(verb, params);
		Object rs = thisMethod.invoke(qs, paramsObj);
		System.out.println (" execution:" + rs.toString()) ;
		
	} catch (NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  	//System.out.println     (thisMethod.invoke(qs, paramsObj).toString());
 /* 	 		
			AWord(1, "goodmorning");
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
	System.out.println("Flow delete  no firstword:"+ql.DFlow(1, "firstword"));
	System.out.println("Flow delete  secondword:"+ql.DFlow(1, "secondword"));
	System.out.println("Flow find after delete  secondword:"+ql.FFlow("secondword"));

	System.out.println("Scene Add to flow virginword1:"+ql.AFScene(1, 6,"secondword"));
	System.out.println("Scene Add to flow new word for scene:"+ql.AFScene(1, 6,"newword"));
	System.out.println("Word Add to scene new pie:"+ql.ASWord(1, 7,"pie"));
	System.out.println("Scene Add to scene virgin first:"+ql.ASWord(1, 4,"firstword"));
	
*/ catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}


}
