import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class QuickListMoods_S  extends QuadRR {

public QuickListMoods_S(QuadRR rr) {
	  super(rr);
	   if (allowed){
	  try {
		  	
 
	System.out.println("mood list  for" + U);
	
	DB db = mongo.getDB("moods");
	DBCollection collection = db.getCollection("moods");
	BasicDBObject query = new BasicDBObject();
 	BasicDBObject fields = new BasicDBObject("mood",true).append("_id",false).append("contribbutor", true);
//			;
//	BasicDBObject fields = new BasicDBObject();
 fields.put("mood", 1);
 fields.put("contributor", 2);
  
 	query.put("contributor", U);
	//query.put("c", mood);
/*
 query.put("mood",mood);
				query.put("contributor", U);
				query.put("inspiration","inspiration content");
				query.put("action","create");
				query.put("scope","private");
				query.put("type","contentMood"); 
						 
  
 */
	 
	System.out.println("listing moods for" + U)	;		
	DBCursor cursor = collection.find(query,fields);
   int ii = 0;
   replystatus="no entry";
   rid = -1;
	while(cursor.hasNext()) {
		DBObject o = cursor.next();
		System.out.println("before populate" + WA);
		WA.put(ii,o);
		//rW= (o.get("_id")).toString();
		
		System.out.println("mood" + WA.get(ii));
		ii = ii + 1;
	}
	  replystatus ="ok";
	  rid=0;
	  
	  }
	  
	  

  //insert the mood in collection moods and put the creator in

		   catch (Exception e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		  replystatus="failure"+ e.getMessage();
		 rid = -1;
		  }  

} 
	   else { replystatus="not logged in" ; rid = -1;}   


} 

}
 