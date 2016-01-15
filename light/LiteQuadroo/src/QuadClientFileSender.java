import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class QuadClientFileSender { 
	JSONObject replyJson = null;
	 String replyString  = null;
	public QuadClientFileSender (String url,String verb ) {
	 
	String postURL = 	url + verb ;
	String quadroodata = null;
	  HttpClient client = new DefaultHttpClient();
	   HttpPost post = new HttpPost(postURL);
	 	// we make the quadroodata from reading a file
	   
	   byte [] mybytearray  = new byte [500000000];
	   // OutputStream iso = sock.getOutputStream();
	    FileInputStream ifos = null;
		try {
			ifos = new FileInputStream("logox.png");
		 
		
	    BufferedInputStream bos = new BufferedInputStream(ifos);
	    
		int    bytesRead = ifos.read(mybytearray,0,mybytearray.length);
		 byte[] finalbytearray = new byte[bytesRead];
		    System.arraycopy(mybytearray, 0,finalbytearray, 0, bytesRead);
		    mybytearray = null;
		
		    String objString = new String(Base64.encodeBase64(finalbytearray));
		    
		    System.out.println("object in String" + objString);
		 // your string
	    //    String yourString = new String(Hex.encodeHex(finalbytearray));
		    
	//	    System.out.println("object in String" + objString);
		   quadroodata = "{W:'" +objString+  "',S:'oct3nxfile', F:'Hot'}"; 
		
		
		
		}catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	   

	     

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
   QuadClientFileSender  qc  = new QuadClientFileSender(args[0],args[1]);
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

