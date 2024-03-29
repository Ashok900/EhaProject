package com.elephant.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




@Entity
@Table(name="image")
public class ImageDomain  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1911283952538434002L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="imageId")
	private long imageId;
	
	@Column(name="imageName")
	private String imageName;
	
	@Column(name="imagePath")
	private String imagePath;
	
	@Column(name="header")
	private String header;
	
	@Column(name="desc1")
	private String desc1;
	
	@Column(name="desc2")
	private String desc2;
	
	@Column(name="desc3")
	private String desc3;
	
	@Column(name="desc4")
	private String desc4;
	
	@Column(name="desc5")
	private String desc5;
	
	@Temporal(TemporalType.DATE)
	@Column(name="createdDate")
	private Date createdDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="modifiedDate")
	private Date modifiedDate;
	
	@ManyToOne
	@JoinColumn(name="bannerId")
	private BannerDomain bannerDomain;
	
	
	public long getImageId() {
		return imageId;
	}

	public ImageDomain setImageId(long imageId) {
		this.imageId = imageId;
		return this;
	}

	public String getImageName() {
		return imageName;
	}

	public ImageDomain setImageName(String imageName) {
		this.imageName = imageName;
		return this;
	}

	public String getImagePath() {
		return imagePath;
	}

	public ImageDomain setImagePath(String imagePath) {
		this.imagePath = imagePath;
		return this;
	}

	public String getHeader() {
		return header;
	}

	public ImageDomain setHeader(String header) {
		this.header = header;
		return this;
	}

	public String getDesc1() {
		return desc1;
	}

	public ImageDomain setDesc1(String desc1) {
		this.desc1 = desc1;
		return this;
	}

	public String getDesc2() {
		return desc2;
	}

	public ImageDomain setDesc2(String desc2) {
		this.desc2 = desc2;
		return this;
	}

	public String getDesc3() {
		return desc3;
	}

	public ImageDomain setDesc3(String desc3) {
		this.desc3 = desc3;
		return this;
	}

	public String getDesc4() {
		return desc4;
	}

	public ImageDomain setDesc4(String desc4) {
		this.desc4 = desc4;
		return this;
	}

	public String getDesc5() {
		return desc5;
	}

	public ImageDomain setDesc5(String desc5) {
		this.desc5 = desc5;
		return this;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public ImageDomain setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public ImageDomain setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}

	public BannerDomain getBanner() {
		return bannerDomain;
	}

	public ImageDomain setBanner(BannerDomain bannerDomain) {
		this.bannerDomain = bannerDomain;
		return this;
	}


	
	
}
