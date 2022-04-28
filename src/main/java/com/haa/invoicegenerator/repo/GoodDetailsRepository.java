package com.haa.invoicegenerator.repo;

import com.haa.invoicegenerator.entity.GoodDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodDetailsRepository extends JpaRepository<GoodDetails, Integer> {

}
