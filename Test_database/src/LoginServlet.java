
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		// A menu with Login/Logout links.
		request.getRequestDispatcher("link.html").include(request, response);
		
		try{
		
		// Get login details from Client via HTTP protocol
		String Username=request.getParameter("name");
		String password=request.getParameter("password");
		
		// Start a session for that user
		HttpSession session = request.getSession();

		// Compare the user details with database entry  
		String data = Login_DAO.isUserValid(Username, password);
		
		// Check if login details valid or if session is valid
		if(data.equals("Valid") || Username.equals(session.getAttribute("name"))){
			
		// Success login page
		out.print("Welcome, " + Username);
		session.setAttribute("name", Username);
		session.setAttribute("", Username);
		request.getRequestDispatcher("upload.html").include(request, response);
		}
		
		// If incorrect 3rd attempt, Lock the account. 
		else if(data.equals("Deny")){
		out.println("Access denied");
		out.println("Account has been locked");
		}
		
		// Allow user to try login again until locked
		else if(data.equals("Warn")){
		out.print("Sorry, username or password error!");
		request.getRequestDispatcher("index.html").include(request, response);
		}
		
		// Incorrect User.
		else {
			out.print("Sorry, username or password error!");
		}
		out.close();

		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

}
