
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class QuadClientOrg { 
	JSONObject replyJson = null;
	 String replyString  = null;
	public QuadClientOrg (String url,String verb, String quadroodata ) {
	 
	String postURL = 	url + verb ;
	  HttpClient client = new DefaultHttpClient();
	   HttpPost post = new HttpPost(postURL);
	 	
	
	 try {
	      List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
	      //?UIdata={W:a.b.c.aword}
	   //   nameValuePairs.add(new BasicNameValuePair("registrationid",
	    //          "123456789"));
	      nameValuePairs.add(new BasicNameValuePair("UIdata",
	         quadroodata));
	      post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    //   System.out.println("post:"+post.getParams().getParameter("UIdata"));
	      HttpResponse response = client.execute(post);
	      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	      String line = "";
	      while ((line = rd.readLine()) != null) {
	        System.out.println("Client Received"+line);
	        try {
	        	replyString = line;
				JSONObject jsonReceived = new JSONObject(line);
				JSONObject Qjson = (JSONObject) jsonReceived.get("Qreply");
				replyString =  (String) Qjson.get("reply");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	      }
            
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  
	
	} 
	
	public JSONObject getJsonReply() { return this.replyJson; }
	public String getStringReply(){ return this.replyString ;}
  public static void main(String[] args) {
   QuadClientOrg  qc  = new QuadClientOrg(args[0],args[1],args[2])  ;
   System.out.println("received reply:" +(qc.getStringReply()));
	//  HttpClient client = new DefaultHttpClient();
  // url "http://localhost:8080/projectname/quadrooServer/");
  // verb extractNS 
    //   nameValuePairs.
  //      "{W:a.b.c.aword}"));   
    
    /*
    try {
      List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
      //?UIdata={W:a.b.c.aword}
   //   nameValuePairs.add(new BasicNameValuePair("registrationid",
    //          "123456789"));
      nameValuePairs.add(new BasicNameValuePair("UIdata",
         "{W:a.b.c.aword}"));
      post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
       System.out.println("post:"+post.getParams().getParameter("UIdata"));
      HttpResponse response = client.execute(post);
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      while ((line = rd.readLine()) != null) {
        System.out.println("Client Received"+line);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }*/
  }
  
  
  
} 

