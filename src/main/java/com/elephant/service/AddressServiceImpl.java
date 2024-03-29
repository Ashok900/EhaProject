package com.elephant.service;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import com.elephant.dao.AddressDao;
import com.elephant.dao.CustomerRepository;
import com.elephant.domain.AddressDomain;
import com.elephant.domain.CustomerDomain;
import com.elephant.mapper.entities.AddressMapper;
import com.elephant.model.AddressModel;
import com.elephant.response.Response;


@EnableJpaRepositories
@Service
public class AddressServiceImpl implements AddressService {
	
	//private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);


	@Autowired
	AddressDao addressDao;
	
	@Autowired
	AddressMapper addressMapper;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Response addAddress(AddressModel addressModel, String email) throws Exception  {
		
		AddressDomain addressDomain=new AddressDomain();
		BeanUtils.copyProperties(addressModel, addressDomain);
		CustomerDomain customerDomain=customerRepository.findByEmail(email);
		addressDomain.setCustomerDomain(customerDomain);
		return addressDao.addAddress(addressDomain);
		
	}
	@Override
	public Response deleteAddressById(long addressId) throws Exception {		
		return addressDao.deleteAddressById(addressId);
			
	}

	@Override
	public Response updateAddressById(AddressModel addressModel) throws Exception {
		try {
			AddressDomain addressDomain = new AddressDomain();
			BeanUtils.copyProperties(addressModel, addressDomain);
		
			Response response = addressDao.updateAddressById(addressDomain);
			return response;
		} catch (Exception ex) {
			System.out.println(ex);
			//logger.info("Exception Service:" + ex.getMessage());
		}
		return null;
	
		
		
	}

	@Override
	public List<AddressModel> getAllAddress() {
		try {
			List<AddressDomain> addressDomain= addressDao.getAllAddress();
			return addressMapper.entityList(addressDomain);
		} catch (Exception ex) {
			System.out.println(ex);
			//logger.info("Exception getUsers:", ex);
		}
		return null;
	}

	@Override
	public AddressModel getAddressById(long addressId) throws Exception {
		AddressDomain addressDomain=addressDao.getAddressById(addressId);
		AddressModel addressModel = new AddressModel();
		if (addressDomain == null)
			return null;
		BeanUtils.copyProperties(addressDomain, addressModel);
		return addressModel;
	}
	@Override
	public List<AddressModel> getAddressByCustomer(String email) {
		List<AddressDomain> customerDomain=customerRepository.findByEmail(email).getAddressDomain();
		//List<AddressDomain> addressDomain=customerDomain.getAddressDomain();
		return addressMapper.entityList(customerDomain);
	}
		
	
	
}
