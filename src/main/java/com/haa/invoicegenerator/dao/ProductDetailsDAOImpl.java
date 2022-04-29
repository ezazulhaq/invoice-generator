package com.haa.invoicegenerator.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.haa.invoicegenerator.entity.ProductDetails;
import com.haa.invoicegenerator.repo.ProductDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDetailsDAOImpl implements ProductDetailsDAO {

    @Autowired
    private ProductDetailsRepository productRepo;

    @Override
    public List<ProductDetails> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    @Transactional
    public void saveProduct(ProductDetails product) {
        productRepo.save(product);
    }

    @Override
    @Transactional
    public void removeProduct(Integer id) {
        productRepo.deleteById(id);
    }

    @Override
    public Optional<ProductDetails> fetchProductById(Integer gstNo) {
        return productRepo.findById(gstNo);
    }

}
