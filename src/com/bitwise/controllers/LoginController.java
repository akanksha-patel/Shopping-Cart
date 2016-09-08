package com.bitwise.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getRequestDispatcher("login.jsp").include(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username.equals("varun") && password.equals("pikachu")) {
			session.setAttribute("username", username);
			session.setAttribute("sessID", session.getId());
			session.setMaxInactiveInterval(1000);
			Cookie cookie = new Cookie("sessID", session.getId());
			response.addCookie(cookie);
			response.sendRedirect("ProductController");
		} else {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.write("Invalid Credentials!");
			request.getRequestDispatcher("login.jsp").include(request, response);
			
		}
	}
		

}
