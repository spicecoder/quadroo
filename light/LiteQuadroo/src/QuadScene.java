import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;

import org.json.JSONException;
import org.json.JSONObject;

public class QuadScene extends HttpServlet {
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	public String currentDate = dateFormat.format(date);
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
	public JSONObject rr = new JSONObject();
	 
	public int nid = -9;
	public String N ="?";
	public String seq ="?";
	
	//word
	public int wid = -9;
	public String W = "?";
	
	//iamhere
	public int hered = -9;
	public String lo = "?";
	public String la = "?";
	public String tm = "?";
	public String pl = "?";
	
	//scene
	public int sid = -9 ;
	public String S = "?" ;
	
	//flow
	public int fid = -9;
	public String F = "?";
	
	//user
	public int uid = -9;
	public String U = "?";

	
	//new word
	public int nWid = -9;
	public String nW = "?";
	//measure
				
	public String m = "?";
	//reply
	public int rid = -9;
	public JSONObject reply = new JSONObject();
	public String replystatus  ="?";
	
	public String  SQLr; 
	
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
    
	
	public ConnectionPool connectionPool;
	protected  Statement statement = null;
	protected Connection connection = null;	
	
	
	
	public void populate(){ 
		try {
			this.rr.accumulate("uid", uid);
			this.rr.accumulate("nid", nid);
			this.rr.accumulate("seq", seq);
			this.rr.accumulate("N", N);
			this.rr.accumulate("wid", wid);
			this.rr.accumulate("wid", wid);
			//iamhere
			this.rr.accumulate("hered",hered);
			this.rr.accumulate("lo",lo);
			this.rr.accumulate("la",la);
			this.rr.accumulate("tm",tm);
			this.rr.accumulate("pl",pl);
						
			//scene
			this.rr.accumulate("sid",sid);
			this.rr.accumulate("S",S);;
						
			//flow
			this.rr.accumulate("fid",fid);
			this.rr.accumulate("F",F);
						
			//user
			this.rr.accumulate("uid",uid);
			this.rr.accumulate("U",U);
					
			//new word
			this.rr.accumulate("nWid",nWid);
			this.rr.accumulate("nW",nW);
			
			//measure
			this.rr.accumulate("m",m);			
			//reply
			this.rr.accumulate("rid",rid);
			this.rr.accumulate("reply",reply);
			this.rr.accumulate("replystatus",replystatus);
			this.rr.accumulate("SQLr",SQLr);
						
			//word
			this.rr.accumulate("rwid",rwid);
			this.rr.accumulate("rW",rW);
			this.rr.accumulate("rtrnWd",rtrnWd);
						
			//scene
			this.rr.accumulate("rsid",rsid);
			this.rr.accumulate("rS",rS);
						
			//flow
			this.rr.accumulate("rfid",rfid);
			this.rr.accumulate("rF",rF);
						
			//user
			this.rr.accumulate("ruid",ruid);
			this.rr.accumulate("rU",rU);
						
			//new word
			this.rr.accumulate("rnwid",rnwid);
			this.rr.accumulate("rnW",rnW);
			
			//new measure
			this.rr.accumulate("rm",rm);			
			//new name space
			this.rr.accumulate("rnid",rnid);
			this.rr.accumulate("rN",rN);
			
		    
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}