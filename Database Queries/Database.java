package nalobaid;
import java.sql.*;

import java.io.*;

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
						out.println("</head>");
								out.println("<body>");
		

		try {
		  // Step 1. Load the JDBC driver
		  Class.forName("org.gjt.mm.mysql.Driver");
		  
		  // Step 2. Create a Connection object
		  Connection con = DriverManager.getConnection(
		    "jdbc:mysql///xeon/CompanySecret", 
		    "budi", "secret");

		  System.out.println("got connection");

		  
		  // Step 3. Create a Statement object and call its executeUpdate 
		  // method to insert a record
		  Statement s = con.createStatement();
		  String sql = 
		    "INSERT INTO Users VALUES ('Michael', 'Franks', '12/12/2003', 'm')";
		  s.executeUpdate(sql);

		  // Step 4. Use the same Statement object to obtain a ResultSet object
		  sql = "SELECT FirstName, LastName FROM Users";
		  ResultSet rs = s.executeQuery(sql);
		  while (rs.next()) {
		    out.println(rs.getString(1) + " " + rs.getString(2) + "<br>");
		  }
		  rs.close();
		  s.close();
		  con.close();
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
		%>
		</body>
		</html>
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
