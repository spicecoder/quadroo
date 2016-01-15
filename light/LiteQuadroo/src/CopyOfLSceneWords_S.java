import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class CopyOfLSceneWords_S  extends QuadRR {

public CopyOfLSceneWords_S(QuadRR rr) {
	
	 super(rr);
	  
	   
	  try {
	  W= W.trim();
	   int  wi = (new FWord_S (rr)).rwid;
	  if (wi > 0) {
	  	int wid = wi;
	  	System.out.println("inside if");
	  	System.out.println("select wid from quadroo where sid = '" + wid+"'");
	  	ResultSet rsc 	= statement.executeQuery("select wid from quadroo where sid = " + wid + " AND fid = 0 AND wid != 0");
	  	//ResultSet rsc 	= statement.executeQuery("select wid from quadroo where sid = " + wid + " AND fid=0 AND wid = 0 ");
	  	//rsc.last();
	  	//System.out.println("The row are "+rsc.getRow());
	  	if (rsc.next()){
	  	JSONObject  jsn= new JSONObject();
	  	jsn.put("scene",W);
	  	//JSONObject re = new JSONObject();
	  	while (rsc.next())  {  
	  		
	  		
	  		
	  	                     wi = rsc.getInt("wid");
	  	                     rr.wid = wi;
	  	                     String nW = (new GWord_S(rr)).rW ;
	  		                 JSONObject nwj = new JSONObject();
	  		                 nwj.put("id", wi);
	  		                 nwj.put("word",nW);
	  		                 
	  	                     jsn.accumulate("words", nwj);   }
	  	
	  	
	  	
	  	 this.reply.accumulate("reply", jsn);
	  	replystatus ="done";
	  	
	  		
	/*   		rr.wid = wi;
	  	                     Integer wrd;
	  	                     
//	  	                     QuadRR rrs = new QuadRR();
	  	                     rrs.wid = rr.wid;
	  	                     //wrd = (new GWord_S(rrs)).rW;
	  	                     wrd = rrs.wid;
	  	                     System.out.println("First Word "+wrd);
	  	                     jsn = jsn + "word:[" + wrd+ ",";  
//	  	                   System.out.println("The wi is "+wi);
	  		while (rsc.next()){
	  			wi = rsc.getInt("wid");
	  			rr.wid = wi;

                 
                  rrs.wid = rr.wid;
	              wrd = rrs.wid;
	              jsn = jsn + wrd+ ",";  	
	              System.out.println("The value of wrd is "+wrd);	  			
	  				System.out.println("jsn in the loop is "+jsn);
	  				//System.out.println("The last value in loop "+rsc.next());
	  		}
	  		
			if(!rsc.next()){
//				System.out.println("jsn.length() "+jsn.length());
//				System.out.println("jsn.substring(jsn.length()-1) "+jsn.substring(jsn.length()-1));
	  		//jsn = jsn.replace(jsn.substring(jsn.length()-1), "");
				jsn = jsn.substring(0,jsn.length()-1);
				jsn = jsn + "]}}" ;
	  		}
	  		rid = 0 ;
	  		System.out.println("The last jsn is "+jsn);
	  		System.out.println("Reply test "+jsn);
	  		reply.put("result", jsn);
	  		//return xml ;  
	  		*/
	  	}
	 	  	else 
	  	{   rid = -2;
	  		reply.put("result", "no scene with that name");
	  		replystatus ="no scene with that name";
	  	}
	  	
	  }
	  
	  else {
		  rid = -1;
	  		reply.put("result", "scene name not in dictionary");
	  		replystatus ="scene name not in dictionary ";
		  
		  
	  }
	  	
	 // }
/*	  else 
	  {     rid = -2;
	  		reply.put("result", "scene name not in dictionary");
	  	 
	  }
	     */
	  } catch (Exception e) {
	  e.printStackTrace();
	  //return "error";
	  try {
		reply.put("result", "error:" + e.getMessage());
	} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  
	  }   
}
}