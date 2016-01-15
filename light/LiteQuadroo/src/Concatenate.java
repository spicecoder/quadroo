
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

public class Concatenate {
	public static  void  concatenate(String inf, BufferedOutputStream bos  )
	{
		
		 ByteBuffer buffer = ByteBuffer.allocate(1000000);
		   byte [] mybytearray  = buffer.array();	
  //  byte [] mybytearray  = new byte [10000000];
      
    FileInputStream ifs = null;
   
	try{	
		
		
		ifs = new FileInputStream(inf );
	 
	
    BufferedInputStream bis = new BufferedInputStream(ifs);
    
	int    bytesRead = bis.read(mybytearray,0,mybytearray.length);
	 byte[] finalbytearray = new byte[bytesRead];
    System.arraycopy(mybytearray, 0,finalbytearray, 0, bytesRead);
	 
  
 


 System.out.println("appending..."  +finalbytearray.length );
 
 //do it right ..acumulation
  
 bos.write(finalbytearray,0,finalbytearray.length);
 

	 
	  
	  System.out.println("bytes written from:" +inf+ finalbytearray.length);
	 
  }
 
  catch (Exception e) {
	  e.printStackTrace();
	  //return "error";
	  } 
   		
  }
	
	
	

}
