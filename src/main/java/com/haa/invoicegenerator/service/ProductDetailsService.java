package com.haa.invoicegenerator.service;

import java.util.List;
import java.util.Optional;

import com.haa.invoicegenerator.entity.ProductDetails;

public interface ProductDetailsService {

    List<ProductDetails> getAllProducts();

    void saveProduct(ProductDetails customer);

    void removeProduct(Integer id);

    Optional<ProductDetails> fetchProductById(Integer id);

}
