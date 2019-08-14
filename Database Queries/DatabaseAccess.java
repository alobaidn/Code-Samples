package nalobaid;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.lang.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import java.sql.*;

import java.io.*;

/**
 * Servlet implementation class Database
 */

public class Database extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Database() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.setContentType ("TEXT/HTML");
		PrintWriter out = res.getWriter ();
		out.println("<html>");
		out.println("<head>");
				out.println("<title>Accessing data in a database</title>");
				 out.println ("<script>");
			     out.println ("function order1()");
			out.println ("{");
			out.println ("document.getElementById(\"order1\").style.display='inline';");

			out.println ("}");
		    out.println ("function order2()");
					out.println ("{");
					out.println ("document.getElementById(\"order2\").style.display='inline';");

					out.println ("}");
			 out.println ("</script>");
out.println("</head>");
out.println("<body>");

out.println("<form action=\"\">");
	out.println("<input type=\"radio\" name=\"choice\" value=\"one\" onclick=\"order1();\">Add a supplier order<br>");
	out.println ("<div id=\"order1\" style=\"display:none;\">");
	out.println ("Supplier ID <input type=\"Text\" name=\"si\">");
	out.println ("Item ID <input type=\"Text\" name=\"ii1\">");
	out.println ("Quantity <input type=\"Text\" name=\"qty1\">");
	out.println ("</div>");
	out.println ("<br />");
	out.println("<input type=\"radio\" name=\"choice\" value=\"two\" onclick=\"order2();\">Add a manufacturer order<br>");
	out.println ("<div id=\"order2\" style=\"display:none;\"> Customer ID <input type=\"Text\" name=\"ci2\">");
	out.println ("Manufacturer ID <input type=\"Text\" name=\"mi\">");
	out.println ("Item ID <input type=\"Text\" name=\"ii2\">");
	out.println ("Quantity <input type=\"Text\" name=\"qty2\">");
	out.println ("</div>");
	out.println ("<br />");
	out.println("<input type=\"radio\" name=\"choice\" value=\"three\" >Display Customers Demands along with Items Shipped<br>");
	out.println("<input type=\"radio\" name=\"choice\" value=\"four\" >Display Supplier Orders along with Items Shipped<br>");
	out.println("<input type=\"radio\" name=\"choice\" value=\"five\" >Display Manufacturer Orders along with Items Shipped<br>");
	out.println("<input type=\"radio\" name=\"choice\" value=\"six\" >Display Customers whose Demands aren't Satisfied<br>");
	out.println("<input type=\"radio\" name=\"choice\" value=\"seven\" >Display Suppliers whose Orders aren't fully Shipped Out<br>");
	out.println("<input type=\"radio\" name=\"choice\" value=\"eight\" >Display Manufacturers whose Orders aren't fully Shipped Out<br>");
	
