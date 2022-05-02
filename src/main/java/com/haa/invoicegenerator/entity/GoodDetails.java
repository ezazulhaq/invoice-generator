package com.haa.invoicegenerator.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GoodDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private InvoiceDetails invoice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private ProductDetails product;

    private Integer kgs;

    private Double amount;

    public GoodDetails() {
    }

    public GoodDetails(Integer id, InvoiceDetails invoice, ProductDetails product, Integer kgs, Double amount) {
        this.id = id;
        this.invoice = invoice;
        this.product = product;
        this.kgs = kgs;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InvoiceDetails getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceDetails invoice) {
        this.invoice = invoice;
    }

    public ProductDetails getProduct() {
        return product;
    }

    public void setProduct(ProductDetails product) {
        this.product = product;
    }

    public Integer getKgs() {
        return kgs;
    }

    public void setKgs(Integer kgs) {
        this.kgs = kgs;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "GoodDetails [amount=" + amount + ", id=" + id + ", invoice=" + invoice + ", kgs=" + kgs + ", product="
                + product + "]";
    }

}
