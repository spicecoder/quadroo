 
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class RegisterIn_S  extends QuadRR {
//the input is uid pwd in rr;
//if it matches what in db 
public RegisterIn_S(QuadRR rr) {
	
	super(rr);
	  
	  //System.out.println("select wid from quadroo where sid = " + W);
	  try {
		  // connect with mongodb server, get uid pwd matched stat
		  
		   
			  
			//	Mongo mongo = new Mongo("localhost", 27017);
			//	String epwd = CryptWithMD5.cryptWithMD5(pwd);
				
				DB db = mongo.getDB("users");
				DBCollection collection = db.getCollection("users");
				BasicDBObject query = new BasicDBObject();
				 
				query.put("name", U);
			//	query.put("password", epwd);
				 
				System.out.println("user to insert" + U)	;		
				DBCursor cursor = collection.find(query);

				if (cursor.hasNext()) {
				   replystatus="already registered"; rid = 1;
				}
				else {
					//insert new record
					 
					String epwd = CryptWithMD5.cryptWithMD5(pwd);
					
					 
					 collection = db.getCollection("users");
					 
					 
					query.put("name", U);
					query.put("password", epwd);
					 
						 
							collection.insert(query);
					
					
					replystatus="success"; rid = 0; }
				
				
				 
				}
				 
	 
				
	  		
			
	  catch (Exception e) {
	  e.printStackTrace();
	 replystatus = "exception error"+ e.getMessage();
	 rid= -1;
	  
	  		}
	  System.out.println("regis:" + replystatus);
	  
}
}
	   		
	   
 