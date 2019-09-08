package com.elephant.mapper.entities;


	
	import org.springframework.stereotype.Component;

import com.elephant.domain.CustomerDomain;
import com.elephant.mapper.AbstractModelMapper;
import com.elephant.model.CustomerModel;


	@Component
	public class CustomerMapper extends  AbstractModelMapper<CustomerModel, CustomerDomain>   {
		
		

		@Override
		public Class<CustomerModel> entityType() {
			// TODO Auto-generated method stub
			return CustomerModel.class;
		}

		@Override
		public Class<CustomerDomain> modelType() {
			// TODO Auto-generated method stub
			return CustomerDomain.class;
		}

}
