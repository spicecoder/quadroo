

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class TemplateRenderer
{
	public static void main(String[] args)
	{
		// Add the values in the datamodel
		Map datamodel = new HashMap();
		datamodel.put("pet", "Bunny");
		datamodel.put("number", new Integer(6));
         TemplateRenderer tr = new TemplateRenderer();
		// Process the template using FreeMarker
		try {
			tr.freemarkerDo(datamodel, "example1.html", "outtext.html");
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	
	// Process a template using FreeMarker and print the results
	public  void freemarkerDo(Map datamodel, String template , String futput) throws Exception
	{
		Configuration cfg = new Configuration();
		Template tpl = cfg.getTemplate(template);
		OutputStreamWriter output = new OutputStreamWriter(System.out);
		//Writer outputx =  File  
		Writer writer = new FileWriter(futput);

		tpl.process(datamodel, writer);
	}
}