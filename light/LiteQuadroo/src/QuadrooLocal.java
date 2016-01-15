import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/*QuadrooServer  servlet is able to invoke all methods depending on the URL 
 * 
 * Author: Pronob Pal ; code borrowed from core servlets with thanks.
 * co-Auhor: Ahmmad Ismail
 * 
 * */

public class QuadrooLocal extends QuadRRLocal {
	
//	int rr = -9;
	String verb;
	// all methods we use have no argument
	Class params[] = {};
	Object paramsObj[] = {};
	boolean outer;
	//doPost/ doget invokes methods matching the verb extracted from URI
 	public void doPost(String verb, QuadRRLocal request, QuadRRLocal response)
 			throws  IOException {doGet(verb,  request,  response) ;}

	 public void doGet(String verb, QuadRRLocal request, QuadRRLocal response)
 		throws   IOException {
	//
		
	//QuadRR represents both request and response
		
		QuadRRLocal repl = new QuadRRLocal();

		try {

			// extract verb and parameters:
			// the last part of URI is the verb or the method name
	/*		String ruri = request.W;
			int ms = ruri.lastIndexOf("/");

			if ((ms > 0) && (ms < ruri.length())) {
				verb = ruri.substring(ms + 1);
				*/
				String x = "" +verb.charAt(0);
// a $ as the first character represents quadrroo native verb.
				if(x.equals("$")){
					outer =false;
					System.out.println("inner verb");
					verb =verb.substring(1);
					
					System.out.println("New Verb is "+verb);
					
				}
				else {
					
					outer =true;
					
				}
				
				
			 

		//	System.out.println("encounterd " + verb + " : "+ request.getParameter("UIdata"));

			// next we extract parameters and hold that in the request scope

			// the input parameter UIdata holds an json string
			// http://localhost:8080/quadrooServer/quadrooql/extractNS?UIdata={W:a.b.c.aword}

			
			 JSONObject jObj = this.getQuadroodata();
				//	request.getParameter("UIdata")); // this parses the  UIdata 
			
			if(!outer){
				 											 
			
			
			Iterator it = (jObj).keys();
			// gets all the keys
			
			while (it.hasNext()) {
				String key = (String) it.next(); // get key
				Object o = jObj.get(key); // get value
				// session.putValue(key, o); // store in session
				System.out.println("json " + key + " value is: " + o.toString());

//abbreviations:
				/* F:Flow
				 * N: NameSpace
				 * S: Scene
				 * U:user 
				 * uid:user identifier
				 * W:word
				 * nW: new word
				 * m:measure or value attached to a word
				 * lo:longitude
				 * la:latitude
				 * pl:place
				 * ln:language
				 * WA wordarray
				*/
				 
				
				if (key.equalsIgnoreCase("F"))
					this.F = o.toString();
				if (key.equalsIgnoreCase("N"))
					this.N = o.toString();
				if (key.equalsIgnoreCase("S"))
					this.S = o.toString();
				if (key.equalsIgnoreCase("U"))
					this.U = o.toString();
				if (key.equalsIgnoreCase("uid"))
					this.uid = Integer.parseInt(o.toString());
				if (key.equalsIgnoreCase("sid"))
					this.sid = Integer.parseInt(o.toString());
				if (key.equalsIgnoreCase("W"))
					this.W = o.toString();
				if (key.equalsIgnoreCase("nW"))
					this.nW = o.toString();
				if (key.equalsIgnoreCase("m")) {
					if (o instanceof JSONObject)
					this.m = ((JSONObject)o).toString(); 
					else { m = o.toString(); }
				System.out.println("string m"+ m);}
				if (key.equalsIgnoreCase("lo"))
					this.lo = o.toString();
				if (key.equalsIgnoreCase("la"))
					this.la = o.toString();
				if (key.equalsIgnoreCase("pl"))
					this.pl = o.toString();
				if (key.equalsIgnoreCase("wid"))
					this.wid = Integer.parseInt(o.toString());
				if (key.equalsIgnoreCase("WA"))
					this.WA = (JSONArray) o;
			
				//http://localhost:8080/Sam/quadrooserver/SetVSW?UIdata={sid:7,WA:{{name:"apple",value:"red"}}}				
			}	
			}


			// invoke verb class ,by passing QuaddRR in the constructor
			Class cl; 
			String cmd = verb + "_S";
			Class	clarg=Class.forName("QuadRR");  // needed for constructor
		
			cl = Class.forName(cmd);
			System.out.println("Two Classes");
			System.out.println(cl);
			System.out.println(clarg);
			
			Class[] argTypes = {clarg};
				Constructor ctor = cl.getConstructor(argTypes);
				System.out.println("class construct: "+ctor);
				
				// receive the reply from the verb action
				//we are doing this in the constructore now
				QuadRR tempRR = new QuadRR();
				tempRR.F = this.F;
				tempRR.fid = this.fid;
				tempRR.la = this.la;
				tempRR.lo = this.lo;
				tempRR.m =this.m;
				tempRR.N = this.N;
				//tempRR.nid= this.nid;
				//tempRR.nW =this.nW;
				//tempRR.nWid=this.nWid;
				//tempRR.pl=this.pl;
				tempRR.UIdata= this.quadroodata;
				tempRR.reply=this.reply;
				tempRR.replystatus=this.replystatus;
				tempRR.rF=this.rF;
				tempRR.rfid=this.rfid;
				tempRR.rid=this.rid;
				tempRR.rm= this.rm;
				tempRR.rN=this.rN;
				tempRR.rnid=this.rnid;
				tempRR.rnW=this.rnW;
				tempRR.rnwid=this.rnwid;
				//tempRR.rr=this.rr;
				tempRR.rS=this.rS;
				tempRR.rsid=this.rsid;
				tempRR.rtrnWd=tempRR.rtrnWd;
				tempRR.rU=this.rU;
				tempRR.ruid=this.ruid;
				tempRR.rW=this.rnW;
				tempRR.rwid=this.rwid;
				tempRR.S=this.S;
			//	tempRR.seq=this.seq;
				tempRR.sid=this.sid;
		//		tempRR.SQLr=this.SQLr;
				tempRR.statement =this.statement;
				tempRR.statementW=this.statementW;
				tempRR.connection=this.connection;
				tempRR.connectionPool=this.connectionPool;
				 
				
				
				
				
				
				repl = (QuadRRLocal) ctor.newInstance(tempRR);
			

			connectionPool.free(connection);
			System.out.println("why error repl" + repl);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error x :" + e.getMessage());

		}
		String rt = makeResponse(repl);
		System.out.println("progressing:" + this.W);
		// Time to Set up the reply Text
	//	response.setContentType("text/xml");
		// Prevent the browser from caching the response. See
		// Section 7.2 of Core Servlets and JSP for details.
	//	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	//	response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
	//	PrintWriter out = response.getWriter();
		// String title = "Connection Pool Test";

		// String rt = "<reply>" + repl.reply + "</reply> ";
		System.out.println("put out:" + rt);
	//	out.write(rt);
		// return(rt);
	}

