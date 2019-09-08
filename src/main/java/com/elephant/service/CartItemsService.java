package com.elephant.service;

import java.security.Principal;
import java.util.List;

import com.elephant.model.CartItemModel;
import com.elephant.response.Response;

public interface CartItemsService {

	public Response saveItem(List<CartItemModel> cartItemModel, Principal currentUser);

	public Response editItem(int quantity, long cartItemId);

	public Response removeItem(long cartItemId);

	public List<CartItemModel> getAllCartItem();

	public List<CartItemModel> getCartItemsByCustomer(String email);

	//CartDomain getShoppingCartById(long CartId);

}
