import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

public class ConcatFiles_S  extends QuadRR {
	  

	
	public ConcatFiles_S(QuadRR rr) {

	super(rr);
	if(allowed) {
		
	  //  W holds th filenam to be concatenated , eher each filname carries a number at the end
	// WA[0]  can have the numbers  
//	public void ConcatFiles() 
	 FilenameFilter textFilter = new FilenameFilter() {
		public boolean accept(File dir, String name) {
			String lowercaseName = name.toLowerCase();
			if (lowercaseName.contains("$") ) {
				return true;
			} else {
				return false;
			}
		}
	};
	
	S = S.trim();
	 String homedir = "C:/ActApprent/WS/NowIamIn/Webcontent/moodresources/";
	
	File homep  = new File(homedir + S ); // current directory
	
    
 
	FileOutputStream ofs = null;
	try {
		ofs = new FileOutputStream(homedir+ S + "/mood.png");
	
    
    BufferedOutputStream bos = new BufferedOutputStream(ofs);
     
	File[] files = homep.listFiles(textFilter);
	String[ ] filenames = new String[files.length];
	
	int ii = 0;
 for (File file : files) {
		filenames[ii] = file.getAbsolutePath();
		
		ii = ii + 1;
	}
 System.out.println(" fils:" + filenames.length);
 
	Arrays.sort(filenames, 0, filenames.length);
	for (String file : filenames) {
		
		 
		   Concatenate.concatenate(file, bos );
		   boolean success = (new File
			         (file)).delete();
		   System.out.print("  processed   file:"+ file);
	}
	

      bos.flush();
	  bos.close();
//	  ofs.close();
       
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   System.out.println("file written:" );
	 
	}
} 
	 
		  
        
	}  
 