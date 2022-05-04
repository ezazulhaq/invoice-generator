package com.haa.invoicegenerator.service;

import java.util.List;
import java.util.Optional;

import com.haa.invoicegenerator.dao.InvoiceDetailsDAO;
import com.haa.invoicegenerator.entity.GoodDetails;
import com.haa.invoicegenerator.entity.InvoiceDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceDetailsServiceImpl implements InvoiceDetailsService {

    @Autowired
    private InvoiceDetailsDAO invoiceDAO;

    @Override
    public List<InvoiceDetails> getAllInvoice() {
        return invoiceDAO.getAllInvoice();
    }

    @Override
    public void saveInvoice(InvoiceDetails invoice) {
        invoiceDAO.saveInvoice(invoice);
    }

    @Override
    public void removeInvoice(Integer invoiceId) {
        invoiceDAO.removeInvoice(invoiceId);
    }

    @Override
    public Optional<InvoiceDetails> fetchInvoiceById(Integer invoiceId) {
        return invoiceDAO.fetchInvoiceById(invoiceId);
    }

    @Override
    public List<String> getGoodsListByInvoice(Integer invoiceId) {
        return invoiceDAO.getGoodsListByInvoice(invoiceId);
    }

    @Override
    public Optional<GoodDetails> fetchGoodByIdAndInvoice(Integer id, Integer invoiceId) {
        return invoiceDAO.fetchGoodByIdAndInvoice(id, invoiceId);
    }

    @Override
    public GoodDetails addGoodDetails(Integer invoiceId) {
        return invoiceDAO.addGoodDetails(invoiceId);
    }

    @Override
    public Optional<GoodDetails> fetchGoodById(Integer id) {
        return invoiceDAO.fetchGoodById(id);
    }

    @Override
    public GoodDetails saveGoods(GoodDetails good) {
        return invoiceDAO.saveGoods(good);
    }

    @Override
    public void removeGoodsFromInvoice(Integer goodId) {
        invoiceDAO.removeGoodsFromInvoice(goodId);
    }

}
