package com.haa.invoicegenerator.dao;

import java.util.List;
import java.util.Optional;

import com.haa.invoicegenerator.entity.CustomerDetails;

public interface CustomerDetailsDAO {

    List<CustomerDetails> getAllCustomers();

    void saveCustomer(CustomerDetails customer);

    void removeCustomer(String gstNo);

    Optional<CustomerDetails> fetchCustomerByGSTNo(String gstNo);

}
