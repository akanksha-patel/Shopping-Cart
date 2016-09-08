package com.bitwise.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitwise.models.Cart;
import com.bitwise.models.Product;

@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String submit = request.getParameter("submit");
		String prod = request.getParameter("prod");
		System.out.println(prod);
		HttpSession session = request.getSession(false);
		
		
		if (submit == null) {
			return;
		}
		
		if (submit.equals("ADD TO CART")) {
			if (isCartNull(session)) {
				initializeCart(prod, session);
				response.sendRedirect("ViewCartController");
				return;
			} else {
				addItemToCart(prod, session);
				response.sendRedirect("ViewCartController");
				return;
			}
		}
		
		if (submit.equals("REMOVE FROM CART")) {
			if (isCartNotEmpty(session)) {
				removeItemFromCart(prod, session);
				response.sendRedirect("ViewCartController");
				return;
			}
		}
		
		if (submit.equals("DISPLAY CART")) {
			if (isCartNotEmpty(session)) {
				response.sendRedirect("ViewCartController");
				return;
			}
		}
		
	}
	
	private boolean isCartNotEmpty(HttpSession session) {
		return session.getAttribute("cart") != null;
	}

	private boolean isCartNull(HttpSession session) {
		return session.getAttribute("cart") == null;
	}

	private void addItemToCart(String productName,HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		cart.add(new Product(productName));
		
	}
	
	private void removeItemFromCart(String productName,HttpSession session){
		Product product = new Product(productName);
		Cart cart = (Cart) session.getAttribute("cart");
		cart.remove(product);
	}

	private void initializeCart(String productName,HttpSession session){
		Cart cart = new Cart ();
		session.setAttribute("cart", cart);
		cart.add(new Product(productName));
	
	}


	
}
