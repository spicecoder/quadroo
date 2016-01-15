import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Stack;

import javax.servlet.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.Mongo;


/*QuadrooServer  servlet is able to invoke all methods depending on the URL 
 * 
 * Author: Pronob Pal ; code borrowed from core servlets with thanks.
  
 * 
 * */

public class QuadrooServer extends QuadRR {
	
//	int rr = -9;
	//String verb;
	// all methods we use have no argument
	Class params[] = {};
	Object paramsObj[] = {};
	boolean outer;
	//doPost/ doget invokes methods matching the verb extracted from URI
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {doGet( request,  response) ;}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	//
		
	//QuadRR represents both request and response
		
	 	QuadRR repl ;
	 	String rt = "execution result:";
	 	outref = response;
	 	inref = request;

		try {

			// extract verb and parameters:
			// the last part of URI is the verb or the method name
			String ruri = request.getRequestURI();
			int ms = ruri.lastIndexOf("/");

			if ((ms > 0) && (ms < ruri.length())) {
				verb = ruri.substring(ms + 1);
				
				String x = ""+verb.charAt(0);
/*// a $ as the first character represents quadrroo native verb.
				if(x.equals("$")){
					outer =false;
					System.out.println("inner verb");
					verb =verb.substring(1);
					
					System.out.println("New Verb is "+verb);
					
				}
				else {
					
					outer =true;
					
				}*/
				
				
			}

		//	System.out.println("encounterd " + verb + " : "+ request.getParameter("UIdata"));

			// next we extract parameters and hold that in the request scope

			// the input parameter UIdata holds an json string
			// http://localhost:8080/quadrooServer/quadrooql/extractNS?UIdata={W:a.b.c.aword}

			System.out.println("UIData received:" + request.getParameter("UIdata"));
			JSONObject jObj = new JSONObject(
					request.getParameter("UIdata")); // this parses the  UIdata 
			
	//		if(outer){
				this.UIdata = jObj;	
				
						
	//		}			// for non quadroo verb we pass on the UIdata 
				
	//		else {												 
		//	System.out.println("The Obj is :" +jObj.toString());
			
			Iterator it = jObj.keys();
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
				if (key.equalsIgnoreCase("pwd"))
					this.pwd = o.toString();
				if (key.equalsIgnoreCase("uid"))
					this.uid = (Integer.parseInt(o.toString()));
				if (key.equalsIgnoreCase("sid"))
					this.sid = Integer.parseInt(o.toString());
				if (key.equalsIgnoreCase("W"))
					this.W = o.toString();
		//		if (key.equalsIgnoreCase("nW"))
		//			this.nW = o.toString();
				if (key.equalsIgnoreCase("m")) {
					if (o instanceof JSONObject)
					this.m = ((JSONObject)o).toString(); 
					else { m = o.toString(); }
				System.out.println("string m"+ m);}
				if (key.equalsIgnoreCase("lo"))
					this.lo = o.toString();
				if (key.equalsIgnoreCase("la"))
					this.la = o.toString();
	//			if (key.equalsIgnoreCase("pl"))
	//				this.pl = o.toString();
				if (key.equalsIgnoreCase("wid"))
					this.wid = Integer.parseInt(o.toString());
				if (key.equalsIgnoreCase("WA"))
				{System.out.println("WA:" + WA);
					this.WA = new JSONArray(o.toString());}
				  
			
				//http://localhost:8080/Sam/quadrooserver/SetVSW?UIdata={sid:7,WA:{{name:"apple",value:"red"}}}				
			}	
		//	}

          repl = (new RRVerber( this,verb));
			 
			connectionPool.free(connection);
		 
			  rt =  "response"  + 	 repl.byD.replystatus;	
			  
		 		rt = makeResponse(repl.byD);  
		 		//this.nuller(this);
			  
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error x :" + e.getMessage());

		}
	 	;		
	 	
		 
		// Time to Set up the reply Text
		response.setContentType("text/xml");
		// Prevent the browser from caching the response. See
		// Section 7.2 of Core Servlets and JSP for details.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		PrintWriter out = response.getWriter();
		// String title = "Connection Pool Test";

		// String rt = "<reply>" + repl.reply + "</reply> ";
		System.out.println("put out:" + rt);
		out.write(rt);
		// return(rt);
	}

	public String makeResponse(QuadRR rl) {
		/*String rt = "<Qreply>" + "<replyint>" + rl.rid + "</replyint>"
				+ "<reply>" + rl.reply + "</reply>" + "<rfid>" + rl.rfid
				+ "</rfid>" + "<rf>" + rl.rF + "</rf>" + "<rs>" + rl.rS
				+ "</rs>" + "<rsid>" + rl.rsid + "</rsid>" + "<rw>" + rl.rW
				+ "</rw>" + "<rwid>" + rl.rwid + "</rwid>" + "<rnw>" + rl.rnW
				+ "</rnw>" + "<rnwid>" + rl.rnwid + "</rnwid>" + "<rn>" + rl.rN
				+ "</rn>" +

				"</Qreply> "; */
		JSONObject jsonReply = new JSONObject();
		 
		try {
			jsonReply.append("replyint", rl.rid);
		
		jsonReply.put("reply", rl.reply);
		jsonReply.put("replystatus", rl.replystatus);
		jsonReply.put("rfid", rl.rfid);
		jsonReply.put("rF", rl.rF);
		jsonReply.put("rS", rl.rS);
		jsonReply.put("rsid", rl.rsid);
		jsonReply.put("rW", rl.rW);
		jsonReply.put("rwid", rl.rwid);
		jsonReply.put("rnw", rl.rnW);
		jsonReply.put("rnwid", rl.rnwid);
		jsonReply.put("rn", rl.rN);
		System.out.println("received WA:"+ rl.WA);
		jsonReply.put("WA", rl.WA);
		jsonReply.put("S", rl.S);
		//System.out.println("jsonreply WA:"+ jsonReply);
		//qReply.append("QReply", jsonReply);
		
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//THE JSON REPLY 
		String rt =  jsonReply.toString();
		return rt;
	}

	public void init(ServletConfig config) {
		try {
			super.init(config);
		} catch (ServletException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// init is the place to set up url and connection details
		//String url = "jdbc:sqlite:d:/qudtest.db";
		//String driver = "org.sqlite.JDBC";
		servletcontext = this.getServletContext();
		String purl = getServletContext().getInitParameter("my.db.url");
		String pdriver = getServletContext().getInitParameter("my.driver");
				 System.out.println("parameter:"+ purl);
				 System.out.println("driverparameter:"+ pdriver);
		try {
			// create a database connection
			// connection = DriverManager.getConnection(url);
			connectionPool = new ConnectionPool(pdriver, purl, null, null, 2, 10,
					true);
			//connectionPool = new ConnectionPool(pdriver, url, username, password, initialConnections, maxConnections, waitIfBusy)
			// connection = connectionPool.getConnection();
			mongo = new Mongo("localhost", 27017);

		} catch (SQLException sqle) {
			System.err.println("Error making pool: " + sqle);
			getServletContext().log("Error making pool: " + sqle);
			connectionPool = null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		try {
			connection = connectionPool.getConnection();
			
			int ii = 0;
			statements = new Stack();
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			while (ii < 6){
				ii++;
				Statement  st =  connection.createStatement();
			    st.setQueryTimeout(30);
			
			statements.push(st);}
			//statementW = connection.createStatement();
			//statementW.setQueryTimeout(30); // set timeout to 30 sec.
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