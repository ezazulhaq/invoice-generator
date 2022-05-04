package com.haa.invoicegenerator.dao;

import java.util.List;
import java.util.Optional;

import com.haa.invoicegenerator.entity.GoodDetails;
import com.haa.invoicegenerator.entity.InvoiceDetails;

public interface InvoiceDetailsDAO {

    List<InvoiceDetails> getAllInvoice();

    void saveInvoice(InvoiceDetails invoice);

    void removeInvoice(Integer invoiceId);

    Optional<InvoiceDetails> fetchInvoiceById(Integer invoiceId);

    List<String> getGoodsListByInvoice(Integer invoiceId);

    Optional<GoodDetails> fetchGoodByIdAndInvoice(Integer id, Integer invoiceId);

    GoodDetails addGoodDetails(Integer invoiceId);

    Optional<GoodDetails> fetchGoodById(Integer id);

    GoodDetails saveGoods(GoodDetails good);

    void removeGoodsFromInvoice(Integer goodId);

}
