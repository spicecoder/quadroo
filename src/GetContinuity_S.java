import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetContinuity_S  extends QuadRR {

public GetContinuity_S(QuadRR rr) {
	super(rr);
	//System.out.println("The QSession Data is: "+rr.quadroodata.toString());
	

		try {
			ResultSet rsc 	= statement.executeQuery("select q_session from session where uid='"+uid+"'");
			if (rsc.next() )  {
	  	    	System.out.println("data found");

				//String jsn = "{AllWords:[{'name':'" + rsc.getString(2) + "','id':'"+rsc.getString(1)+"'},";
		  	    
		  	    	System.out.println("The data is "+rsc.getString(1));
					try {
						JSONObject re = new JSONObject(rsc.getString(1));
								System.out.println("The status "+re.toString());
								reply.accumulate("result",re);
					} catch (JSONException e) {
						e.printStackTrace();
	            	}
		  	    System.out.println(" rep json" + reply.toString() );
		  		
		  	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

}
}