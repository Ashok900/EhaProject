package com.elephant.mapper.entities;

import org.springframework.stereotype.Component;

import com.elephant.domain.BannerDomain;
import com.elephant.mapper.AbstractModelMapper;
import com.elephant.model.BannerModel;

@Component
public class BannerMapper extends AbstractModelMapper<BannerModel, BannerDomain>{

	@Override
	public Class<BannerModel> entityType() {
		return BannerModel.class;
	}

	@Override
	public Class<BannerDomain> modelType() {
		return BannerDomain.class;
	}
}
