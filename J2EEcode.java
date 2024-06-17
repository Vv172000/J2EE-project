package com.jee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.attribute.AclEntry;
import java.sql.*;

/**
 * Servlet implementation class Loginpage
 */
public class Loginpage extends HttpServlet{
	
	
	Connection con=null;
	String url="jdbc:mysql://localhost:3306/employee";
	String un="root";
	String pass="2000";
	PreparedStatement psmt=null;
	ResultSet res=null;
	
	@Override
		public void init() throws ServletException {
			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,un,pass);
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			resp.setContentType("text/html");
			PrintWriter writer=resp.getWriter();
			String username=req.getParameter("username");
			String password=req.getParameter("password");
			
			String query="select * from login_details where un = ? and password = ?";
			try {
				psmt=con.prepareStatement(query);
				psmt.setString(1, username);
				psmt.setString(2, password);
				res=psmt.executeQuery();
				
				if(res.next()==true) {
					writer.println("Welcome to the page "+res.getString(1));

				}
				else {
					writer.println("please try again");
				}
				
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
	
	

		
		

}

