package com.elephant.mapper.entities;

import org.springframework.stereotype.Component;

import com.elephant.domain.ImageDomain;
import com.elephant.mapper.AbstractModelMapper;
import com.elephant.model.ImageModel;


@Component
public class ImageMapper extends AbstractModelMapper<ImageModel,ImageDomain>{
	
	@Override
	public Class<ImageModel> entityType() {
		return ImageModel.class;
	}

	@Override
	public Class<ImageDomain> modelType() {
		
		return ImageDomain.class;
	}

}

