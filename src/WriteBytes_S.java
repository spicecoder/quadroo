import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WriteBytes_S  extends QuadRR {
	 
	
	
	
	 public static byte[] toByteArrayUsingJava(InputStream is) throws IOException{
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        int reads = is.read();
	       
	        while(reads != -1){
	            baos.write(reads);
	            reads = is.read();
	        }
	      
	        return baos.toByteArray();
	       
	    } 

	
	public WriteBytes_S(QuadRR rr) {

	super(rr);
	  
	if (allowed ) {
	
	  
	  try {
		  //The W contains the byte array
		  //S contains the file name
		  //F contains directory name
		  
		//  byte[] objBack = W.getBytes();

	     ByteArrayInputStream in = new ByteArrayInputStream(Base64.decodeBase64(W.getBytes()));
		    
		    /////////////////////////
		    
		 //   hex  coding does not work

	        // deserialize
	  //      ByteArrayInputStream in = new ByteArrayInputStream(Hex.decodeHex(W.toCharArray()));
	  
		    
		    //////////////////////////////////
		    
		    
		    byte[] objBack =   toByteArrayUsingJava(in);
      /*
       // sendfile
      File myFile = new File ("moodpicsucked.jpg");
      byte [] mybytearray  = new byte [4000000];
      FileOutputStream fiso = new FileOutputStream(myFile);
      BufferedOutputStream bos = new BufferedOutputStream(fiso);
       InputStream iso = sock.getInputStream();
      System.out.println("Receiving...");
      iso.read(mybytearray,0,mybytearray.length);
      bos.write(mybytearray,0,mybytearray.length);

      bos.flush();
	  bos.close();
	  fiso.close();
       */
		
		  //  chararray = objString.toCharArray();
		  F = F.trim()  ;
		  S=S.trim();
		  //S has  the full file name as mood + slotno ;
		  // F has the split file numbers
		  String homedir = "C:/ActApprent/WS/NowIamIn/Webcontent/moodresources/" ;
		  File homep  = new File("C:/ActApprent/WS/NowIamIn/Webcontent/moodresources/" ); // current directory
		//C:\ActApprent\WS\NowIamIn\WebContent\modresources\theduck_thatled___
		    final File dir = new File(homep, S);
		    if (!dir.exists() && !dir.mkdirs()) {
		        throw new IOException("Unable to create " + dir.getAbsolutePath() );
		    }
		 
		  
		  
		  File myFile = new File (homedir+S+"/" +F+".png");
		   System.out.println("file written :"+ myFile.getAbsolutePath());
		    FileOutputStream fiso = new FileOutputStream(myFile);
		    BufferedOutputStream fos = new BufferedOutputStream(fiso);
		    fos.write(objBack,0,objBack.length);  
		  System.out.println("bytes written:" + objBack.length);
		 
		     fos.flush();
		     fos.close();
		   System.out.println("file written:" +  myFile.getAbsolutePath());
   
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
}