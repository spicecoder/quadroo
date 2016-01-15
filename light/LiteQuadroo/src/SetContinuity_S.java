import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SetContinuity_S  extends QuadRR {

public SetContinuity_S(QuadRR rr) {
	super(rr);
	try {
		JSONObject receivedData = new JSONObject(rr.W);
		//System.out.println("Json conversion successful");
		//System.out.println(receivedData.toString());
//		JSONArray QS = receivedData.getJSONArray("QSession");
//		System.out.println("The QSession Data in word is: "+QS.toString());
//		
		try {
			statement.executeUpdate("insert into session('uid','q_session') values( " + uid +",'" +  receivedData.toString() +"') " );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		for (int i = 0; i < QS.length(); ++i) {
//			JSONObject rec = QS.getJSONObject(i);
//		    int SceneId = rec.getInt("scene");
//		    System.out.println("Scene :" +SceneId);
//		    JSONArray words = rec.getJSONArray("words");
//		
//		    for (int j = 0; j < words.length(); ++j) {
//		    	int wordId = words.getInt(j);
//		    	System.out.println("Words :"+ wordId);	
//		    }
//		}
//		
	} catch (JSONException e) {
		System.out.println("Went to catch "+rr.W);
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}