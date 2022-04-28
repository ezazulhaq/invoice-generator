package com.haa.invoicegenerator.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

@Entity
public class CustomerDetails {

    @Id
    @NotEmpty(message = "Please enter GST No")
    private String gstNo;

    @NotEmpty(message = "Please enter Customer Name")
    private String customerName;

    @NotEmpty(message = "Please enter Address")
    private String address;

    private String state;

    @NotNull(message = "Please enter Phone Number")
    @NumberFormat(pattern = "/^[0-9]+$/")
    private Long phoneNumber;

    @NotNull(message = "Please enter Pin Code")
    @NumberFormat(pattern = "/^[0-9]+$/")
    private Long pinCode;

    @OneToMany(mappedBy = "customer", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH })
    private List<InvoiceDetails> invoice;

    public CustomerDetails() {
    }

    public CustomerDetails(String gstNo, String customerName, String address, String state, Long phoneNumber,
            Long pinCode) {
        this.gstNo = gstNo;
        this.customerName = customerName;
        this.address = address;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.pinCode = pinCode;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getPinCode() {
        return pinCode;
    }

    public void setPinCode(Long pinCode) {
        this.pinCode = pinCode;
    }

    public List<InvoiceDetails> getInvoice() {
        return invoice;
    }

    public void setInvoice(List<InvoiceDetails> invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "CustomerDetails [address=" + address + ", customerName=" + customerName + ", gstNo=" + gstNo
                + ", invoice=" + invoice + ", phoneNumber=" + phoneNumber + ", pinCode=" + pinCode + ", state=" + state
                + "]";
    }

}
