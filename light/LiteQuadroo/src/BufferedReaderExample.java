import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BufferedReaderExample {   

    public static void main(String args[]) {
      
        //reading file line by line in Java using BufferedReader       
        FileInputStream fis = null;
        BufferedReader reader = null;
      
        try {
            fis = new FileInputStream("WebContent/MakeMoodTrail.html");
            reader = new BufferedReader(new InputStreamReader(fis));
          
            System.out.println("Reading File line by line,skipping first one using BufferedReader");
          
            String line = reader.readLine();
            String Blines = "";
            //omit first line
            if(line != null) {
             line = reader.readLine(); }
            /*
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
            String temp = buffer.toString();
            temp = temp.trim();
            System.out.println("HTML read:" + temp);
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BufferedReaderExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
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