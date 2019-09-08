package com.elephant.domain;

import java.io.Serializable;
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
@Table(name="address")
public class AddressDomain implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4438933691687068650L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressId;
	@Column(name="fullname")
	private String fullname;
	
	@Column(name="addressline1")
	private String addressline1;
	
	@Column(name="addressline2")
	private String addressline2;
	
	@Column(name="addressline3")
	private String addressline3;
	
	@Column(name="pincode")
	private String pincode;

	@Column(name="town")
	private String town;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String country;
	

	@ManyToOne
	@JoinColumn(name="customersId")
	private CustomerDomain customerDomain;
	
	@OneToMany(mappedBy="addressDomain", cascade=CascadeType.ALL, fetch=FetchType.LAZY )
	private List<OrderDomain> orderDomain;
	
	@OneToMany(mappedBy="addressDomain", cascade=CascadeType.ALL, fetch=FetchType.LAZY )
	private List<InvoiceDomain> invoiceDomain;
	
	public List<InvoiceDomain> getInvoiceDomain() {
		return invoiceDomain;
	}

	public AddressDomain setInvoiceDomain(List<InvoiceDomain> invoiceDomain) {
		this.invoiceDomain = invoiceDomain;
		return this;
	}

	public List<OrderDomain> getOrderDomain() {
		return orderDomain;
	}

	public AddressDomain setOrderDomain(List<OrderDomain> orderDomain) {
		this.orderDomain = orderDomain;
		return this;
	}

	public CustomerDomain getCustomerDomain() {
		return customerDomain;
	}

	public AddressDomain setCustomerDomain(CustomerDomain customerDomain) {
		this.customerDomain = customerDomain;
		return this;
	}

	public long getAddressId() {
		return addressId;
	}

	public AddressDomain setAddressId(long addressId) {
		this.addressId = addressId;
		return this;
	}

	public String getFullname() {
		return fullname;
	}

	public AddressDomain setFullname(String fullname) {
		this.fullname = fullname;
		return this;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public AddressDomain setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
		return this;
		
	}

	public String getAddressline2() {
		return addressline2;
	}

	public AddressDomain setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
		return this;
	}

	public String getAddressline3() {
		return addressline3;
	}

	public AddressDomain setAddressline3(String addressline3) {
		this.addressline3 = addressline3;
		return this;
	}

	public String getPincode() {
		return pincode;
	}

	public AddressDomain setPincode(String pincode) {
		this.pincode = pincode;
		return this;
	}

	public String getTown() {
		return town;
	}

	public AddressDomain setTown(String town) {
		this.town = town;
		return this;
	}

	public String getCity() {
		return city;
	}

	public AddressDomain setCity(String city) {
		this.city = city;
		return this;
	}

	public String getState() {
		return state;
	}

	public AddressDomain setState(String state) {
		this.state = state;
		return this;
	}

	public String getCountry() {
		return country;
	}

	public AddressDomain setCountry(String country) {
		this.country = country;
		return this;
	}


		
	
		
	
}
