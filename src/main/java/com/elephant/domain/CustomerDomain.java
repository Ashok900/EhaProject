package com.elephant.domain;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import javax.persistence.Table;
import javax.validation.constraints.Email;

//import com.elephant.domain.roles.Role;
import org.springframework.format.annotation.NumberFormat;

import com.elephant.domain.AddressDomain;


@Entity
@Table(name="customers")
public class CustomerDomain implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4662026020580548585L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customersId")
	
	private long customersId;
	
	@Column(name="customerName")
	private String customerName;

	@Column(name="gender")
	private String gender;
	
	
	
	@Email
	@Column(name="email",unique=true)
	private String email;
	
	@Column(name="password")
	private String password;
	
	@NumberFormat
	@Column(name="mobileNumber")
	private long mobileNumber;
	
//	@Column(name="confirmPassword")
//	private String confirmPassword;
	 
	
	 private Date expiryDate;
	 
	 @ManyToMany(fetch=FetchType.LAZY)
	 @JoinTable(name = "user_roles", 
 	joinColumns = @JoinColumn(name = "customer_id"), 
 	inverseJoinColumns = @JoinColumn(name = "role_id"))
 //private Set<Role> roles = new HashSet<>();
	 
	// @OneToMany(mappedBy="customerDomain",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
		//@JoinTable(name = "USER_ROLES", joinColumns={
				//@JoinColumn(name = "USER_ID", referencedColumnName ="customersId") }, inverseJoinColumns = {
					//@JoinColumn(name = "ROLE_NAME", referencedColumnName = "name") })
		private List<Role> roles;
		
	
	
	
	@OneToMany(mappedBy="customerDomain", cascade=CascadeType.REMOVE, fetch=FetchType.LAZY )
	private List<AddressDomain> addressDomain;
	

	/*//@OneToOne(mappedBy="customer", cascade=CascadeType.REMOVE, fetch=FetchType.LAZY )	
	@OneToOne
	@JoinColumn(name="cartId")
	private CartDomain cartDomain;*/
	
	@OneToMany(mappedBy="customerDomain", cascade=CascadeType.ALL, fetch=FetchType.LAZY )
	private List<CartItemDomain> cartItemDomain;

	

	@OneToMany(mappedBy="customerDomain", cascade=CascadeType.ALL, fetch=FetchType.LAZY )
	private List<OrderDomain> orderDomain;
	
	@OneToMany(mappedBy="customerDomain", cascade=CascadeType.ALL, fetch=FetchType.LAZY )
	private List<InvoiceDomain> invoiceDomain;
	

	public List<CartItemDomain> getCartItemDomain() {
		return cartItemDomain;
	}
	public CustomerDomain setCartItemDomain(List<CartItemDomain> cartItemDomain) {
		this.cartItemDomain = cartItemDomain;
		return this;
	}
	
	public List<AddressDomain> getAddressDomain() {
		return addressDomain;
	}
	public CustomerDomain setAddressDomain(List<AddressDomain> addressDomain) {
		this.addressDomain = addressDomain;
		return this;
	}
	
	public List<InvoiceDomain> getInvoiceDomain() {
		return invoiceDomain;
	}
	public CustomerDomain setInvoiceDomain(List<InvoiceDomain> invoiceDomain) {
		this.invoiceDomain = invoiceDomain;
		return this;
	}
	
	public List<OrderDomain> getOrderDomain() {
		return orderDomain;
	}
	public CustomerDomain setOrderDomain(List<OrderDomain> orderDomain) {
		this.orderDomain = orderDomain;
		return this;
	}
	/*public CartDomain getCartDomain() {
		return cartDomain;
	}
	public void setCartDomain(CartDomain cartDomain) {
		this.cartDomain = cartDomain;
	}*/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	private boolean isActive;
	
//	private boolean isActiveUser;
	
	@Column(name="valitateCode")
	private String valitateCode;
	
//	@Temporal(TemporalType.DATE)
//	@Column(name="dateOfBirth")
//	private Date dateOfBirth;
//	
	
	public boolean isActive() {
		return isActive;
	}
	public CustomerDomain setActive(boolean isActive) {
		this.isActive = isActive;
		return this;
	}
	//	public Date getDateOfBirth() {
//		return dateOfBirth;
//	}
//	public void setDateOfBirth(Date dateOfBirth) {
//		this.dateOfBirth = dateOfBirth;
//	}
	public long getCustomersId() {
		return customersId;
	}
	
	
	public List<Role> getRoles() {
		return roles;
	}
	public CustomerDomain setRoles(List<Role> roles) {
		this.roles = roles;
		return this;
	}
	public CustomerDomain setCustomersId(long customersId) {
		this.customersId = customersId;
		return this;
	}
	public String getCustomerName() {
		return customerName;
	}
	public CustomerDomain setCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}
	public String getGender() {
		return gender;
	}
	public CustomerDomain setGender(String gender) {
		this.gender = gender;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public CustomerDomain setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public CustomerDomain setPassword(String password) {
		this.password = password;
		return this;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public CustomerDomain setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
		return this;
	}
//	public String getConfirmPassword() {
//		return confirmPassword;
//	}
//	public void setConfirmPassword(String confirmPassword) {
//		this.confirmPassword = confirmPassword;
//	}
	
	public Date getExpiryDate() {
		return expiryDate;
	}
	public CustomerDomain setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
		return this;
	}

	
//	public boolean isActiveUser() {
//		return isActiveUser;
//	}
//	public void setActiveUser(boolean isActiveUser) {
//		this.isActiveUser = isActiveUser;
//	}
	public String getValitateCode() {
		return valitateCode;
	}
	public CustomerDomain setValitateCode(String valitateCode) {
		this.valitateCode = valitateCode;
		return this;
	}
	public CustomerDomain setExpiryDate(int minutes){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        this.expiryDate = now.getTime();
        return this;
    }
	  public boolean isExpired() {
	        return new Date().after(this.expiryDate);
	    }
	  
	  public CustomerDomain(String email, String name, String password) {
			this.email = email;
			this.customerName = name;
			this.password = password;
		}
	public CustomerDomain() {
		// TODO Auto-generated constructor stub
	}
	
	

	
	

	
	

}
