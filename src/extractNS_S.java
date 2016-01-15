import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

public class extractNS_S  extends QuadRR {
	public extractNS_S(QuadRR rr)
	{ 
		super(rr);
		
		 
	//Quadroorl repl = new Quadroorl();
	System.out.println("invoked extractns:"+W);
	int nsi = W.lastIndexOf(".");
		  //System.out.println("what is nsi : "+nsi);
	 
	if (nsi > 0) 
	{
		String ns = W.substring(0,nsi+1); 
		rN = ns; 
		System.out.println("The RN value is "+rN);
		try {
			reply.put("result","done");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} else
		try {
			reply.put("result","nonamespace");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
}