import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.net.InetSocketAddress;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;

public class SimpleQuadRR extends HttpExchange {
	
	



	SimpleQuadRR() { super();}
	
	SimpleQuadRR( SimpleQuadRR rr)  { 
	super();
	
	try {
		statement = rr.connection.createStatement();
		statementW = rr.connection.createStatement();
		statement.setQueryTimeout(30); 
		statement.setQueryTimeout(30); 
		 
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	 
 
	
	
	
	// UIdata to be referenced through byD    ..not ..this.quadroodata = rr.quadroodata;
	this.byD = rr;   //to be used with invoke verb within a verb
	this.outref = rr.outref;
	this.inref = rr.inref;
	this.servletcontext = rr.servletcontext;
	this.W = rr.W;
	this.S =rr.S;
	this.F =rr.F;
	this.FA =rr.FA;
	this.SA =rr.SA;
	this.WA =rr.WA;
	this.fid = rr.fid;
	this.sid = rr.sid;
	this.wid = rr.wid;
	this.la = rr.la;
	this.lo = rr.lo;
	this.m = rr.m;
	this.mA = rr.mA;
	this.N = rr.N;
	//this.quadroodata = rr.quadroodata;
	this.reply = rr.reply;
	this.replystatus = rr.replystatus;
	this.rF = rr.rF;
	this.rFA = rr.rFA;
	this.rS = rr.rS;
	this.rSA = rr.rSA;
	this.rW = rr.rW;
	this.rWA = rr.rWA;
	this.rfid = rr.rfid;
	this.rid = rr.rid;
	this.rwid = rr.rwid;
	this.rm = rr.rm;
	this.inref = rr.inref;
	this.uid = rr.uid;
	this.U = rr.U;
	this.pwd = rr.pwd;
 	this.statement = rr.statement;
 	this.statementW = rr.statementW;
 	this.verb  = rr.verb;
 	this.mongo = rr.mongo;
 	System.out.println("verb obtained:" + verb);
 	if (verb.compareTo( "RegisterIn") != 0 ) 
 	{
 		System.out.println("still going:" + verb);
	 $LogIn_S xs = new $LogIn_S(new QuadRR () );
	this.allowed = xs.allowed ;
	this.U = xs.U;
	
 	}
 	else {this.allowed=true; }
	
	}   // end of constructor
	

	public JSONObject getQuadroodata() {
		return UIdata;
	}
	public void setQuadroodata(JSONObject quadroodata) {
		this.UIdata = quadroodata;
	}
	///////////////////////////////////////////
	
	
	public SimpleQuadRR invokeVerb(String verb) { 
		
		System.out.println(verb + " verb  invoked");

	/////////////////////////////////////
	
	Class cl; 
	String cmd = verb + "_S";
	 Class clarg;
	try {
		clarg = Class.forName("QuadRR");
	
	cl = Class.forName(cmd);
	System.out.println("Two Classes");
	System.out.println(cl);
	System.out.println(clarg);
	
	Class[] argTypes = {clarg};
		Constructor ctor = cl.getConstructor(argTypes);
		System.out.println("class construct: "+ctor);
		System.out.println("rr passed S:"+byD.S);
		// receive the reply from the verb action
		return ( (SimpleQuadRR)  (ctor.newInstance(this.byD)));
		
		    
	} catch ( Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} // needed for constructor
	 
	this.rid =  -9;
	this.replystatus  = "Could not Execute Verb"+verb;
	
	return this;
	
	//////////////////////////////////////
	
	
	
	
	//return this;
	}
	
	 
	 
	protected JSONObject UIdata;
	Object outref ;
	Object inref;
	ServletContext servletcontext;
	//designed pre condition for a scene
		SimpleQuadRR byD;
		//designed  post structure for a scene
		SimpleQuadRR postD;
	
	/* F:Flow
	 * fid:flow id 
	 * N: NameSpace
	 * sid: scene id
	 * S: Scene
	 * seq: Sequence
	 * U:user 
	 * uid:user identifier
	 * nid : a new id value
	 * wid: word id
	 * nwid:new word id
	 * W:word
	 * nW: new word
	 * m:measure or value attached to a word
	 * lo:longitude
	 * la:latitude
	 * pl:place
	 * ln:language
	*/

	//Name space
//	public JSONObject rr = new JSONObject();
	
	
	//public JSONObject Qsession = new JSONObject();
	
	//public int nid = -9;
	public String N ="?";
	
	
	//word
	public int wid = -9;
	public String W = "?";
	
	
	//word
	//public int wid = -9;
	public JSONArray WA = new JSONArray();
	public JSONArray FA = new JSONArray();
	public JSONArray SA = new JSONArray(); 
	public JSONArray rWA = new JSONArray();
	public JSONArray rFA = new JSONArray();
	public JSONArray rSA = new JSONArray(); 
	public JSONArray mA = new JSONArray();
	public JSONArray rmA = new JSONArray();
	
	
	public String lo = "?";
	public String la = "?";
	public String tm = "?";
	 
	//scene
	public int sid = -9 ;
	public String S = "?" ;
	
	//flow
	public int fid = -9;
	public String F = "?";
	
	//user
	public int uid = -9;
	public String U = "?";
	//password
	//public int uid = -9;
	public String pwd = "?"; 
	
	//new word
//	public int nWid = -9;
//	public String nW = "?";
	//measure
				
	public String m = "?";
	//reply
	public int rid = -9;
	public JSONObject reply = new JSONObject();
	public String replystatus  ="?";
	
//	public String  SQLr; 
	
	//word
	public int rwid = -9;
	public String rW = "?";
	public int rtrnWd;
	
	//scene
	public int rsid = -9 ;
	public String rS = "?" ;
	
	//flow
	public int rfid = -9;
	public String rF = "?";
	
	//user
	public int ruid = -9;
	public String rU = "?";
	
	//new word
	public int rnwid = -9;
	public String rnW = "?";
	//new measure
				
	public String rm = "?";
		//new name space
	public int rnid  = -9;
	public String rN= "?";		
	public Stack statements ;
	public Boolean allowed;
    
	
	public ConnectionPool connectionPool;
	protected  Statement statement = null;
	protected  Statement statementW = null;
	protected Connection connection = null;	
	protected Mongo mongo = null;
	public String verb;
	
	 
	public void nuller(SimpleQuadRR rr){
	this.W = null;
	this.F =null;
	this.FA =null;
	this.fid = 0;
	this.la = null;
	this.lo = null;
	this.m = null;
	this.mA = null;
	this.N = null;
	this.UIdata = null;
	this.reply = null;
	this.replystatus = null;
	this.rF = null;
	this.rFA =null;
	this.rS = null;
	this.rSA = null;
	this.rW =null;
	this.rWA = null;
	this.rfid = 0;
	this.rid = 0;
	this.rwid = 0;
	this.rm = null;
	this.statements = null;
	this.allowed = null;
	
	
		
		
	}
	public void inicopy(SimpleQuadRR rr){
		this.W = rr.W;
		this.F =rr.F;
		this.FA =rr.FA;
		this.fid = rr.fid;
		this.la = rr.la;
		this.lo = rr.lo;
		this.m = rr.m;
		this.mA = rr.mA;
		this.N = rr.N;
		this.UIdata = rr.UIdata;
		this.reply = rr.reply;
		this.replystatus = rr.replystatus;
		this.rF = rr.rF;
		this.rFA = rr.rFA;
		this.rS = rr.rS;
		this.rSA = rr.rSA;
		this.rW = rr.rW;
		this.rWA = rr.rWA;
		this.rfid = rr.rfid;
		this.rid = rr.rid;
		this.rwid = rr.rwid;
		this.rm = rr.rm;
		this.statements = rr.statements;
		this.allowed = rr.allowed;
		System.out.println("allowed :" + allowed);
		
		
			
			
		}
	public void populate(JSONObject rr ){ 
		try {
			rr.accumulate("uid", uid);
		//	rr.accumulate("nid", nid);
		//	rr.accumulate("seq", seq);
			rr.accumulate("N", N);
			rr.accumulate("wid", wid);
			rr.accumulate("wid", wid);
			//iamhere
		//	rr.accumulate("hered",hered);
			rr.accumulate("lo",lo);
			rr.accumulate("la",la);
			rr.accumulate("tm",tm);
	//		rr.accumulate("pl",pl);
						
			//scene
			rr.accumulate("sid",sid);
			rr.accumulate("S",S);;
						
			//flow
			rr.accumulate("fid",fid);
			rr.accumulate("F",F);
						
			//user
			rr.accumulate("uid",uid);
			rr.accumulate("U",U);
					
			//new word
	//		rr.accumulate("nWid",nWid);
	//		rr.accumulate("nW",nW);
			
			//measure
			rr.accumulate("m",m);			
			//reply
			rr.accumulate("rid",rid);
			rr.accumulate("reply",reply);
			rr.accumulate("replystatus",replystatus);
	//		rr.accumulate("SQLr",SQLr);
						
			//word
			rr.accumulate("rwid",rwid);
			rr.accumulate("rW",rW);
	//		rr.accumulate("rtrnWd",rtrnWd);
						
			//scene
			rr.accumulate("rsid",rsid);
			rr.accumulate("rS",rS);
						
			//flow
			rr.accumulate("rfid",rfid);
			rr.accumulate("rF",rF);
						
			//user
			rr.accumulate("ruid",ruid);
			rr.accumulate("rU",rU);
						
			//new word
		//	rr.accumulate("rnwid",rnwid);
		//	rr.accumulate("rnW",rnW);
			
			//new measure
			rr.accumulate("rm",rm);			
			//new name space
			rr.accumulate("rnid",rnid);
			rr.accumulate("rN",rN);
			rr.accumulate("WA",WA);			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpContext getHttpContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InetSocketAddress getLocalAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpPrincipal getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InetSocketAddress getRemoteAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getRequestBody() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Headers getRequestHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URI getRequestURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutputStream getResponseBody() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getResponseCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Headers getResponseHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendResponseHeaders(int arg0, long arg1) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStreams(InputStream arg0, OutputStream arg1) {
		// TODO Auto-generated method stub
		
	}
}