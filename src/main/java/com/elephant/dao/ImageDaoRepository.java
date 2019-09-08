package com.elephant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elephant.domain.ImageDomain;

public interface ImageDaoRepository extends JpaRepository<ImageDomain, Long> {

	public ImageDomain findByImageId(long imageId);
}
