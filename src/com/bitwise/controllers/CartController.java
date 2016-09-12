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
		String [] tokens = prod.split(",");
		
		Product product = new Product (tokens[0], Double.parseDouble(tokens[1]));
		Double price = product.getProductPrice();
		HttpSession session = request.getSession(false);
		
		
		if (submit == null) {
			return;
		}
		
		if (submit.equals("ADD TO CART")) {
			if (isCartNull(session)) {
				initializeCart(prod, price, session);
				response.sendRedirect("ViewCartController");
				return;
			} else {
				addItemToCart(prod,  price, session);
				response.sendRedirect("ViewCartController");
				return;
			}
		}
		
		if (submit.equals("REMOVE FROM CART")) {
			if (isCartNotEmpty(session)) {
				removeItemFromCart(prod,  price,session);
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

	private void addItemToCart(String productName,Double productPrice,HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		cart.add(new Product(productName,productPrice));
		
	}
	
	private void removeItemFromCart(String productName,Double productPrice,HttpSession session){
		Product product = new Product(productName,productPrice);
		Cart cart = (Cart) session.getAttribute("cart");
		cart.remove(product);
	}

	private void initializeCart(String productName,Double productPrice,HttpSession session){
		Cart cart = new Cart ();
		session.setAttribute("cart", cart);
		cart.add(new Product(productName,productPrice));
	
	}


	
}
