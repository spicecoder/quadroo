
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class QuickNote_S  extends QuadRR {

public QuickNote_S(QuadRR rr) {
	  super(rr);
	   if (allowed){
	  try {
		  
			String type=F;
	 	 	String name = W;	 		  	
			String mood =  S;
			String notes = WA.getString(0);
	 	 // put mood in reply  
			System.out.println("mood note for:"+ mood +"is" +notes);
			
			DB db = mongo.getDB("moods");
			DBCollection collection = db.getCollection(mood);
			BasicDBObject query = new BasicDBObject();
			 
				query.put("entryname", name);
		 		query.put("content",notes) ;
				query.put("type",F);
				query.put("scope", "private");
				query.put("contributor", U);
				 
					 
						collection.insert(query);
						
						
						DBCursor cursor = collection.find(query);
			             int ii = 0;
			             replystatus="failed";
			             rid = 0;
						while(cursor.hasNext()) {
							DBObject o = cursor.next();
							rW= (o.get("_id")).toString();
							replystatus="success"; rid = 0; 
							 
						}
				
				
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
 