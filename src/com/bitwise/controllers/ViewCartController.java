package com.bitwise.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitwise.models.Cart;
import com.bitwise.models.Product;
import com.bitwise.models.Products;


@WebServlet("/ViewCartController")
public class ViewCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewCartController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Cart cart = (Cart)request.getSession(false).getAttribute("cart");
		List<Product> cartItems = cart.getCartItems();
	
		response.setContentType("text/html");
		double sum = 0d;
		
		out.println("<form action='ProductController'>");
		out.println("<center>"
				+ "<table>"
				+ "<tr><th>Cart Items</th></tr>");
		for (Product item: cartItems) {
			out.println("<tr>"
					+ "<td>"+item.getProductName() + 
							 "</tr>");
			sum=sum+item.getProductPrice();
			
		}
		out.println("<tr><td>Total Price: <span class='red'>" + sum + " INR</span></td></tr>" );
		out.println("<tr><td><input type='submit' value='ADD MORE ITEMS'></td></tr>");
		out.println("</table>"
				+ "</center>");
		
		out.println("</form>");
		
		
		out.flush();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
