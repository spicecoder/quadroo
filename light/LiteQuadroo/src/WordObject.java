import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class WordObject  {
	
	public String wordName;
	public JSONObject wordMeasure;
	public static  JSONArray WordObjectsToJsontArray(ArrayList<WordObject> wo){
	JSONArray ca = new JSONArray();
	Iterator itr =  wo.iterator();
	while(itr.hasNext()) {
		WordObject ta = (WordObject)(itr.next());
		
		
	ca.put(ta.getWordJson()	) 	 	;
		
	}
	
	
	return ca; 
		
		
		
		
	}

	public JSONObject getWordJson() {
		
		
		JSONObject wj = null ;
		
		
		
		try {  wj = new JSONObject();
		       
		       wj.append("name",this.wordName);
		       wj.append("value", this.wordMeasure);
		      
			  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return wj;
		
	}
	
		  
		
		
	} 

