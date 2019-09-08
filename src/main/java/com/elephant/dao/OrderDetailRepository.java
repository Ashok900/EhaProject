package com.elephant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elephant.domain.OrderDetailDomain;

public interface OrderDetailRepository extends JpaRepository<OrderDetailDomain, Long> {

	//public OrderDfindAllByOrderId(long orderId);
}
