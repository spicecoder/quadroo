import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.*;
import java.util.logging.Logger;

 

public class CScene_S  extends QuadRR {
	private static final Logger log = Logger.getLogger( CScene_S.class.getName() );
	
 public CScene_S(QuadRR rr) {
	  super(rr);
	  
	//  System.out.println("dbg W is :"+W);
	//  System.out.println("dbg nW is :"+nW);
 	 try {
 		W.trim();
 		 System.out.println("in AWord:" +W);
 		log.log( Level.FINE, "processing change" );
 				ResultSet rs = statement.executeQuery("select * from words where name =" + "'"+W +"'" );
 				  if (rs.next() ) {
 				//	  if(rs.getInt("uid") == luid)
 					  
 					  { 
 					statement.executeUpdate("update words  set name = '" + rW+ "' where  name = '" + W + "'");
 					//#################### what to do? ###########################
 					//ANSFromWord(rr);
 					replystatus = "done"; 
 					}
 				// 		 else {
 				 //			replystatus = "usermismatch:"+luid+"/"+rs.getInt("uid"); 
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