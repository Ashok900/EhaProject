package com.elephant.service;

import java.util.List;

import com.elephant.domain.ImageDomain;
import com.elephant.model.ImageModel;
import com.elephant.response.Response;


public interface ImageService {

	public Response postImage(ImageModel imageModel,String bannerArea)throws Exception;

	public List<ImageModel> allImage()throws Exception;

	public ImageModel getImage(long imageId)throws Exception;

	public Response delete(long imageId)throws Exception;

	public Response update(ImageModel imageModel)throws Exception;

	public ImageDomain getPath(String imagePath)throws Exception;

	List<ImageModel> getImageByBannerArea(String bannerArea);

}

