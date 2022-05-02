package com.haa.invoicegenerator.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_invoice", initialValue = 1000, allocationSize = 1)
public class InvoiceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_invoice")
    private Integer invoiceId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private CustomerDetails customer;

    private Date invoiceDate;

    private Double total;

    private Double cgst;

    private Double sgst;

    private Double grandTotal;

    private String amountInWords;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<GoodDetails> goods;

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public CustomerDetails getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDetails customer) {
        this.customer = customer;
    }

    public List<GoodDetails> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodDetails> goods) {
        this.goods = goods;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getCgst() {
        return cgst;
    }

    public void setCgst(Double cgst) {
        this.cgst = cgst;
    }

    public Double getSgst() {
        return sgst;
    }

    public void setSgst(Double sgst) {
        this.sgst = sgst;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getAmountInWords() {
        return amountInWords;
    }

    public void setAmountInWords(String amountInWords) {
        this.amountInWords = amountInWords;
    }

    @Override
    public String toString() {
        return "InvoiceDetails [amountInWords=" + amountInWords + ", cgst=" + cgst + ", customer=" + customer
                + ", goods=" + goods + ", grandTotal=" + grandTotal + ", invoiceDate=" + invoiceDate + ", invoiceId="
                + invoiceId + ", sgst=" + sgst + ", total=" + total + "]";
    }

}
