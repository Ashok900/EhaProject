package com.elephant.mapper.entities;

import org.springframework.stereotype.Component;

import com.elephant.domain.InvoiceDetailsDomain;
import com.elephant.mapper.AbstractModelMapper;
import com.elephant.model.InvoiceDetailsModel;


@Component
public class InvoiceDetailsMapper  extends AbstractModelMapper<InvoiceDetailsModel,InvoiceDetailsDomain>{

	@Override
	public Class<InvoiceDetailsModel> entityType() {
		return InvoiceDetailsModel.class;
	}

	@Override
	public Class<InvoiceDetailsDomain> modelType() {
		return InvoiceDetailsDomain.class;
	}
	
}
