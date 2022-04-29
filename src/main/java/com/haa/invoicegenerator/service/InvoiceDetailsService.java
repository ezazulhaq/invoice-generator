package com.haa.invoicegenerator.service;

import java.util.List;
import java.util.Optional;

import com.haa.invoicegenerator.entity.GoodDetails;
import com.haa.invoicegenerator.entity.InvoiceDetails;

public interface InvoiceDetailsService {

    List<InvoiceDetails> getAllInvoice();

    void saveInvoice(InvoiceDetails invoice);

    void removeInvoice(Integer invoiceId);

    Optional<InvoiceDetails> fetchInvoiceById(Integer invoiceId);

    List<GoodDetails> getGoodsListByInvoice(Integer invoiceId);

}
