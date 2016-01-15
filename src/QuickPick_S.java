import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class QuickPick_S  extends QuadRR {

public QuickPick_S(QuadRR rr) {
	  super(rr);
	   if (allowed){
	  try {
		  
			//an entry made for picture location in W directory in "S" user in U
	 	 	//need to make the unique URL for the pic 	 		  	
			String mood =  W;
			String notes = WA.getString(0);
	 	 // put mood in reply  
			System.out.println("mood note for:"+ mood +"is" +notes);
			
			DB db = mongo.getDB("moods");
			DBCollection collection = db.getCollection("moods");
			BasicDBObject query = new BasicDBObject();
			 
			query.put("name", mood);
		//put the url to the pic  in the content

			query.put("content",notes) ;
			query.put("type","pic");
			query.put("contributor", U);
			 
				 
					 
						collection.insert(query);
				
				
				replystatus="success"; rid = 0; }
			
		 

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
 