package com.elephant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elephant.domain.BannerDomain;

public interface BannerRepository extends JpaRepository<BannerDomain, Long>{

	BannerDomain findByBannerArea(String bannerArea);
	BannerDomain findById(long bannerId);
}
