import java.sql.ResultSet;
import java.sql.SQLException;

public class AIamHere_S  extends QuadRR {

public AIamHere_S(QuadRR rr) {
	 
	  super(rr);
	  String plc = "";
	  //System.out.println("the value of h is " +h);
	  
//	  
//	  
//	  String[] parts = h.split("#");
//	  System.out.println("length array " + parts.length);
//	  for (int i=0;i<parts.length;i++)
//	  {
//		  System.out.println("value from the array :"+parts[i]);
//		  
//	  }
//	  
	  //System.out.println("the type of lng is :"+parts[0].getClass().getName());
//	  System.out.println(lng);
//	  System.out.println(lat);
//	  System.out.println(plc);

	  statement = rr.statement;
	  //System.out.println();
	  
	  new AWord_S(rr);
	  
	  int wid = rr.rtrnWd;
	  
	  
	try {
		//statement.exe
		statement.execute("insert into iAmHere('word_id','uid','longitude','latitude','time','place') values('" + wid +"','" + uid +"','"+ lo+"','"+ la+"','"+ tm+"','"+ plc+"')");
		reply.put("result","Done");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Query did not run");
	}	 
	
}
}