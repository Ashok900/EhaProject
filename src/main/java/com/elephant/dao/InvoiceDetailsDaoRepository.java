package com.elephant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elephant.domain.InvoiceDetailsDomain;

public interface InvoiceDetailsDaoRepository extends JpaRepository<InvoiceDetailsDomain, Long> {

}
