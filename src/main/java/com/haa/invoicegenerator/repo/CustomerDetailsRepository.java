package com.haa.invoicegenerator.repo;

import com.haa.invoicegenerator.entity.CustomerDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, String> {

}
