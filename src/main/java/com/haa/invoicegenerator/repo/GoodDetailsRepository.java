package com.haa.invoicegenerator.repo;

import java.util.List;
import java.util.Optional;

import com.haa.invoicegenerator.entity.GoodDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodDetailsRepository extends JpaRepository<GoodDetails, Integer> {

    @Query(value = "SELECT p.hsn_code,p.particulars,p.rate,g.kgs,g.amount,g.id FROM product_details p, good_details g WHERE p.id=g.product_id AND g.invoice_invoice_id=?1", nativeQuery = true)
    List<String> findByGoodDetailsInvoiceId(Integer invoiceId);

    @Query(value = "SELECT * FROM good_details g WHERE g.id=?1 AND g.invoice_invoice_id=?2", nativeQuery = true)
    Optional<GoodDetails> findByGoodDetailsByIdAndInvoiceId(Integer id, Integer invoiceId);

}
