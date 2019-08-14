/** *****************************************************************
    ParserXML.java

        @author Quansheng Xiao

        @version 1.0    (10/25/2001)

  parses a XML file
********************************************************************* */
package nalobaid;

import java.io.*;
import java.util.*;

import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

// ParserXML class
//
// ****************  OPERATIONS  ************************************************
// void ParserXML ()  --> default constructor
// public static Document readFile() --> converts a filename to a uri for parsing
// public static Document readDoc() --> creates a DOM object
// public void parse() --> parses a xml file
// public void parseRec() --> parses throught a file
// public void parseChildren() --> recursively parses through a file
//*******************************************************************************

public class ParserXML
{
/*
  public static void main(String argv[]) throws Exception {
    if (argv.length != 1) {
      System.out.println("usage: java swe642.ParserXML xmlFile");
      System.exit(1);
    } 
    ParserXML dmW = new ParserXML();
    dmW.parse(dmW.readFile(argv[0]));

System.out.println("StudList length: " + dmW.StudList.size());
  } 
*/

//Constructor
public ParserXML()
{
}


//converts a filename to a uri for parsing
public Document readFile(String fileName) throws Exception
{
  if (null == fileName)
  {
    throw new Exception("no fileName for readFile()");
  } 
  String uri = "file:" + new File(fileName).getAbsolutePath();
  return readDoc(uri);
} 


//creates a DOM object
public Document readDoc(String uri) throws Exception
{
  Document doc;
  try {// create a DOM object using JAXP abstract classes
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    doc = db.parse(uri);
    return doc;
  } catch (SAXParseException ex) {
    //throw (ex);
    System.out.println("SAXParseException: " + ex);
    System.out.println("SAXParse error in XMLParser.readDoc(" + uri + ")");
    return null;
  } catch (SAXException ex) {// do some logging
    //throw (ex);
    System.out.println("SAXException: " + ex);
    System.out.println("SAXParse error in XMLParser.readDoc(" + uri + ")");
    return null;
  } 
} 


// stores the students' info after parses the xml file
public ArrayList RestaurantA;// = new ArrayList();

private static String n="", loc="", o="", h="",l="", d="", r =""; 
private static boolean recordStart = false;
private static StringBuffer e = new StringBuffer (1024);
private static String previousNodeName = "";


//parses a xml file
public void parse(Node node) throws IOException, SAXException
{
  if(node == null)
  {
    System.out.println("SAXParse error from XML file.");
    System.exit(0);
  }
  else
  {
    //reset variables, critical important
    RestaurantA = new ArrayList();
    recordStart = false;
    previousNodeName = "";
    e.setLength(0);

    parseRec(node);

    //handles the last student's info
   // email = e.toString();
   
    RestaurantA.add(new Restaurant(n, loc, o, r, d, h, l));
  }
} // end of parse()


//parses throught a file
public void parseRec(Node node) throws IOException, SAXException
{
  short nodeType = node.getNodeType();

  if (nodeType == Node.ELEMENT_NODE) {
    String nodeName = node.getNodeName();
    previousNodeName = nodeName;

    if(nodeName.equals("Restaurant"))
    {
       if(recordStart == false)
       {
          recordStart = true;
       }
       else
       {
         // email = e.toString();
          RestaurantA.add(new Restaurant(n, loc, o, r, d, h, l));
          e.setLength(0); //reset e
       }
    }
    parseChildren(node);
  }

  else if (nodeType == Node.TEXT_NODE) {
    //if(recordStart == true)
    {
      if(previousNodeName.equals("Name"))
        n = node.getNodeValue().trim();
      else if(previousNodeName.equals("Location"))
        loc = node.getNodeValue().trim();
      else if(previousNodeName.equals("Opinion"))
        o = node.getNodeValue().trim();
      else if(previousNodeName.equals("Rating"))
        r = node.getNodeValue().trim();
      else if(previousNodeName.equals("Date"))
        d = node.getNodeValue().trim();
      else if(previousNodeName.equals("Food Hated Most"))
        h = node.getNodeValue().trim();
      else if(previousNodeName.equals("Food Loved Most"))
        l = node.getNodeValue().trim();

         previousNodeName = "";//reset to empty
    }
  }

  else if (nodeType == Node.DOCUMENT_NODE) {
    parseChildren(node);
  }

  else {
    //does nothing
  } 
}// end of parseRec()


//recursively parses through a file
public void parseChildren(Node node) throws IOException, SAXException
{
  NodeList children = node.getChildNodes();
  for (int i = 0; i < children.getLength(); i++) {
    parseRec(children.item(i));
  }
}

}  // End Parser XML
