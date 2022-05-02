package com.haa.invoicegenerator.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

@Entity
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Please enter Product Name")
    private String particulars;

    @NotNull(message = "Please select HSN Code")
    private Integer hsnCode;

    @NotNull(message = "Please enter Rate")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private Double rate;

    @OneToMany(mappedBy = "product", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH })
    private List<GoodDetails> good;

    public ProductDetails() {
    }

    public ProductDetails(Integer id, @NotEmpty(message = "Please enter Product Name") String particulars,
            @NotNull(message = "Please select HSN Code") Integer hsnCode,
            @NotNull(message = "Please enter Rate") Double rate) {
        this.id = id;
        this.particulars = particulars;
        this.hsnCode = hsnCode;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public Integer getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(Integer hsnCode) {
        this.hsnCode = hsnCode;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public List<GoodDetails> getGood() {
        return good;
    }

    public void setGood(List<GoodDetails> good) {
        this.good = good;
    }

    @Override
    public String toString() {
        return "ProductDetails [hsnCode=" + hsnCode + ", id=" + id + ", particulars=" + particulars + ", rate=" + rate
                + "]";
    }

}
