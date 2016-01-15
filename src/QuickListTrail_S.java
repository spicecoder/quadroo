import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class QuickListTrail_S  extends QuadRR {

public QuickListTrail_S(QuadRR rr) {
	  super(rr);
	   if (allowed){
	  try {
		  // mood is in S and we take the contents from mood
		  /* m table entries
		   query.put("entryname", name);
		 		query.put("content",notes) ;
				query.put("type","note");
				query.put("scope", "private");
				query.put("contributor", U);
				 
				 
		   *  
		   */
			
	 	 		 		  	
			String mood =  S;
		// notes = WA.getString(0);
	 	 // put mood in reply  
			System.out.println("mood trail for:"+ mood );
			
			DB db = mongo.getDB("moods");
			DBCollection collection = db.getCollection(mood);
			BasicDBObject query = new BasicDBObject();
			BasicDBObject fields = new BasicDBObject("content",true).append("_id",true).append("type", true).append("entryname", true);
			fields.put("entryname", 1);
			fields.put("content", 1);
		   fields.put("type", 1);
		  
		//	query.put("name", mood);
			//query.put("c", mood);
		
			 
			System.out.println("listing contententries for" + mood)	;		
			DBCursor cursor = collection.find(query,fields);
             int ii = 0;
             replystatus="no entry";
             rid = 0;
			while(cursor.hasNext()) {
				DBObject o = cursor.next();
				String  ky = (o.get("_id")).toString();
				String  ty =(String) (o).get("type");
				String  nt =(String) (o).get("content");
				o.put("ky", ky);
				WA.put(ii,o);
				System.out.println("from WA:"+ WA.get(ii));
				ii = ii +1;
			}
			    
			
				
				replystatus="success"; rid = 0; 
				System.out.println("all trail:"+ WA );
				
	  }
			
		 

		   catch (Exception e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		  replystatus="failure"+ e.getMessage();
		 rid = -1;
		  }  

} 
	   else { replystatus="not logged in" ;}   


} 

}
 