import java.sql.ResultSet;
import java.sql.SQLException;

public class ANSFromWord_S  extends QuadRR {

public ANSFromWord_S(QuadRR rr){
  	super(rr);
  	String ans = new extractNS_S(rr).rnW;
  	rr.N = ans;
  	if (ans != null ) {
  		
	  	if	(new FWord_S(rr).rid == -1 ) {
	  		new ANspace_S(rr);
	  		
	  	}	
  	}
  }


}