	public String makeResponse(QuadRRLocal repl) {
		
		JSONObject jsonReply = new JSONObject();
		 
		try {
			jsonReply.append("replyint", repl.rid);
		
		jsonReply.put("reply", repl.reply);
		jsonReply.put("replystatus", repl.replystatus);
		jsonReply.append("rfid", repl.rfid);
		jsonReply.append("rf", repl.rF);
		jsonReply.append("rs", repl.rS);
		jsonReply.append("rsid", repl.rsid);
		jsonReply.append("rw", repl.rW);
		jsonReply.append("rwid", repl.rwid);
		jsonReply.append("rnw", repl.rnW);
		jsonReply.append("rnwid", repl.rnwid);
		jsonReply.append("rn", repl.rN);
		
		//qReply.append("QReply", jsonReply);
		
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String rt = '{"Qreply": + jsonReply.toString()+"}";
		String rt =  jsonReply.toString();
		
		
		

		return rt;
	}

	public void init() {
		// init is the place to set up url and connection details
		//String url = "jdbc:sqlite:d:/qudtest.db";
		//String driver = "org.sqlite.JDBC";
		String purl  = null;
		//= getServletContext().getInitParameter("my.db.url");
		String pdriver = null; ;
		//= getServletContext().getInitParameter("my.driver");
				//System.out.println("parameter:"+ purl);
				//System.out.println("driverparameter:"+ pdriver);
		try {
			// create a database connection
			// connection = DriverManager.getConnection(url);
			connectionPool = new ConnectionPool(pdriver, purl, null, null, 2, 10,
					true);
			//connectionPool = new ConnectionPool(pdriver, url, username, password, initialConnections, maxConnections, waitIfBusy)
			// connection = connectionPool.getConnection();

		} catch (SQLException sqle) {
			System.err.println("Error making pool: " + sqle);
		//	getServletContext().log("Error making pool: " + sqle);
			connectionPool = null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		try {
			connection = connectionPool.getConnection();
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			statementW = connection.createStatement();
			statementW.setQueryTimeout(30); // set timeout to 30 sec.
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void destroy() {
		connectionPool.closeAllConnections();
	}

	/**
	 * Override this in subclass to change number of initial connections.
	 */

	protected int initialConnections() {
		return (10);
	}

	

	protected int maxConnections() {
		return (50);
	} 
    public QuadRRLocal invoke(String verb, String quadroodata) {
    	JSONObject qd = null;
		try {
			qd = new JSONObject(quadroodata);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
    	this.setQuadroodata(qd);
    	
    	
    	QuadRR rr = new QuadRR(); return null; }
	
	// clear all tables and rebuild
	public String clearTables() {
		// users hold the user password, user is in the form of email
		try {
			statement.executeUpdate("drop table if exists users");
			statement
					.executeUpdate("create table users ( user string primary KEY, passwd string )");
			System.out.println("users");

			// uid is the rowid of the user, both tables words and quadroo have
			// the creator in uid .
			// rule is only creator can update or delete the word or any quadroo
			// entry,administrator can always come in as any user
			statement.executeUpdate("drop table if exists nspace");
			statement
					.executeUpdate("create table nspace (nid INTEGER PRIMARY KEY AUTOINCREMENT ,uid integer ,name string Unique )");
			System.out.println("nspace");
			statement.executeUpdate("drop table if exists words");
			statement
					.executeUpdate("create table words (wid INTEGER PRIMARY KEY AUTOINCREMENT ,uid integer,name string Unique, m varchar(1) )");
			statement.executeUpdate("drop table if exists quadroo");
			// quadroo is the measure asssociated with a word; 
			// measure can also be  just the description of the
			// word,significance of its name stored in a dictionary -with name space association
			// word in scene has the measure as the value of the word; in that
			// sense, word can not have value without being in a scene.
			// flow is the story about how the value changes through scenes.
			// only flow and only scene may have measure where they have
			// description about the scene and the flow resp.
			//some sample data:
			statement
					.executeUpdate("create table quadroo (qid INTEGER PRIMARY KEY AUTOINCREMENT,uid integer,  wid INTEGER, sid INTEGER, fid INTEGER,m varchar(1))");
			int r1 = statement
					.executeUpdate("insert into words(uid,name) values(2,'starter')");

			System.out.println("inserted words");

			return ("tables recreated");

		} catch (Exception e) {
			System.out.println("error x :" + e.getMessage());

			return e.getMessage();
		}
	}

}