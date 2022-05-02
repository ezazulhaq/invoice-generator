package com.haa.invoicegenerator.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.haa.invoicegenerator.entity.GoodDetails;
import com.haa.invoicegenerator.entity.InvoiceDetails;
import com.haa.invoicegenerator.repo.GoodDetailsRepository;
import com.haa.invoicegenerator.repo.InvoiceDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InvoiceDetailsDAOImpl implements InvoiceDetailsDAO {

    @Autowired
    private InvoiceDetailsRepository invoiceRepo;

    @Autowired
    private GoodDetailsRepository goodRepo;

    @Override
    public List<InvoiceDetails> getAllInvoice() {
        return invoiceRepo.findAll();
    }

    @Override
    @Transactional
    public void saveInvoice(InvoiceDetails invoice) {
        invoiceRepo.save(invoice);
    }

    @Override
    @Transactional
    public void removeInvoice(Integer invoiceId) {
        invoiceRepo.deleteById(invoiceId);
    }

    @Override
    public Optional<InvoiceDetails> fetchInvoiceById(Integer invoiceId) {
        return invoiceRepo.findById(invoiceId);
    }

    @Override
    public List<String> getGoodsListByInvoice(Integer invoiceId) {
        return goodRepo.findByGoodDetailsInvoiceId(invoiceId);
    }

    @Override
    public Optional<GoodDetails> fetchGoodByIdAndInvoice(Integer id, Integer invoiceId) {
        return goodRepo.findByGoodDetailsByIdAndInvoiceId(id, invoiceId);
    }

    @Override
    @Transactional
    public GoodDetails addGoodDetails(Integer invoiceId) {
        GoodDetails good = new GoodDetails();
        InvoiceDetails invoiceDetails = invoiceRepo.getById(invoiceId);
        good.setInvoice(invoiceDetails);
        return goodRepo.save(good);
    }

    @Override
    public Optional<GoodDetails> fetchGoodById(Integer id) {
        return goodRepo.findById(id);
    }

    @Override
    @Transactional
    public GoodDetails saveGoods(GoodDetails good) {
        return goodRepo.save(good);
    }

}
