import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FlowObject  {
	private String FlowName;
	//private String SceneName;
	private ArrayList<SceneObject> sceneobjects;

	public JSONObject getFlowJson() {
		
		JSONArray fs = SceneObject.ScenedObjectsToJsontArray(sceneobjects);
		JSONObject 		fj;		
		
		try {  fj = new JSONObject();
		       fj.append("flow", FlowName);
		       fj.append("scenes",fs);
		      
		      
			  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
		
	}
	
		  
		
		
	} 

