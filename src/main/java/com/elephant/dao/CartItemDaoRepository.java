package com.elephant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elephant.domain.CartItemDomain;

public interface CartItemDaoRepository extends JpaRepository<CartItemDomain, Long>{

	//public List<CartItemDomain> findCartItemsByCustomer(CustomerDomain customerDomain);

}
