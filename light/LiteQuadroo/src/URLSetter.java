

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class URLSetter {

public void setURL(String zproj){
	Map datamodel = new HashMap();
	datamodel.put("proj", zproj);	
//	
	  TemplateRenderer tr = new TemplateRenderer();
	  System.out.println("rendered");
		// Process the template using FreeMarker
		try {
			tr.freemarkerDo(datamodel, "patterns/indexTemplate.html", "WebContent/index.html");
			
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
 
} 

public static  void  main(String[] args){
    String zproj = args[0];
    URLSetter fm = new URLSetter();
	
	fm.setURL(zproj);
	
	
 	
	
}

}
