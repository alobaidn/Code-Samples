/*
  Jeremiah Howdeshell and Chaoran Lu
  SWE432-001
  HW11 : Login Validation Servlet
*/
package HLVoting;

// Import Java Libraries
import java.io.*;
import java.util.*;
import java.util.Arrays;

// Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;

public class Predict extends HttpServlet
{  
	public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException
  {
    HttpSession session = req.getSession(true); //get or create session object
    ServletContext context = session.getServletContext(); //get context object

    //get session attributes
    String username = (String) session.getAttribute("username");
    String pred = nl2br(req.getParameter("prediction"));
    String args = nl2br(req.getParameter("arguments"));
    int[] votes = new int[3];
    
    //create arraylist<PredObj> to import context object predArr or initialize it
    List<PredObj> predArr;
    if(context.getAttribute("jhowdes1_predArr")==null) {
      context.setAttribute("jhowdes1_predArr", new ArrayList<PredObj>());
    }
    predArr = (ArrayList<PredObj>)context.getAttribute("jhowdes1_predArr");
    
    //add new prediction
    PredObj predo = new PredObj(username,pred,args,votes);
    predArr.add(predo);
    int arrSize = predArr.size();
    
    //xml writing code
    //String xml = "/data/apps-swe432/swe432/WEB-INF/data/jhowdes1_preds.xml";
    //String xml = "/Applications/apache-tomcat-7.0.54/webapps/experiment/WEB-INF/data/HLVoting/jhowdes1_preds.xml";
    String xml = "/var/www/CS/webapps/uprapham/WEB-INF/data/HLVoting/jhowdes1_preds.xml";
    File file = new File(xml);
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
    bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    bw.newLine();
    bw.write("<assertions>");
    bw.newLine();
    for(int i = 0; i < arrSize; i++){
      bw.write("\t<assertion>");
      bw.newLine();
      bw.write("\t\t<user>" + predArr.get(i).getUser() + "</user>");
      bw.newLine();
      bw.write("\t\t<predict>" + predArr.get(i).getPred() + "</predict>");
      bw.newLine();
      bw.write("\t\t<argument>" + predArr.get(i).getArgs() + "</argument>");
      bw.newLine();
      bw.write("\t\t<convinced>" + predArr.get(i).getAgree() + "</convinced>");
      bw.newLine();
      bw.write("\t\t<unsure>" + predArr.get(i).getUnsure() + "</unsure>");
      bw.newLine();
      bw.write("\t\t<disagree>" + predArr.get(i).getDisagree() + "</disagree>");
      bw.newLine();
      bw.write("\t</assertion>");
      bw.newLine();
    }
    bw.write("</assertions>");
    bw.newLine();
    bw.flush();
    bw.close();
        
    //update session request
    session.setAttribute("reqType","viewSubmit");
    
    //String locJSP = "http://apps-swe432.vse.gmu.edu:8080/swe432/jsp/jhowdes1/index.jsp";
    //String locJSP = "http://localhost:8080/experiment/HLVoting/index.jsp";
    String locJSP = "http://cs.gmu.edu:8080/uprapham/HLVoting/index.jsp";
    res.sendRedirect(locJSP);
  }
  
  //function to replicate PHP nl2br
  public static String nl2br(String text) {return text.replaceAll("\n","<br />");}
}