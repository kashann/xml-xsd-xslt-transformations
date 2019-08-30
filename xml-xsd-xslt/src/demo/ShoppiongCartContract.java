package demo;

import javax.activation.DataHandler;

public interface ShoppiongCartContract {

	void addToCart(String id, String productId);
	
	void updataCart(String id, DataHandler handler);
	
	void displayCart(String id);
}
