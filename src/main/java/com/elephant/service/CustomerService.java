package com.elephant.service;

import java.security.Principal;
import java.util.List;

import org.springframework.ui.Model;

import com.elephant.domain.CustomerDomain;
import com.elephant.model.CustomerModel;
import com.elephant.response.Response;

public interface CustomerService {
	
	public Response addCustomer(CustomerModel customerModel, Model model1) throws Exception;

	public Response updateCustomer(CustomerModel customerModel, Principal pr) throws Exception;

	public Response deleteCustomer(long customersId) throws Exception;
	
	public CustomerModel getCustomer(long customersId) throws Exception;
	

	public List<CustomerModel> getCustomers() throws Exception;

	public List<CustomerModel> getcustomerByrollId(long rollId);

	//public CustomerModel getConfirm(String valitate, String email);

	//public String updateCust(CustomerModel customerModel);

	public CustomerModel authenticate(CustomerModel customerModel);

	//public String updateCusto(CustomerModel customerModel);

	public void getConfirm(String valitate, String email);

	public String resetPassword(CustomerModel customerModel);

	public void resetPass(String email, String pass);

	public void createAdmin(CustomerDomain newAdmin);





	//public void getConfirm(String valitate, String email);

}
  