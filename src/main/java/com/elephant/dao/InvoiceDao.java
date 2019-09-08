package com.elephant.dao;

import java.util.Date;
import java.util.List;

import com.elephant.domain.InvoiceDomain;

public interface InvoiceDao {

	List<InvoiceDomain> getAllInvoices();

	List<InvoiceDomain> getInvoiceByDate(Date invoiceDate);

	List<InvoiceDomain> getInvoiceBetweenDates(Date fromDate, Date toDate);

	

}
