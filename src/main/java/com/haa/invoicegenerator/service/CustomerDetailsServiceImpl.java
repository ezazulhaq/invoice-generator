package com.haa.invoicegenerator.service;

import java.util.List;
import java.util.Optional;

import com.haa.invoicegenerator.dao.CustomerDetailsDAO;
import com.haa.invoicegenerator.entity.CustomerDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

    @Autowired
    private CustomerDetailsDAO customerDao;

    @Override
    public List<CustomerDetails> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public void saveCustomer(CustomerDetails customer) {
        customerDao.saveCustomer(customer);
    }

    @Override
    public void removeCustomer(String gstNo) {
        customerDao.removeCustomer(gstNo);
    }

    @Override
    public Optional<CustomerDetails> fetchCustomerByGSTNo(String gstNo) {
        return customerDao.fetchCustomerByGSTNo(gstNo);
    }

}
