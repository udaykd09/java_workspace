
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
		request.getRequestDispatcher("link.html").include(request, response);
		
		try{
		String Username=request.getParameter("name");
		String password=request.getParameter("password");

		HttpSession session = request.getSession();
		out.print("Welcome, " + Username);
		session.setAttribute("name", Username);
		session.setAttribute("", Username);
		
		out.print("Access denied");
		out.print("Account has been locked");
		
		out.print("Sorry, username or password error!");
		
		request.getRequestDispatcher("index.html").include(request, response);
		
		out.close();

		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

}
