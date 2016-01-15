import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MakeBytes_S  extends QuadRR {
	 public static byte[] toByteArrayUsingJava(InputStream is) throws IOException{
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        int reads = is.read();
	       
	        while(reads != -1){
	            baos.write(reads);
	            reads = is.read();
	        }
	      
	        return baos.toByteArray();
	       
	    } 

	
	public MakeBytes_S(QuadRR rr) {

	super(rr);
	  
	  
	  try {
		  //The W contains the byte array
		  //S contains the file name
		  //F contains directory name
		// we make the quadroodata from reading a file
		   byte [] mybytearray  = new byte [800000];
		   // OutputStream iso = sock.getOutputStream();
		    FileInputStream ifos = null;
			try {
				ifos = new FileInputStream(S);
			
		    BufferedInputStream bos = new BufferedInputStream(ifos);
		
			int    bytesRead = ifos.read(mybytearray,0,mybytearray.length);
			 byte[] finalbytearray = new byte[bytesRead];
			    System.arraycopy(mybytearray, 0,finalbytearray, 0, bytesRead);
			
			    String objString = new String(Base64.encodeBase64(finalbytearray));
			    
			    System.out.println("object in String" + objString);
			 W = objString;
			
			}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   

		     

		//  byte[] objBack = W.getBytes();

	     ByteArrayInputStream in = new ByteArrayInputStream(Base64.decodeBase64(W.getBytes()));
		    
		    /////////////////////////
		    
		 //   hex  coding does not work

	        // deserialize
	  //      ByteArrayInputStream in = new ByteArrayInputStream(Hex.decodeHex(W.toCharArray()));
	  
		    
   
	  } catch (Exception e) {
	  e.printStackTrace();
	  //return "error";
	  
	  try {
	  			reply.put("result","error"+ e.getMessage());
	  			replystatus="error result";
	  		} catch (JSONException e1) {
	  			// TODO Auto-generated catch block
	  			e1.printStackTrace();
	  			replystatus="Error";
	  			 
	  		}
	   		
	  
	  }   
}
}