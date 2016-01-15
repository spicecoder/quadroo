import java.sql.ResultSet;
import java.sql.SQLException;

public class FNSpace_S  extends QuadRR {

public FNSpace_S(QuadRR rr) {
	 super(rr);

 	 try {
 		  W= W.trim();

 		  ResultSet rs = statement.executeQuery("select * from nspace where name =" + "'"+W +"'");
 		   if (rs.next() ) {
 		  	 	
 		  		rid = rs.getInt("nid");
 		  		reply.put("result", "Done");
 		   
 		  	 }

 		   else 
 		   {
 			   rid = -1;
 			  reply.put("result", "Eror");
 		  		 
 		   
 		   }

 		  } catch (Exception e) {
 		  // TODO Auto-generated catch block
 		  e.printStackTrace();
 		  rid = -1;
 		  
 		  }    	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 

}
}