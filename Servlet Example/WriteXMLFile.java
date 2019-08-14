package nalobaid;

import java.util.*;
import java.lang.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*; 
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 import javax.servlet.*;
import javax.servlet.http.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 //import javax.servlet.*;
//import javax.servlet.http.*;
 
 //static String FileName = "http://apps-swe642.vse.gmu.edu:8080/swe642/resources/nalobaid/HW5.xml";
public class WriteXMLFile {
 public void doGet (HttpServletRequest req, HttpServletResponse res)
   throws ServletException, IOException
{
 res.setContentType ("text/html");
   //Get the response's PrintWriter to return text to the client.
  PrintWriter out = res.getWriter ();

out.println ("<HTML>");
        out.println ("<HEAD>");
        out.println ("<TITLE>Norah Alobaidan</TITLE>");
        out.println ("<style type=\"text/css\">");
               out.println ("body {text-align:left;background-color:#FFFFCC;background-image:url();}");

        out.println ("h1{font-family:Cursive;color:000000;}");
        out.println ("p {font-family:Cursive;font-size:14px;font-style:normal;font-weight:normal;color:000000;}");
       out.println ("div.calendar {");
        out.println ("max-width: 240px;");
        out.println ("margin-left: auto;");
        out.println ("margin-right: auto;");
     out.println ("}");
          out.println ("</style>");
        out.println ("</head>");
        out.println ("<body>");
        out.println ("<h1>Norah Alobaidan</h1>");
        //out.println ("<h2>SWE 642 Web Page</h2>");
        out.println ("<form name=\"restaurant\"  onsubmit=\"return validateForm();\" method=\"post\">");
       
     
out.println ("<button type=\"button\" onclick=\"validateForm();\">Submit</button>");
out.println ("<input type=\"reset\" value=\"Reset Form\">");
out.println ("<input type=\"sort\" value=\"Sort\"/>");
out.println ("</form>");
out.println ("</body>");
out.println ("</html>");
}
	public void doPost (HttpServletRequest req, HttpServletResponse res)
   throws ServletException, IOException
{
  try{// first, set the "content type" header of the response
   res.setContentType ("text/html");
   //Get the response's PrintWriter to return text to the client.
  PrintWriter toClient = res.getWriter ();

   String para;
   Enumeration paraNames = req.getParameterNames ();
 toClient.println ("");
 DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 docFactory.setNamespaceAware(false);
		// root elements
		Document doc = docBuilder.newDocument();

  // toClient.println ("<Restaurant>");
  // toClient.print ("<Name>");
  // toClient.print("Mcdonalds");
  // toClient.print("</Name>
//<Location>Fairfax</Location>
//<Date>10-9-2013</Date>
//<Rating>Good</Rating>
//<Opinion>Good</Opinion>
//<Loved>Cheese Lobster</Loved>
//<Type>Sea Food</Type></Restaurant> */
	Element rootElement = doc.createElement("Restaurant");
		doc.appendChild(rootElement);
while (paraNames.hasMoreElements ())
   {  // For each parameter name.
   
//toClient.println ("document.getElementById(\"namel\").innerHTML = \"\";");
//toClient.println ("document.getElementById(\"locl\").innerHTML = \"\";");
//toClient.println ("document.getElementById(\"opinionl\").innerHTML = \"\";");
      para = (String) paraNames.nextElement ();
    //  if (!para.equalsIgnoreCase ("submit"))
      //{
        
         String[] values = req.getParameterValues (para);
Element loc = doc.createElement(para);
	rootElement.appendChild(loc);
   for (int i = 0; i < values.length; i++)
         
loc.appendChild(doc.createTextNode(values[i]));
//loc.appendChild(doc.createTextNode(req.getParameterValues(para)));


                              
         
      
  // }
   
   }
   String FileName0 = "/data/swe642fall2013/swe642/resources/nalobaid/rest0.xml";
   String FileName = "/data/swe642fall2013/swe642/resources/nalobaid/rest.xml";

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
      File statText = new File(FileName0);
 //if (!(statText.isFile()))
   //{
      //statText = new File(FileName0);
     //   }

    //else
    
 
        FileOutputStream is = new FileOutputStream(statText, true);
        OutputStreamWriter osw = new OutputStreamWriter(is);    
        Writer w = new BufferedWriter(osw);
 StreamResult result = new StreamResult(statText);
 
		transformer.transform(source, result);
 toClient.write("<b> Restaurant Information has been saved. </b>");
 

        StringWriter sw = new StringWriter();
         result = new StreamResult(sw);
        source = new DOMSource(doc);
        transformer.transform(source, result);
        String xmlString = sw.toString();

        File file = new File(FileName);
        FileWriter fw=new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(xmlString);
        bw.flush();
        bw.close();
		//System.out.println("File saved!");
      }
 
	   catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (Exception e) {
		e.printStackTrace();
	  }
     
	}
  
}