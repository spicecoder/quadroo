import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class QuickMood_S  extends QuadRR {

public QuickMood_S(QuadRR rr) {
	  super(rr);
	   if (allowed){
	  try {
		  
			
	 	 		 		  	
			//String mood = WA.getString(0) + "_" + WA.getString(1) + "_" + WA.getString(2) + "_" + WA.getString(3) + "_" + WA.getString(4);
	 	   String mood =S;
	 	 // put mood in reply  
			System.out.println("mood:"+ mood +"for" +U);
			
			DB db = mongo.getDB("moods");
			DBCollection collection = db.getCollection("moods");
			BasicDBObject query = new BasicDBObject();
			query.put("mood", mood);
			 BasicDBObject fields = new BasicDBObject("mood",true).append("_id",false).append("contribbutor", true);
			 fields.put("mood", 1);
			/////
		
			System.out.println("mood to insert" +mood)	;		
			DBCursor cursor = collection.find(query);

			if (cursor.hasNext()) {
			   replystatus="that moodalready  created"; rid = 1;
			}
			else {
				//insert new record				 
					 
				 collection = db.getCollection("moods");
				query.put("mood",mood);
				query.put("contributor", U);
				query.put("inspiration","inspiration content");
				query.put("action","create");
				query.put("scope","private");
				query.put("type","contentMood"); 
						 
						collection.insert(query);
				
				
				replystatus="success"; rid = 0; }
			
			
		  }
	  
	  

  //insert the mood in collection moods and put the creator in

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
 