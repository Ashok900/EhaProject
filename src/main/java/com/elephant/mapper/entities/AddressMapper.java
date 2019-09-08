package com.elephant.mapper.entities;

import org.springframework.stereotype.Component;

import com.elephant.domain.AddressDomain;
import com.elephant.mapper.AbstractModelMapper;
import com.elephant.model.AddressModel;

@Component
public class AddressMapper extends AbstractModelMapper<AddressModel, AddressDomain> {

	
	@Override
	public Class<AddressModel> entityType() {
		return AddressModel.class;
	}

	@Override
	public Class<AddressDomain> modelType() {
		return AddressDomain.class;
	}
}
