import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SceneObject  {
	
	public String SceneName;
	public ArrayList<WordObject> words;
	
	public static  JSONArray ScenedObjectsToJsontArray(ArrayList<SceneObject> so){
		JSONArray ca = new JSONArray();
		Iterator itr =  so.iterator();
		while(itr.hasNext()) {
			SceneObject ta = (SceneObject)(itr.next());
			
			
		ca.put(ta.getSceneJson()	) 	 	;
			
		}
		
		
		return ca; 
			
			
			
			
		}


	public JSONObject getSceneJson() {
		
		JSONArray wj = WordObject.WordObjectsToJsontArray(words);
		JSONObject sj = null ;
		
		
		
		try {  sj = new JSONObject();
		       
		       sj.append("scene",SceneName);
		       sj.append("words", wj);
		      
			  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return sj;
		
	}
	
		  
		
		
	} 

