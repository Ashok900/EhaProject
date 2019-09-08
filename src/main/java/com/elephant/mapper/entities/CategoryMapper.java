package com.elephant.mapper.entities;

import org.springframework.stereotype.Component;

import com.elephant.domain.Category;
import com.elephant.mapper.AbstractModelMapper;
import com.elephant.model.CategoryModel;

	@Component
	public class CategoryMapper extends AbstractModelMapper<CategoryModel, Category> {

		@Override
		public Class<Category> modelType() {
			return Category.class;
		}

		

		@Override
		public Class<CategoryModel> entityType() {
			return CategoryModel.class;
		}
	}
	
	