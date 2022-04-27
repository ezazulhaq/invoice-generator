package com.haa.invoicegenerator.dao;

import java.util.List;
import java.util.Optional;

import com.haa.invoicegenerator.entity.ProductDetails;

public interface ProductDetailsDAO {

    List<ProductDetails> getAllProducts();

    void saveProduct(ProductDetails product);

    void removeProduct(Integer id);

    Optional<ProductDetails> fetchProductById(Integer id);

}
