package com.elephant.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elephant.constant.StatusCode;
import com.elephant.domain.BannerDomain;
import com.elephant.response.Response;
import com.elephant.utils.CommonUtils;

@Transactional
@Repository
public class BannerDaoImpl  implements BannerDao{

	private static final Logger logger = LoggerFactory.getLogger(BannerDaoImpl.class);

	@Autowired
	BannerRepository bannerRepository;
	
	public Response createBanner(BannerDomain bannerDomain) {
		
		Response response = CommonUtils.getResponseObject("Add Banner");
		try {
			bannerRepository.save(bannerDomain);
			response.setStatus(StatusCode.SUCCESS.name());
		} catch (Exception e) {
			logger.error("Exception in  creating Banner ", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
		}
		return response;

	}
	
	public Response deleteBannerById(long bannerId) {
		Response response = CommonUtils.getResponseObject("Delete Address");	
		try {
			bannerRepository.deleteById(bannerId);;
		response.setStatus(StatusCode.SUCCESS.name());
		}
		catch (Exception e) {
			logger.error("Exception in addUser", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
		}
		return response;
	}
	public Response updateBannerById(BannerDomain bannerDomain) {
		Response response = CommonUtils.getResponseObject("Update banner data");
		try {
		BannerDomain bannerDomain1=getBannerById(bannerDomain.getBannerId());
		bannerDomain1.setBannerName(bannerDomain.getBannerName())
		             .setBannerArea(bannerDomain.getBannerArea())
		             .setCreatedDate(bannerDomain.getCreatedDate())
	                 .setModifiedDate(bannerDomain.getModifiedDate());
		bannerRepository.flush();
		response.setStatus(StatusCode.SUCCESS.name());
		} catch (Exception e) {
			logger.error("Exception in updateBanner", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
		}
		return  response;
		}
	
	public BannerDomain getBannerById(long bannerId) {
		
//		String hql="FROM BannerDomain where bannerId=?1";
//		return (BannerDomain) entityManager.createQuery(hql).setParameter(1, bannerId).getSingleResult();
		BannerDomain bannerDomain=bannerRepository.findById(bannerId);
		return bannerDomain;
	}
	
	@SuppressWarnings("unchecked")
	public List<BannerDomain> getAllBanners(){
//		String hql="FROM BannerDomain";
//		return (List<BannerDomain>)  entityManager.createQuery(hql).getResultList();
		return bannerRepository.findAll();
	}
}
