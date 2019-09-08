
package com.elephant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elephant.domain.CustomerDomain;

public interface CustomerRepository extends JpaRepository<CustomerDomain, Long> {

	
	CustomerDomain findByEmail(String email);
    
}
