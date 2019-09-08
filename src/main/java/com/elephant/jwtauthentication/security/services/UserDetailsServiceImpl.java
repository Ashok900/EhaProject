package com.elephant.jwtauthentication.security.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elephant.dao.CustomerRepository;
import com.elephant.domain.CustomerDomain;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    CustomerRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
    	if(userRepository.findByEmail(email).isActive()==true) {
        CustomerDomain user = userRepository.findByEmail(email);
    	        	
        return UserPrinciple.build(user);
    }
    	else
    		return UserPrinciple.build(null);
    }
}