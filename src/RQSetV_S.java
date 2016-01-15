//import java.sql.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class RQSetV_S  extends QuadRR {

	
	public RQSetV_S(QuadRR rr) {	
		super(rr);
//		this.quadroodata = rr.quadroodata;
//		statement = rr.statement;
//		
//		ResultSet rs ;
//		{url:"www.yahoo.com",pwd:"abcdef",session:"mika",uid:"abcd"}
		JSONObject jo = new JSONObject();
		try {
			
			jo.put("url", "www.yahoo.com");
			jo.put("pwd", "abcdef");
			jo.put("session", "mika");
			jo.put("uid", "abcd");
			
			System.out.println(jo.toString());
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}