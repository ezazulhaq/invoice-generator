package com.haa.invoicegenerator.service;

import java.util.List;
import java.util.Optional;

import com.haa.invoicegenerator.entity.CustomerDetails;

public interface CustomerDetailsService {

    List<CustomerDetails> getAllCustomers();

    void saveCustomer(CustomerDetails customer);

    void removeCustomer(String gstNo);

    Optional<CustomerDetails> fetchCustomerByGSTNo(String gstNo);

}
