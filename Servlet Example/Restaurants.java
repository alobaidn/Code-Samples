package nalobaid;

// Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;

// Import Java Libraries
import java.io.*;
import java.util.*;
import java.lang.*;

// Import SMTP Class
import sun.net.smtp.SmtpClient;
import org.xml.sax.*;


//***********************************************************************

public class Restaurants extends HttpServlet
{
   private static String Servlet = "http://cs.gmu.edu:8080/swe642/servlet/nalobaid.Restaurants"; 
   public  static String classWebSiteURL = "http://www.cs.gmu.edu/~offutt/classes/642/";
   public  static String FileName = "/data/swe642fall2013/swe642/resources/nalobaid/HW5.xml";
   //"http://apps-swe642.vse.gmu.edu:8080/swe642/resources/nalobaid/HW5.xml";
   private static String name;
   private  static String loc;
   private static String opinion;
   private static String date;
   private static String hated;
   private static String loved;
   private static String r;
   public static ArrayList RestaurantA; // The list of students


/** *****************************************************
 *  Overrides HttpServlet's doGet().
 *  For completeness.
********************************************************* */
public void doGet (HttpServletRequest req, HttpServletResponse res)
   throws ServletException, IOException
{
   res.setContentType ("TEXT/HTML");
   PrintWriter out = res.getWriter ();
   printForm (req, out, "blank", "");
  out.close ();
   
  // if (!(new File (FileName).exists()))
 //  {
   //   FileWriter datafile = new FileWriter (FileName);
   //   datafile.close ();
 //  }
}
// used as updating flag, if true, update available; if false, update not available
static boolean update = false;

/** *****************************************************
 *  Overrides HttpServlet's doPost().
********************************************************* */
public void doPost (HttpServletRequest req, HttpServletResponse res)
   throws ServletException, IOException
{
   String message;
   res.setContentType ("TEXT/HTML");
   PrintWriter out = res.getWriter ();
   PrintWriter toClient = res.getWriter ();
      name = req.getParameter ("name");
     loc = req.getParameter ("location");
      opinion   = req.getParameter ("opinion");
      date   = req.getParameter ("datefield");
      loved   = req.getParameter ("loved");
      hated   = req.getParameter ("hated");
    r   = req.getParameter ("rating");
      
   //   if (verifyEmptyXMLFile (FileName) == true)
  //    {
         loadRestList(FileName);
                     appendRestInfo (req);
            //printACK (out, "Restaurant information has been submitted.");
       //  }
//else
//appendRestInfo (req);

   WriteXMLFile xml = new WriteXMLFile();
  
   String para;
   Enumeration paraNames = req.getParameterNames ();

   toClient.println ("<HTML>");
   toClient.println ("<HEAD>");
   toClient.println ("<TITLE>Simple Form Handler</TITLE>");
   toClient.println ("</HEAD>");

   toClient.println ("<BODY BGColor=\"#EEEEEE\">");
   toClient.println ("");
   toClient.println ("<CENTER><H2>Restaurant Information</H2></CENTER>");
   toClient.println ("<P>");
   toClient.println ("The following table lists all parameter names and");
   toClient.println ("their values that were submitted from your form.");
   toClient.println ("</P>");

   toClient.println ("");
   toClient.println ("<P>");
   toClient.println ("<TABLE cellSpacing=1 cellPadding=1 width=\"75%\" border=1 bgColor=lavender>");
   toClient.println ("");
   toClient.println ("  <TR BGColor=\"#FFFFFF\">");
   toClient.println ("   <TH Align=\"center\"><STRONG>Parameter</STRONG></TD>");
   toClient.println ("   <TH Align=\"center\"><STRONG>Value</STRONG></TD>");
   toClient.println ("  </TR>");

   while (paraNames.hasMoreElements ())
   {  // For each parameter name.
   
//toClient.println ("document.getElementById(\"namel\").innerHTML = \"\";");
//toClient.println ("document.getElementById(\"locl\").innerHTML = \"\";");
//toClient.println ("document.getElementById(\"opinionl\").innerHTML = \"\";");
      para = (String) paraNames.nextElement ();
      if (!para.equalsIgnoreCase ("submit"))
      {
         toClient.println ("  <TR>");
         toClient.println ("    <TD style=\"WIDTH: 20%\" width=\"20%\"><STRONG>"
                          + para + "</STRONG></TD>");

         String[] values = req.getParameterValues (para);

         if (values != null && !values[0].equals(""))
            toClient.println ("    <TD>" + values[0] + "</TD></TR>");
         else
            toClient.println ("    <TD>&nbsp;</TD></TR>");

         for (int i = 0; i < values.length; i++)
         {
            if (!values[i].equals(""))
            {
               toClient.println ("  <TR>");
               toClient.println ("    <TD style=\"WIDTH: 20%\" width=\"20%\">&nbsp;</TD>");
               toClient.println ("    <TD>" + values[i] + "</TD></TR>");
            }
            else if (values[0].equals(null)) 
            {
            toClient.println ("document.getElementById(\"namel\").innerHTML = \"Please enter a name\";");
toClient.println ("document.getElementById(\"namel\").style.color = \"Red\";");
            }
            
            else if (values[1].equals("")) 
            {
            toClient.println ("loc.innerHTML = \"Please enter a location\";");
toClient.println ("loc.style.color = \"Red\";");
             
         }
         else if (values[2].equals("")) 
            {
            toClient.println ("document.getElementById(\"opinionl\").innerHTML = \"Please enter your opinion\";");
toClient.println ("document.getElementById(\"opinionl\").style.color = \"Red\";");
             
         }

         
      }
   }
   
   }
        toClient.println ("</TABLE>");
   toClient.println ("");
   toClient.println ("</BODY>");
   toClient.println ("</HTML>");

   toClient.println ("");
 xml.doPost(req, res);
    

   // Close the writer; the response is done.
  

 
    toClient.close();
  
xml.doGet(req, res);
  // String action = req.getParameter ("submit");
   //if (action.equals ("Submit")) // new student's submission
  // {
          // }
    //  else
     // {
        // appendRestInfo (req);
         //printACK (out, "Restaurant information has been submitted.");
         // printACK (out, FName, LName, PIN, "Your information has been submitted.");
    //  }
   
  
   
 //  out.close ();*/

  
} // end of doPost() method


/** *****************************************************
 *  Prints the Information Page.
 *  flag = "blank", prints blank form
 *       = "login", prints form with FName and LName prefilled
 *       = "retrieve", prints form with retrieved data
********************************************************* */
//private void printForm (PrintWriter out, String flag, String errmsg)
   //throws IOExceptionprivate
void printForm (HttpServletRequest req, PrintWriter out, String flag, String errmsg)
      throws ServletException, IOException
{
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
     out.println ("div.calendar table {");
        out.println ("width: 100%;");
      out.println ("}");
      out.println ("div.dateField {");
        out.println ("width: 140px;");
        out.println ("padding: 6px;");
        out.println ("-webkit-border-radius: 6px;");
        out.println ("-moz-border-radius: 6px;");
        out.println ("color: #555;");
        out.println ("background-color: white;");
        out.println ("margin-left: auto;");
        out.println ("margin-right: auto;");
        out.println ("text-align: center;");
      out.println ("}");
      
 out.println ("#rateStatus{float:left; clear:both; width:100%; height:20px;}");
    out.println ("#rateMe{float:left; clear:both; width:100%; height:auto; padding:0px; margin:0px;}");
    out.println ("#rateMe li{float:left;list-style:none;}");
    out.println ("#rateMe li a:hover,");
    out.println ("#rateMe .on{background:url(http://apps-swe642.vse.gmu.edu:8080/swe642/resources/nalobaid/star-on.png) no-repeat;}");
    out.println ("#rateMe a{float:left;background:url(http://apps-swe642.vse.gmu.edu:8080/swe642/resources/nalobaid/star-off.png) no-repeat;width:12px; height:12px;}");
    out.println ("#ratingSaved{display:none;}");
    out.println (".saved{color:red; }");

        out.println ("</style>");
      
   out.println ("<script type=\"text/javascript\" language = \"javascript\" src=\"http://apps-swe642.vse.gmu.edu:8080/swe642/resources/nalobaid/prototype.js\"></script>");
 out.println ("<script type=\"text/javascript\" language = \"javascript\" src=\"http://apps-swe642.vse.gmu.edu:8080/swe642/resources/nalobaid/calendarview.js\"></script>");
 out.println ("<script type=\"text/javascript\" language=\"javascript\" src=\"http://apps-swe642.vse.gmu.edu:8080/swe642/resources/nalobaid/ratingsys.js\"></script>"); 

        out.println ("<script>");
        out.println ("function other()");
out.println ("{");
  out.println ("document.getElementById(\"Other1\").style.display='inline';");

out.println ("}");
out.println ("function validateForm()");
out.println ("{");
out.println ("var count = 0;");
out.println ("var name = document.forms[\"restaurant\"][\"name\"].value;");
out.println ("var x = document.forms[\"restaurant\"][\"location\"].value;");
out.println ("var o=document.forms[\"restaurant\"][\"opinion\"].value;");

out.println ("document.getElementById(\"namel\").innerHTML = \"\";");
out.println ("document.getElementById(\"locl\").innerHTML = \"\";");
out.println ("document.getElementById(\"opinionl\").innerHTML = \"\";");
out.println ("if ( name == '')");
out.println ("{");

out.println ("count++;");
out.println ("document.getElementById(\"namel\").innerHTML = \"Please enter a name\";");
out.println ("document.getElementById(\"namel\").style.color = \"Red\";");
//return false;
out.println ("}");

out.println ("if ( x == '')");
out.println ("{");

out.println ("count++;");
out.println ("var loc = document.getElementById(\"locl\");");

out.println ("loc.innerHTML = \"Please enter a location\";");
out.println ("loc.style.color = \"Red\";");
//return false;
out.println ("}");

out.println ("if ( o == '')");
out.println ("{");
//alert("Please enter your opinion");
out.println ("count++;");
out.println ("document.getElementById(\"opinionl\").innerHTML = \"Please enter your opinion\";");
out.println ("document.getElementById(\"opinionl\").style.color = \"Red\";");
//return false;
out.println ("}");

out.println ("if(count>0)");
        out.println ("{");
              // out.println ("document.getElementById(\"divError").innerHTML = err.message;
//document.write("Hello");
             //   err.field.focus();
               out.println ("return false;");        
                
        out.println ("}"); 
out.println("document.getElementById(\"date\").value = document.getElementById(\"embeddedDateField\").value;");
out.println ("return true;");
out.println ("}");
out.println ("function setupCalendars() {");
        // Embedded Calendar
        out.println ("Calendar.setup(");
          out.println ("{");
            out.println ("dateField: 'datefield',");
            out.println ("parentElement: 'embeddedCalendar'");
          out.println ("}");
        out.println (")");


        // Popup Calendar
        out.println ("Calendar.setup(");
          out.println ("{");
            out.println ("dateField: 'popupDateField',");
            out.println ("triggerElement: 'popupDateField'");
          out.println ("}");
        out.println (")");
      out.println ("}");
      out.println ("Event.observe(window, 'load', function() { setupCalendars() })");
      
      
              out.println ("</script>");
        out.println ("</head>");
        out.println ("<body>");
        out.println ("<h1>Norah Alobaidan</h1>");
        //out.println ("<h2>SWE 642 Web Page</h2>");
        out.println ("<form name=\"restaurant\"  onsubmit=\"return validateForm();\" method=\"post\">");
       
       out.println ("Please Enter the Restaurant's Name: <input title=\"name\" id=\"name\" name=\"name\" type=\"text\" size=\"20\" /> <label id=\"namel\"></label>");

out.println ("<br />");
out.println ("<br />");
out.println ("Location: <input title=\"location\" id=\"location\" name=\"location\" type=\"text\" size=\"20\" /> <label id=\"locl\"></label>");
out.println ("<br />");
out.println ("<br />");
out.println ("Type of Food:");
out.println ("<br />");
out.println ("<input type=\"radio\" name=\"type\" value=\"Indian\"> Indian <br>");
out.println ("<input type=\"radio\" name=\"type\" value=\"Italian\" checked> Italian <br>");
out.println ("<input type=\"radio\" name=\"type\" value=\"Chinese\"> Chinese <br/>");
out.println ("<input type=\"radio\" name=\"type\" value=\"Fast Food\"> Fast Food <br/>");
out.println ("<input type=\"radio\" name=\"type\" value=\"Japanese\"> Japanese <br>");
out.println ("<input type=\"radio\" name=\"type\" value=\"Other\" onclick=\"other();\"> Other <br>");
out.println ("<br />");
       
  out.println ("<div id=\"Other1\" style=\"display:none;\"> Other <input type=\"Text\" name=\"Other1\">");
out.println ("</div>");
out.println ("<br />");

out.println ("Last Time Visited ( Select a Date ):");
 out.println ("<div style=\"float: center; width: 20%\">");
      out.println ("<div style=\"height: 200px; background-color: #efefef; padding: 10px; -webkit-border-radius: 12px; -moz-border-radius: 12px; margin-right: 10px\">");
        
        out.println ("<div id=\"embeddedExample\" style=\"\">");
          out.println ("<div id=\"embeddedCalendar\" style=\"margin-left: auto; margin-right: auto; margin-center: auto;\">");
          out.println ("</div>");
          out.println ("<br />");
          //out.println ("<div id=\"embeddedDateField\" class=\"dateField\">");
            //out.println ("Select Date");
          //out.println ("</div>");
          
        out.println ("</div>");
      out.println ("</div>");
    out.println ("</div>");
     out.println ("<br />");
              out.println ("<br />");
          out.println ("Date: <input title=\"date\" id=\"datefield\" name=\"datefield\" type=\"text\" size=\"20\" />");

            
out.println ("<br />");

out.println ("Opinion: <input title=\"opinion\" id=\"opinion\" name=\"opinion\" type=\"text\" size=\"20\" /><br /><label id=\"opinionl\"></label>");
out.println ("<br />");
out.println ("<span id=\"rateStatus\">Rate this </span>");
out.println ("<span id=\"ratingSaved\">Rating Saved!</span>"); 
out.println ("<div id=\"rateMe\" title=\"Rate this restaurant\">");
    out.println ("<a onclick=\"rateIt(this)\" id=\"_1\" title=\"ehh..bad\" onmouseover=\"rating(this)\" onmouseout=\"off(this)\"></a>");
 out.println ("<a onclick=\"rateIt(this)\" id=\"_2\" title=\"Poor\" onmouseover=\"rating(this)\" onmouseout=\"off(this)\"></a>");
    out.println ("<a onclick=\"rateIt(this)\" id=\"_3\" title=\"Regular\" onmouseover=\"rating(this)\" onmouseout=\"off(this)\"></a>");
out.println ("<a onclick=\"rateIt(this)\" id=\"_4\" title=\"Good\" onmouseover=\"rating(this)\" onmouseout=\"off(this)\"></a>");
out.println ("<a onclick=\"rateIt(this)\" id=\"_5\" title=\"Great!!\" onmouseover=\"rating(this)\" onmouseout=\"off(this)\"></a>");
out.println ("</div>");
out.println ("Food you Loved the Most:  <input title=\"loved\" id=\"loved\" name=\"loved\" type=\"text\" size=\"20\" /><br />");
out.println ("<br />");
out.println ("Food you Hated the Most:<input title=\"hated\" id=\"hated\" name=\"hated\" type=\"text\" size=\"20\" /><br />");
out.println ("<br />");

out.println ("<button type=\"button\" onclick=\"validateForm();\">Submit</button>");
out.println ("<input type=\"reset\" value=\"Reset Form\">");
out.println ("<input type=\"submit\" value=\"SUBMIT\" onclick=\"validateForm();\"/>");
out.println ("</form>");
out.println ("</body>");
out.println ("</html>");
} // end of method printForm()

/** *****************************************************
 *  Sorts the array by last  name.
********************************************************* */
private ArrayList sortByName (ArrayList sl)
{
   int n = sl.size();
   String tmp;
   String na[]= new String [n];
   String loc[]= new String [n];
   String op[]= new String [n];
   String dat[]= new String [n];
   String lov[]= new String [n];
   String hat[]= new String [n];
   String r[]= new String [n];
   
   for (int i = 0; i < n; i++)
   {
      Restaurant rest = (Restaurant) sl.get (i);
      na[i] = rest.getName();
      loc[i] = rest.getLoc();
      op[i] = rest.getOpinion();
      dat[i] = rest.getDate();
      lov[i] = rest.getLoved();
      hat[i] = rest.getHated();
      r[i] = rest.getRating();
        }

   //sort by last name
   for (int i = 0; i < n-1; i++)
      for (int j = i+1; j < n; j++)
      {
         if (na[i].compareTo (na[j]) > 0)
         {
            tmp = na[i]; na[i]= na[j]; na[j]= tmp;
            tmp = loc[i]; loc[i]= loc[j]; loc[j]= tmp;
            tmp = op[i]; op[i]= op[j]; op[j]= tmp;
            tmp = dat[i]; dat[i]= dat[j]; dat[j]= tmp;
            tmp = lov[i]; lov[i]= lov[j]; lov[j]= tmp;
            tmp = hat[i]; hat[i]= hat[j]; hat[j]= tmp;
            tmp = r[i]; r[i]= r[j]; r[j]= tmp;
         }
      }
   ArrayList sl_tmp = new ArrayList(); // temperary list of students
   for (int i = 0; i < n; i++ )
   {
      sl_tmp.add (new Restaurant (na[i], loc[i], op[i], r[i], dat[i],  hat[i], lov[i]));
   }
   return sl_tmp;
} // end of method sortByLastName()

/** *****************************************************
 * Removes a less than character '<' from a string
 * XML does not like them. (It crashes tomcat.)
********************************************************* */
private String trimLT (String s)
{
   if (s.indexOf ('<') >= 0)
   {  // Found one.
      return (s.replace ('<', '#'));
   }
   else
      return s;
}

/** *****************************************************
 *  Appends info to XML file for new student.
********************************************************* */
private void appendRestInfo (HttpServletRequest req)
   throws ServletException, IOException
{
   writePrefixToFile (FileName);

   //if StudList has not been initialized, do it here
   if (RestaurantA == null)
   {
      RestaurantA = new ArrayList();
   }
   name        = trimLT (req.getParameter ("name"));
   loc        = trimLT (req.getParameter ("location"));
   opinion          = trimLT (req.getParameter ("opinion"));
   date = trimLT (req.getParameter ("date"));
   hated       = trimLT (req.getParameter ("hated"));
   loved   = trimLT (req.getParameter ("loved"));
   r        = "";
   
   RestaurantA.add (new Restaurant (name, loc, opinion,r, date, hated, loved));
   RestaurantA = sortByName (RestaurantA);
   for (int i = 0; i < RestaurantA.size(); i++ )
   {
      Restaurant rest = (Restaurant) RestaurantA.get (i);
      name          = rest.getName();
      loc          = rest.getLoc();
      opinion            = rest.getOpinion();
      date   = rest.getDate();
      hated          = rest.getHated();
      loved     = rest.getLoved();
     r          = rest.getRating();
            String message = getXML();
      writeToFile (FileName, message);
   }
   writePostfixToFile (FileName);
} // end of method appendStudInfo()

/** *****************************************************
 *  Verify if the source XML file is empty.
 *  if empty, returns false, else returns true.
********************************************************* */
public static boolean verifyEmptyXMLFile (String fileName) throws IOException, FileNotFoundException
{
   BufferedReader fin = new BufferedReader (new FileReader (fileName));
   String s;

   try
   {
      while ((s = fin.readLine() ) != null)
      {
         s = s.trim();
         if (!s.equals (""))
            return true;
      }
   }
   catch (IOException e)
   {
      System.out.println ("File error: " + fileName);
   }
   fin.close();

   RestaurantA = null; //reset StudList for empty file case

   return false;
} // end of method verifyEmptyXMLFile()

/** *****************************************************
 *  Read students' info from XML file to an Arraylist.
********************************************************* */
public static void loadRestList (String fileName) throws IOException
{
   try
   {
      ParserXML xp = new ParserXML();
      xp.parse (xp.readFile (fileName));
      RestaurantA = xp.RestaurantA;
   }
   catch (Exception e)
   {
      System.out.println ("Exception: " + e);
      System.out.println ("XML SAXParse error in XMLParser.readDoc()");
   }
} // end of method loadStudInfo()

/* ======================================================== */
static int existIndex; //store the student's index in StudList.
/* ======================================================== */
/** *****************************************************
 *  Puts the parameters into a string buffer as XML.
 *  Returns a string.
********************************************************* */
private String getXML() throws IOException
{
   StringBuffer tempStringBuffer = new StringBuffer (4096);

   tempStringBuffer.append ("  <Restaurant Information>\n");
   // Format the contact info.
   tempStringBuffer.append ("    <Name>");
   tempStringBuffer.append (name);
   tempStringBuffer.append ("</Name>\n");
   tempStringBuffer.append ("    <Location>");
   tempStringBuffer.append (loc);
   tempStringBuffer.append ("</Location>\n");
   tempStringBuffer.append ("    <Opinion>");
   tempStringBuffer.append (opinion);
   tempStringBuffer.append ("</Opinion>\n");
 tempStringBuffer.append ("    <Date>");
   tempStringBuffer.append (date);
   tempStringBuffer.append ("</Date>\n");
    tempStringBuffer.append ("    <Rating>");
   tempStringBuffer.append (r);
   tempStringBuffer.append ("</Rating>\n");
    tempStringBuffer.append ("    <Hated>");
   tempStringBuffer.append (hated);
   tempStringBuffer.append ("</Hated>\n");
    tempStringBuffer.append ("    <Loved>");
   tempStringBuffer.append (loved);
   tempStringBuffer.append ("</Loved>\n");
   // Handle several email addresses, separated by a carriage
   // return and line feed.
   // Loop through the email address looking for the next
   // carriage return, stop when there is none.
    tempStringBuffer.append ("  </Restaurant Information>\n");

   return (tempStringBuffer.toString());
} // end of getXML() emthod

/** *****************************************************
 *  Writes the Prefix into the XML file
********************************************************* */
private void writePrefixToFile (String fileName)
{
   try
   {
      FileWriter datafile = new FileWriter (fileName);
      datafile.write ("<SWE>\n");
      datafile.write ("\n");
      datafile.close ();
   }
   catch (IOException e)
   {
      log ("Error occurred while writing to file", e);
   }
}

/** *****************************************************
 *  Writes the Postfix into the XML file
********************************************************* */
private void writePostfixToFile (String fileName)
{
   try
   {
      FileWriter datafile = new FileWriter (fileName, true);
      datafile.write ("</SWE>");
      datafile.close ();
   }
   catch (IOException e)
   {
      log ("Error occurred while writing to file", e);
   }
}

/** *****************************************************
 *  Saves the information into a file
********************************************************* */
private void writeToFile (String fileName, String Mes)
{
   try
   {
      FileWriter datafile = new FileWriter (fileName, true);
      datafile.write (Mes);
      datafile.write ("\n");
      datafile.close ();
   }
   catch (IOException e)
   {
      log ("Error occurred while writing to file", e);
   }
}

/** *****************************************************
 *  Prints the acknowledgement page.
********************************************************* */
} // end of StudinfoSysDemo class
