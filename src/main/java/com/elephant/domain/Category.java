package com.elephant.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Indexed;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
//@Table(name="category")@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@Indexed(interceptor=IndexWhenActiveIntercep) @Analyzer(impl = org.apache.lucene.analysis.standard.StandardAnalyzer.class)
//@AnalyzerDef(
//	name="appAnalyzer",
//	charFilters={ @CharFilterDef(factory=HTMLStripCharFilterFactory.class) },
//	tokenizer=@TokenizerDef(factory=StandardTokenizerFactory.class),
//	filters={ 
//		@TokenFilterDef(factory=StandardFilterFactory.class),
//		 @TokenFilterDef(factory = ASCIIFoldingFilterFactory.class),
//		@TokenFilterDef(factory=StopFilterFactory.class),
//		 @TokenFilterDef(factory = EdgeNGramFilterFactory.class, params = { @Parameter(name = "maxGramSize", value = "15") }),
//		
//		 @TokenFilterDef(factory=SnowballPorterFilterFactory.class, params = {
//			@Parameter(name="language", value="English")
//			
//		})
//	}
//)

public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String categoryId;
	private String categoryName;
	
	@Column(name="description",length = 5000)
	private String description;
	private String createdDate;
	private String modifiedDate;
	private boolean isActive;
	 //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date startingDateAndTime;
	private Date endingDateAndTime;
	
	public Date getStartingDateAndTime() {
		return startingDateAndTime;
	}
	public Date getEndingDateAndTime() {
		return endingDateAndTime;
	}
	public Category setStartingDateAndTime(Date dateStart) {
		this.startingDateAndTime = dateStart;
		return this;
	}
	public Category setEndingDateAndTime(Date dateEnd) {
		this.endingDateAndTime = dateEnd;
		return this;
	}
	
	
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval=true)
	/*private List<UploadProductDomain> product = new ArrayList<>();
	
	public List<UploadProductDomain> getProduct() {
		return product;
	}
	public void setProduct(List<UploadProductDomain> product) {
		this.product = product;
	}*/
	private Set<ProductDomain> product = new HashSet<ProductDomain>();
	
	public Set<ProductDomain> getProduct() {
		return product;
	}
	public Category setProduct(Set<ProductDomain> product) {
		this.product = product;
		return this;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public String getDescription() {
		return description;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public boolean isActive() {
		return isActive;
	}
	public Category setCategoryId(String categoryId) {
		this.categoryId = categoryId;
		return this;
	}
	public Category setCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}
	public Category setDescription(String description) {
		this.description = description;
		return this;
	}
	public Category setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
		return this;
	}
	public Category setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}
	public Set<ProductDomain> setActive(boolean isActive) {
		this.isActive = isActive;
		return product;
	}
	
	public Category(){}
	
}