out.println ("<button type=\"button\" onclick=\"\">Submit</button>");
out.println("</form>");
out.println("</html>");
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			  // Step 1. Load the JDBC driver
				
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); //Or any other driver
				
				
			  
			  // Step 2. Create a Connection object
			  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@apollo.ite.gmu.edu:1521:ite10g", "nalobaid","Nson09876");

			  System.out.println("got connection");

				PrintWriter toClient = res.getWriter ();
				toClient.println ("<HTML>");
				toClient.println ("<HEAD>");
				toClient.println ("<TITLE>Simple Form Handler</TITLE>");
				toClient.println ("</HEAD>");

				toClient.println ("<BODY BGColor=\"#EEEEEE\">");
				toClient.println ("");
			  //  ServletOutputStream  outl = res.getOutputStream ();
				String para;
				Enumeration paraNames = req.getParameterNames ();
				// String fileURL = "/data/swe642fall2013/swe642/resources/nalobaid/output.xml";

			//	RequestDispatcher rd = null;
				 //   URL url = new URL ( fileURL );
				
				toClient.println ("<CENTER><H2>Restaurant Information</H2></CENTER>");
			
				while (paraNames.hasMoreElements ())
				{  // For each parameter name.

		
					   if (req.getParameter("submit") != null) {
				            //RestFile rf = new RestFile();
				           // rf.doGet(req,res);

				    // rd=req.getRequestDispatcher("/servlet/nalobaid.RestFile");
				 
				   para = (String) paraNames.nextElement ();
				   
				   if (req.getParameter("one") != null) {
			  // Step 3. Create a Statement object and call its executeUpdate 
			  // method to insert a record
			  Statement s = con.createStatement();
			  String sql = 
			    "INSERT INTO supplyOrders(supplier, item, qty) VALUES ('"+req.getParameter("si")+"', '"+req.getParameter("ii1")+"', '"+req.getParameter("qty1")+"');";
			  s.executeUpdate(sql);
			  printForm (req, toClient, "saved", "");
				   }
				   else   if (req.getParameter("two") != null) {
						  // Step 3. Create a Statement object and call its executeUpdate 
						  // method to insert a record
						  Statement s = con.createStatement();
						  String sql = 
						    "INSERT INTO manufOrders(manuf, item, qty) VALUES ('"+req.getParameter("mi")+"', '"+req.getParameter("ii2")+"', '"+req.getParameter("qty2")+"');";
						  s.executeUpdate(sql);
						//  printForm (req, toClient, "saved", "");
						  toClient.println("<b> The order has been saved successfully.. </b>");
							   }
				   else if (req.getParameter("three") != null) {
						  // Step 3. Create a Statement object and call its executeUpdate 
						  // method to insert a record
					   
						  Statement s = con.createStatement();
						  String sql = 
						    "select * from shippedVsCustDemand;";
						  ResultSet rs = s.executeQuery(sql);
						  toClient.println ("<TABLE cellSpacing=1 cellPadding=1 width=\"75%\" border=1 bgColor=lavender>");
							toClient.println ("");
							toClient.println ("  <TR BGColor=\"#FFFFFF\">");
							toClient.println ("   <TH Align=\"center\"><STRONG>Customer</STRONG></TD>");
							toClient.println ("   <TH Align=\"center\"><STRONG>Item</STRONG></TD>");
							toClient.println ("   <TH Align=\"center\"><STRONG>Demand Total</STRONG></TD>");
							toClient.println ("   <TH Align=\"center\"><STRONG>Shipped Total</STRONG></TD>");
							toClient.println ("  </TR>");

							 while (rs.next()) {
								 toClient.println ("<TR>");
							      toClient.println ("<TD style=\"WIDTH: 20%\" width=\"20%\">"+ rs.getString(1) + "</TD>");
							 
								    toClient.println("<TD>" + rs.getString(2) + "</TD>"); 
								    toClient.println("<TD>" + rs.getString(3) + "</TD>"); 
								    toClient.println("<TD>" + rs.getString(4) + "</TD></TR>"); 
						  }
							        
							      
								  rs.close();
								  s.close();
								   }
				   else if (req.getParameter("five") != null) {
						  // Step 3. Create a Statement object and call its executeUpdate 
						  // method to insert a record
					   
						  Statement s = con.createStatement();
						  String sql = 
						    "select * from producedVsShipped;";
						  ResultSet rs = s.executeQuery(sql);
						  toClient.println ("<TABLE cellSpacing=1 cellPadding=1 width=\"75%\" border=1 bgColor=lavender>");
							toClient.println ("");
							toClient.println ("  <TR BGColor=\"#FFFFFF\">");
							toClient.println ("   <TH Align=\"center\"><STRONG>Manufacturer</STRONG></TD>");
							toClient.println ("   <TH Align=\"center\"><STRONG>Item</STRONG></TD>");
							toClient.println ("   <TH Align=\"center\"><STRONG>Demand Total</STRONG></TD>");
							toClient.println ("   <TH Align=\"center\"><STRONG>Shipped Total</STRONG></TD>");
							toClient.println ("  </TR>");

							 while (rs.next()) {
								 toClient.println ("<TR>");
							      toClient.println ("<TD style=\"WIDTH: 20%\" width=\"20%\">"+ rs.getString(1) + "</TD>");
							 
								    toClient.println("<TD>" + rs.getString(2) + "</TD>"); 
								    toClient.println("<TD>" + rs.getString(3) + "</TD>"); 
								    toClient.println("<TD>" + rs.getString(4) + "</TD></TR>"); 
						  }
							        
							      
								  rs.close();
								  s.close();
								   }
				   else if (req.getParameter("six") != null) {
						  // Step 3. Create a Statement object and call its executeUpdate 
						  // method to insert a record
					   
						  Statement s = con.createStatement();
						  String sql = 
						    "Select d.customer from Shipped s, Demand d where (d.customer=s.recipient and d.item=s.item and d.DTotal > s.STotal);";
						  ResultSet rs = s.executeQuery(sql);
						  toClient.println ("<TABLE cellSpacing=1 cellPadding=1 width=\"75%\" border=1 bgColor=lavender>");
							toClient.println ("");
							toClient.println ("  <TR BGColor=\"#FFFFFF\">");
							toClient.println ("   <TH Align=\"center\"><STRONG>Customer ID</STRONG></TD>");
						
							toClient.println ("  </TR>");

							 while (rs.next()) {
								 toClient.println ("<TR>");
							      toClient.println ("<TD style=\"WIDTH: 20%\" width=\"20%\">"+ rs.getString(1) + "</TD>");
				
								    toClient.println("</TR>"); 
						  }
							        
							      
								  rs.close();
								  s.close();
								   }
				   else if (req.getParameter("seven") != null) {
						  // Step 3. Create a Statement object and call its executeUpdate 
						  // method to insert a record
					   
						  Statement s = con.createStatement();
						  String sql = 
						    "Select d.supplier from DemandOfSup d, SupShipped S where (d.supplier=s.sender and d.item=s.item and d.Demand_Total > s.STotal);";
						  ResultSet rs = s.executeQuery(sql);
						  toClient.println ("<TABLE cellSpacing=1 cellPadding=1 width=\"75%\" border=1 bgColor=lavender>");
							toClient.println ("");
							toClient.println ("  <TR BGColor=\"#FFFFFF\">");
							toClient.println ("   <TH Align=\"center\"><STRONG>Customer ID</STRONG></TD>");
						
							toClient.println ("  </TR>");

							 while (rs.next()) {
								 toClient.println ("<TR>");
							      toClient.println ("<TD style=\"WIDTH: 20%\" width=\"20%\">"+ rs.getString(1) + "</TD>");
				
								    toClient.println("</TR>"); 
						  }
							        
							      
								  rs.close();
								  s.close();
								   }
				   else if (req.getParameter("eight") != null) {
						  // Step 3. Create a Statement object and call its executeUpdate 
						  // method to insert a record
					   
						  Statement s = con.createStatement();
						  String sql = 
						    "Select distinct d.manuf from DemandOfManuf d, ManShipped S where (d.manuf=s.sender and d.item=s.item and d.Demand_Total > s.STotal);";
						  ResultSet rs = s.executeQuery(sql);
						  toClient.println ("<TABLE cellSpacing=1 cellPadding=1 width=\"75%\" border=1 bgColor=lavender>");
							toClient.println ("");
							toClient.println ("  <TR BGColor=\"#FFFFFF\">");
							toClient.println ("   <TH Align=\"center\"><STRONG>Customer ID</STRONG></TD>");
						
							toClient.println ("  </TR>");

							 while (rs.next()) {
								 toClient.println ("<TR>");
							      toClient.println ("<TD style=\"WIDTH: 20%\" width=\"20%\">"+ rs.getString(1) + "</TD>");
				
								    toClient.println("</TR>"); 
						  }
							        
							      
								  rs.close();
								  s.close();
								   }   
				   
			}
				}
				con.close();
				toClient.close();
		}
			catch (ClassNotFoundException e1) {
			  // JDBC driver class not found, print error message to the console
			  System.out.println(e1.toString());
			}
			catch (SQLException e2) {
			  // Exception when executing java.sql related commands, print error message to the console
			  System.out.println(e2.toString());
			}
			catch (Exception e3) {
			  // other unexpected exception, print error message to the console
			  System.out.println(e3.toString());
			}
			
	}

}
