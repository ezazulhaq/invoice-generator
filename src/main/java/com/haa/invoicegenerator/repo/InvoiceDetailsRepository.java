package com.haa.invoicegenerator.repo;

import com.haa.invoicegenerator.entity.InvoiceDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails, Integer> {

}
