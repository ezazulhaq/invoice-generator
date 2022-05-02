package com.haa.invoicegenerator.pojo;

public class Goods {

    private String hsnCode;
    private String particulars;
    private String rate;
    private String kgs;
    private String amount;
    private String id;

    public Goods(String hsnCode, String particulars, String rate, String kgs, String amount, String id) {
        this.hsnCode = hsnCode;
        this.particulars = particulars;
        this.rate = rate;
        this.kgs = kgs;
        this.amount = amount;
        this.id = id;
    }

    public Goods() {
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getKgs() {
        return kgs;
    }

    public void setKgs(String kgs) {
        this.kgs = kgs;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Goods [amount=" + amount + ", hsnCode=" + hsnCode + ", id=" + id + ", kgs=" + kgs + ", particulars="
                + particulars + ", rate=" + rate + "]";
    }

}
