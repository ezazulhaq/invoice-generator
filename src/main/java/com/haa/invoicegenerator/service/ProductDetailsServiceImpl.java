package com.haa.invoicegenerator.service;

import java.util.List;
import java.util.Optional;

import com.haa.invoicegenerator.dao.ProductDetailsDAO;
import com.haa.invoicegenerator.entity.ProductDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

    @Autowired
    private ProductDetailsDAO productDao;

    @Override
    public List<ProductDetails> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public void saveProduct(ProductDetails product) {
        productDao.saveProduct(product);
    }

    @Override
    public void removeProduct(Integer id) {
        productDao.removeProduct(id);
    }

    @Override
    public Optional<ProductDetails> fetchProductById(Integer id) {
        return productDao.fetchProductById(id);
    }

}
