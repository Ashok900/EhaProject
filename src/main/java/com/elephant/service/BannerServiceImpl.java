package com.elephant.service;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elephant.constant.StatusCode;
import com.elephant.dao.BannerDao;
import com.elephant.dao.BannerRepository;
import com.elephant.domain.BannerDomain;
import com.elephant.mapper.entities.BannerMapper;
import com.elephant.model.BannerModel;
import com.elephant.response.Response;
import com.elephant.utils.CommonUtils;


@Service
public class BannerServiceImpl implements BannerService{

	private static final Logger logger = LoggerFactory.getLogger(BannerServiceImpl.class);
	
	@Autowired
	BannerDao bannerDao;

	@Autowired
	BannerMapper bannerMapper;
	
	@Autowired
	BannerRepository bannerRepository;
	
	//@Autowired
	//CustomerRepository customerRepository;
	
	public Response createBanner(BannerModel bannerModel) {
		//CustomerDomain cust=new CustomerDomain();
		BannerDomain bannerDomain=new BannerDomain();
		BeanUtils.copyProperties(bannerModel, bannerDomain);
		//
		//bannerDomain.getBannerArea()
		Response response=CommonUtils.getResponseObject("Create Banner");
		
		List<BannerDomain> bannerDomainList=bannerRepository.findAll();
		
		
		for(int i=0;i<bannerDomainList.size();i++) {
			
			if(bannerDomain.getBannerArea().equals( bannerDomainList.get(i).getBannerArea()) || bannerDomain.getBannerName().equals(bannerDomainList.get(i).getBannerName())) {
				
				response.setStatus(StatusCode.ERROR.name());
				response.setMessage("BannerArea/BannerName is Same");
				return response;
			}
			
		}
		
		

			response.setStatus(StatusCode.SUCCESS.name());
			response.setMessage("Banner Creation is Successfull");
			bannerRepository.save(bannerDomain);
			return response;
		//bannerDomain.setCustomer(cust.getCustomersId());
		//CustomerDomain customerdomain=customerRepository.findByRollId(customersId);
		
		//bannerDomain.setCustomer(customerdomain);
		//return bannerDao.createBanner(bannerDomain);		
	
	}

	public Response deleteBannerById(long bannerId) {
		
		Response response=CommonUtils.getResponseObject("Delete banner");
		try {
			bannerRepository.deleteById(bannerId);
			response.setStatus(StatusCode.SUCCESS.name());
			return response;
		//return bannerDao.deleteBannerById(bannerId);
		
		}
		catch(Exception ex) {
			System.out.println("Exception in deleting banner"+ex);
		}
		return response;
	}

	public Response updateBannerById(BannerModel bannerModel) {		
		try {
			
			BannerDomain bannerDomain= new BannerDomain();
			BeanUtils.copyProperties(bannerModel, bannerDomain);
			Response response = bannerDao.updateBannerById(bannerDomain);
			return response;
		} catch (Exception ex) {
			logger.info("Exception Service:" + ex.getMessage());
		}
		return null;
	}

	public BannerModel getBannerById(long bannerId) {
		BannerDomain bannerDomain=bannerDao.getBannerById(bannerId);
		BannerModel bannerModel = new BannerModel();
		if (bannerDomain == null)
			return null;
		BeanUtils.copyProperties(bannerDomain, bannerModel);
		return bannerModel;
		
	}

	public List<BannerModel> getAllBanners() {
		try {
			List<BannerDomain> bannerDomain= bannerDao.getAllBanners();
			return bannerMapper.entityList(bannerDomain);
		} catch (Exception ex) {
			logger.info("Exception getUsers:", ex);
		}
		return null;
	}
	
	

}
