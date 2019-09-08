package com.elephant.mapper.entities;

import org.springframework.stereotype.Component;

import com.elephant.domain.OrderDomain;
import com.elephant.mapper.AbstractModelMapper;
import com.elephant.model.OrderModel;

@Component
public class OrderMapper extends AbstractModelMapper<OrderModel, OrderDomain> {

	@Override
	public Class<OrderModel> entityType() {
		return OrderModel.class;
	}

	@Override
	public Class<OrderDomain> modelType() {
		return OrderDomain.class;
	}
	
}
