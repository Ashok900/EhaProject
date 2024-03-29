package com.elephant.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elephant.constant.StatusCode;
import com.elephant.dao.BannerRepository;
import com.elephant.dao.ImageDao;
import com.elephant.dao.ImageDaoRepository;
import com.elephant.domain.BannerDomain;
import com.elephant.domain.ImageDomain;
import com.elephant.mapper.entities.ImageMapper;
import com.elephant.model.ImageModel;
import com.elephant.response.Response;
import com.elephant.utils.CommonUtils;

@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	 ImageDao imageDao;
	
	@Autowired
	 ImageMapper imageMapper;
	
	@Autowired
	BannerService bannerService;
	
	@Autowired
	BannerRepository bannerRepository;
	
	@Autowired
	ImageDaoRepository imageDaoRepository ;

	@Override
	public Response postImage(ImageModel imageModel, String bannerArea) throws Exception {
		Response response=CommonUtils.getResponseObject("Post Image");
		
		try {
		ImageDomain imageDomain=new ImageDomain();
		BeanUtils.copyProperties(imageModel, imageDomain);
		List<BannerDomain> bannerDomainList=bannerRepository.findAll();
		for(int i=0;i< bannerDomainList.size();i++) {
			if((bannerArea.equals(bannerDomainList.get(i).getBannerArea()))){
				BannerDomain bannerDomain=bannerRepository.findByBannerArea(bannerArea);
				imageDomain.setBanner(bannerDomain);
				imageDaoRepository.save(imageDomain);
				response.setStatus(StatusCode.SUCCESS.name());
				response.setMessage("Image Post is Successfull");
				//Response response=imageDao.postImage(imageDomain);
				return response;
			}
		}
		response.setStatus(StatusCode.ERROR.name());
		response.setMessage("Banner Area is Not found");
		return response;
		
		}
		catch(Exception ex) {
			response.setStatus(StatusCode.ERROR.name());
			System.out.println("Exception in post image"+ex);
		}
		return  response;
	}

	@Override
	public List<ImageModel> allImage() throws Exception {
		try {
		List<ImageDomain> image= imageDao.allImage();
		return imageMapper.entityList(image);
		}
		catch(Exception e) {
			
		}
		return null;
	}

	@Override
	public ImageModel getImage(long imageId) throws Exception {
		try {
		ImageDomain imageDomain=imageDao.getImage(imageId);
		ImageModel imageModel=new ImageModel();
		BeanUtils.copyProperties(imageDomain, imageModel);
		return imageModel;
		}
		catch(Exception ex) {
			System.out.println("Exception in get image"+ex);
		}
		
		return null;
	}

	@Override
	public Response delete(long imageId) throws Exception {
		return imageDao.delete(imageId);
	}

	@Override
	public Response update(ImageModel imageModel) throws Exception {
		ImageDomain imageDomain= new ImageDomain();
		BeanUtils.copyProperties(imageModel, imageDomain);
		Response response=imageDao.update(imageDomain);
		return response;
	}

	@Override
	public ImageDomain getPath(String imagePath) throws Exception {
		ImageDomain	image=imageDao.getPath(imagePath);
		return image;
	}
	
	@Override
	public List<ImageModel> getImageByBannerArea(String bannerArea){
		try {
			BannerDomain bannerDomain=bannerRepository.findByBannerArea(bannerArea);
			List<ImageDomain> image= bannerDomain.getImageDomain();
			return imageMapper.entityList(image);
			}
			catch(Exception e) {
				
			}
			return null;
		}
	
	
	

}

/*---------------------------------------------------------Original Code--------------------------------------------------------------*/
/*@Override
public Response postImage(ImageModel imageModel,String bannerArea) throws Exception {
	Response response=CommonUtils.getResponseObject("Post Image");
	
	try {
	ImageDomain imageDomain=new ImageDomain();
	BeanUtils.copyProperties(imageModel, imageDomain);
	List<BannerDomain> bannerDomainList=bannerRepository.findAll();
	for(int i=0;i< bannerDomainList.size();i++) {
		if((bannerArea.equals(bannerDomainList.get(i).getBannerArea()))){
			BannerDomain bannerDomain=bannerRepository.findByBannerArea(bannerArea);
			imageDomain.setBanner(bannerDomain);
			imageDaoRepository.save(imageDomain);
			response.setStatus(StatusCode.SUCCESS.name());
			response.setMessage("Image Post is Successfull");
			//Response response=imageDao.postImage(imageDomain);
			return response;
		}
	}
	response.setStatus(StatusCode.ERROR.name());
	response.setMessage("Banner Area is Not found");
	return response;
	
	}
	catch(Exception ex) {
		response.setStatus(StatusCode.ERROR.name());
		System.out.println("Exception in post image"+ex);
	}
	return  response;
}*/
/*--------------------------------------------------------------------------------------------------------------------------------------*/
