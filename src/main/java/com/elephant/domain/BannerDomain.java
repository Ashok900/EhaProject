package com.elephant.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="banner")
public class BannerDomain  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1722018122920526786L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="bannerId")
	private long bannerId;
	
	@Column(name="bannerName")
	private String bannerName;
	
	@Column(name="bannerArea")
	private String bannerArea;
	
	@Column(name="createdDate")
	private Date createdDate;
	
	@Column(name="ModifiedDate")
	private Date ModifiedDate;
	
	@OneToMany(mappedBy="bannerDomain", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ImageDomain> imageDomain;
	
/*	@ManyToOne
	@JoinColumn(name="customersId")
	private CustomerDomain customer;
	
	
	
	public CustomerDomain getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDomain customer) {
		this.customer = customer;
	}*/

	

	public List<ImageDomain> getImageDomain() {
		return imageDomain;
	}

	public BannerDomain setImageDomain(List<ImageDomain> imageDomain) {
		this.imageDomain = imageDomain;
		return this;
	}

	
	
	public long getBannerId() {
		return bannerId;
	}

	public BannerDomain setBannerId(long bannerId) {
		this.bannerId = bannerId;
		return this;
	}

	public String getBannerName() {
		return bannerName;
	}

	public BannerDomain setBannerName(String bannerName) {
		this.bannerName = bannerName;
		return this;
	}

	public String getBannerArea() {
		return bannerArea;
	}

	public BannerDomain setBannerArea(String bannerArea) {
		this.bannerArea = bannerArea;
		return this;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public BannerDomain setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	public Date getModifiedDate() {
		return ModifiedDate;
	}

	public BannerDomain setModifiedDate(Date modifiedDate) {
		ModifiedDate = modifiedDate;
		return this;
	}

	
		
	
}
