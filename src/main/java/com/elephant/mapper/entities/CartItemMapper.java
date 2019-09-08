package com.elephant.mapper.entities;


import org.springframework.stereotype.Component;

import com.elephant.domain.CartItemDomain;
import com.elephant.mapper.AbstractModelMapper;
import com.elephant.model.CartItemModel;

@Component
public class CartItemMapper extends AbstractModelMapper<CartItemModel, CartItemDomain> {

	@Override
	public Class<CartItemModel> entityType() {
		return CartItemModel.class;
	}

	@Override
	public Class<CartItemDomain> modelType() {
		return CartItemDomain.class;
	}
}
