package com.main;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
//import javax.serv;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.AddData;

public class add extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Ssuper.service(arg0, arg1);
		String name = arg0.getParameter("name");
		
		int i  = Integer.parseInt(arg0.getParameter("num1"));
		int j  = Integer.parseInt(arg0.getParameter("num2"));
		int k = i+j;
		arg1.getWriter().println("<html><body><h2>Result is : "+k+"</br></br>");
		
		String message = null;
		AddData ad = new AddData();

		try {
			ad.saveDetails(name);// gui swing call same way
			message = "Name is added in database is : " + name;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = e.toString();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = e.toString();
		}

		arg1.getWriter().println(message+"</h2></body></html>");// responce back to browser
	}

}
