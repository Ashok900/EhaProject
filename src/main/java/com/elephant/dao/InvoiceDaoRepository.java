package com.elephant.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elephant.domain.InvoiceDomain;

public interface InvoiceDaoRepository  extends JpaRepository<InvoiceDomain, Long>{

	public List<InvoiceDomain> findAllByInvoiceDate(Date invoiceDate);
	
	public InvoiceDomain findByInvoiceId(long invoiceId);
	
	public List<InvoiceDomain> findAllByInvoiceDateBetween(Date fromDate, Date toDate);

}
