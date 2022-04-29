package com.haa.invoicegenerator.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.haa.invoicegenerator.entity.CustomerDetails;
import com.haa.invoicegenerator.repo.CustomerDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDetailsDAOImpl implements CustomerDetailsDAO {

    @Autowired
    private CustomerDetailsRepository customerRepo;

    @Override
    public List<CustomerDetails> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    @Transactional
    public void saveCustomer(CustomerDetails customer) {
        customerRepo.save(customer);
    }

    @Override
    @Transactional
    public void removeCustomer(String gstNo) {
        customerRepo.deleteById(gstNo);
    }

    @Override
    public Optional<CustomerDetails> fetchCustomerByGSTNo(String gstNo) {
        return customerRepo.findById(gstNo);
    }

}
