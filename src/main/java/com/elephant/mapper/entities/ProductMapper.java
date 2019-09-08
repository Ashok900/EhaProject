package com.elephant.mapper.entities;

import java.util.List;

import org.springframework.stereotype.Component;

import com.elephant.domain.ProductDomain;
import com.elephant.mapper.AbstractModelMapper;
import com.elephant.model.ProductModel;

@Component
public class ProductMapper extends AbstractModelMapper<ProductModel, ProductDomain> {

	

	@Override
	public Class<ProductDomain> modelType() {
		return ProductDomain.class;
	}

	

	@Override
	public Class<ProductModel> entityType() {
		return ProductModel.class;
	}

}
