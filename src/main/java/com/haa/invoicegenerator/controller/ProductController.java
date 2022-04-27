package com.haa.invoicegenerator.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.haa.invoicegenerator.entity.ProductDetails;
import com.haa.invoicegenerator.service.ProductDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductDetailsService productService;

    @GetMapping(value = "/dashboard")
    public String getAllProductDetails(Model model) {
        List<ProductDetails> productList = productService.getAllProducts();

        model.addAttribute("productList", productList);

        return "product/product-details";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        ProductDetails product = new ProductDetails();

        theModel.addAttribute("product", product);

        List<String> hList = new ArrayList<>();
        theModel.addAttribute("hsnCodeList", hsnCodeList(hList));

        return "product/product-form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") @Valid ProductDetails product, Errors errors,
            Model theModel) {

        if (null != errors && errors.getErrorCount() > 0) {

            theModel.addAttribute("product", product);

            List<String> hList = new ArrayList<>();
            theModel.addAttribute("hsnCodeList", hsnCodeList(hList));

            return "product/product-form";
        }

        productService.saveProduct(product);
        return "redirect:/product/dashboard";
    }

    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable("id") String id, Model theModel) {

        Optional<ProductDetails> product = productService.fetchProductById(Integer.parseInt(id));

        if (product.isPresent())
            theModel.addAttribute("product", product.get());

        List<String> hList = new ArrayList<>();
        theModel.addAttribute("hsnCodeList", hsnCodeList(hList));

        return "product/product-form";
    }

    @GetMapping("/remove/{id}")
    public String deleteProduct(@PathVariable("id") String id, Model theModel) {
        productService.removeProduct(Integer.parseInt(id));
        return "redirect:/product/dashboard";
    }

    private List<String> hsnCodeList(List<String> hList) {

        hList.add("4008");

        return hList;
    }

}
