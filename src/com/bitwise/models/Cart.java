package com.bitwise.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	private List<Product> cartItems = new ArrayList<Product>();
	
	public List<Product> getCartItems() {
		return cartItems;
	}

	public void add(Product product){
		cartItems.add(product);
	}
	
	public void remove(Product product){
		if(cartItems.contains(product)){
			cartItems.remove(product);
		}
		
	}
	
	public void display(){
		for(Product product : cartItems){
			System.out.println(product);
		}
	}

}
