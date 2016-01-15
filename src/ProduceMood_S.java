import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

public class ProduceMood_S  extends QuadRR {

public ProduceMood_S(QuadRR rr) {
	  super(rr);
	  
	  try {
		  //This verb is built for scene "makeMood";at execution time S is expected to be "makeMood"
		  // that will mean byD has all words and values supplied at run time
		  
		  // we need to bring in the logic to write to mongodb, which being with the url is a easy
		  //job not necessary to poll- the scene hold information about picture, words that goes with the scene. 
		  
		  
			//byD is already loadd with th corrct values by matching words.now it is time to return the string for the mood
			//in url
			String[] val =null;
			for (int im =0;im < byD.WA.length();im++) { 
	 	 		 if ((String)(byD.WA.get(im))  ==  "moodmake1" ) {val[0] = (String) byD.mA.get(im) ; }
	 	 		if ((String)(byD.WA.get(im))  ==  "moodmake2" ) {val[1] = (String) byD.mA.get(im) ; }
	 	 		if ((String)(byD.WA.get(im))  ==  "moodmake3" ) {val[2] = (String) byD.mA.get(im) ; }
	 	 		if ((String)(byD.WA.get(im))  ==  "moodmake4" ) {val[3] = (String) byD.mA.get(im) ; }
	 	 		if ((String)(byD.WA.get(im))  ==  "moodmake5" ) {val[4] = (String) byD.mA.get(im) ; }
	 	 		 
	 	 		 		} 	
			String mood = val[0] + "_" + val[1] + "_" + val[2] + "_" + val[3] + "_" + val[4]+   "_" + val[5];
	 	 // put mood in reply  
		  }



		   catch (Exception e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		 rid = -1;
		  }  

}
}