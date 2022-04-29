package com.haa.invoicegenerator.repo;

import java.util.List;

import com.haa.invoicegenerator.entity.GoodDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodDetailsRepository extends JpaRepository<GoodDetails, Integer> {

    @Query(value = "SELECT * FROM good_details g WHERE g.invoice_invoice_id=?1", nativeQuery = true)
    List<GoodDetails> findByGoodDetailsInvoiceId(Integer invoiceId);

}
