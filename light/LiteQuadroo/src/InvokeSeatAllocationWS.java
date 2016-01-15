import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class InvokeSeatAllocationWS  extends QuadRR{
public String translateWord(String aword) throws MalformedURLException,
IOException {
 
//Code to make a webservice HTTP request
String responseString = "";
String outputString = "";
String wsURL = "http://localhost:7776/OnlyWords/services/OnlyWord";
URL url = new URL(wsURL);
URLConnection connection = url.openConnection();
HttpURLConnection httpConn = (HttpURLConnection)connection;
ByteArrayOutputStream bout = new ByteArrayOutputStream();
String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wor=\"http://words\"> " +
" <soapenv:Header/> " +
" <soapenv:Body> " +    
"	<wor:translateWord> " +
  "     <wor:aword>" + aword + "</wor:aword> " +
   " </wor:translateWord> " +
" </soapenv:Body> " +
"</soapenv:Envelope> " ;
 
byte[] buffer = new byte[xmlInput.length()];
buffer = xmlInput.getBytes();
bout.write(buffer);
byte[] b = bout.toByteArray();
String SOAPAction =
"http://words/OnlyWord/translateWordRequest";
// Set the appropriate HTTP parameters.
httpConn.setRequestProperty("Content-Length",
String.valueOf(b.length));
httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
httpConn.setRequestProperty("SOAPAction", SOAPAction);
httpConn.setRequestMethod("POST");
httpConn.setDoOutput(true);
httpConn.setDoInput(true);
OutputStream out = httpConn.getOutputStream();
//Write the content of the request to the outputstream of the HTTP Connection.
out.write(b);
out.close();
//Ready with sending the request.
 
//Read the response.
InputStreamReader isr =
new InputStreamReader(httpConn.getInputStream());
BufferedReader in = new BufferedReader(isr);
 
//Write the SOAP message response to a String.
while ((responseString = in.readLine()) != null) {
outputString = outputString + responseString;
}

System.out.println("system received:" + outputString);
//Parse the String output to a org.w3c.dom.Document and be able to reach every node with the org.w3c.dom API.
Document document = parseXmlFile(outputString);
NodeList nodeLst = document.getElementsByTagName("translateWordReturn");
String wordResult = nodeLst.item(0).getTextContent();
System.out.println("result of translation: " + wordResult);
 
//Write the SOAP message formatted to the console.
String formattedSOAPResponse = formatXML(outputString);
System.out.println(formattedSOAPResponse);
return wordResult;
}

public String formatXML(String unformattedXml) {
try {
Document document = parseXmlFile(unformattedXml);
OutputFormat format = new OutputFormat(document);
format.setIndenting(true);
format.setIndent(3);
format.setOmitXMLDeclaration(true);
Writer out = new StringWriter();
XMLSerializer serializer = new XMLSerializer(out, format);
serializer.serialize(document);
return out.toString();
} catch (IOException e) {
throw new RuntimeException(e);
}
}

private Document parseXmlFile(String in) {
try {
DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
DocumentBuilder db = dbf.newDocumentBuilder();
InputSource is = new InputSource(new StringReader(in));
return db.parse(is);
} catch (ParserConfigurationException e) {
throw new RuntimeException(e);
} catch (SAXException e) {
throw new RuntimeException(e);
} catch (IOException e) {
throw new RuntimeException(e);
}
}

public static void main(String[] args) {
InvokeSeatAllocationWS  WebserviceTester =
new InvokeSeatAllocationWS();
try {
 WebserviceTester.translateWord("amusing ");
} catch (MalformedURLException e) {
e.printStackTrace();
} catch (IOException e) {
e.printStackTrace();
}
}
}