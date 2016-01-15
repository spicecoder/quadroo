import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.*;
import java.util.logging.Logger;

 

public class CWord_S  extends QuadRR {
	private static final Logger log = Logger.getLogger( CWord_S.class.getName() );
	
 public CWord_S(QuadRR rr) {
	 super(rr);
	  
	//  System.out.println("dbg W is :"+W);
	//  System.out.println("dbg nW is :"+nW);
 	 try {
 		W.trim();
 		 System.out.println("in AWord:" +W);
 		log.log( Level.FINE, "processing change" );
 				ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'" );
 				  if (rs.next() ) {
 				   if(rs.getInt("uid") == uid)
 					  
 					  { 
 					statement.executeUpdate("update words  set name = '" + rW+ "' where  name = '" + W + "'");
 					//#################### what to do? ###########################
 					//ANSFromWord(rr);
 					replystatus = "done"; 
 					}
 				 		 else {
 			 		replystatus = "usermismatch:"+uid+"/"+rs.getInt("uid"); 
 				 			}
 				  }
 				 else 		 {
 					 
 					 replystatus = "noword";
 				}
 		
 
 	 } catch (SQLException e) {
 		e.printStackTrace();
 		
 		replystatus= "fail";
 		
	}

}
}