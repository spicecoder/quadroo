import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PullPage_S  extends QuadRR {

public PullPage_S(QuadRR rr) {
	  super(rr);
	  // W contains the page file name in the WebContent folder
	  // the read html [skipping the first line of html5 
	  // the verb places the page source in WA[0]
	  // S contains continuity info
	  //
	   
	  if (allowed){
		  FileInputStream fis = null;
	        BufferedReader reader = null;
	        String dir = servletcontext.getInitParameter("pagepulldir");
	      
	  try {
		//reading file line by line in Java using BufferedReader       
	       
		 
	            fis = new FileInputStream(dir+'\\'+ W);
	            reader = new BufferedReader(new InputStreamReader(fis));
	          
	            System.out.println("Reading File line by line,skipping first one using BufferedReader");
	          
	            String line = reader.readLine();
	            String Blines = "";
	            //omit first line
	            if(line != null) {
	             line = reader.readLine(); }
	            if(line != null) {
		             line = reader.readLine(); }
		            /*
	            
	            /*
	             * 
	             * 
	             *  StringBuilder buffer = new StringBuilder(count * 16);
	for (int i = 0; i < count; i++) { buffer.append(“test”); }
	String temp = buffer.toString();
	             */
	            StringBuilder buffer = new StringBuilder(3000);
	            //max file size 3000 bytes
	            while(line != null){
	             //   System.out.println(line);
	                line = reader.readLine();
	                buffer.append(line);
	                
	            }   
	            WA.put(0, (buffer.toString()).trim());
	           
	            System.out.println("HTML read:" + S);
	          
	        } catch (IOException ex) {
	            Logger.getLogger(BufferedReaderExample.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (Exception ex) {
	            Logger.getLogger(BufferedReaderExample.class.getName()).log(Level.SEVERE, null, ex);
	          
	        } finally {
	            try {
	                reader.close();
	                fis.close();
	            } catch (IOException ex) {
	                Logger.getLogger(BufferedReaderExample.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
		  


		  }  
		  }  

}