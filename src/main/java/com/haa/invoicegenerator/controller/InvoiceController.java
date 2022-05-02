package com.haa.invoicegenerator.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.haa.invoicegenerator.entity.CustomerDetails;
import com.haa.invoicegenerator.entity.GoodDetails;
import com.haa.invoicegenerator.entity.InvoiceDetails;
import com.haa.invoicegenerator.entity.ProductDetails;
import com.haa.invoicegenerator.pojo.Goods;
import com.haa.invoicegenerator.service.CustomerDetailsService;
import com.haa.invoicegenerator.service.InvoiceDetailsService;
import com.haa.invoicegenerator.service.PdfService;
import com.haa.invoicegenerator.service.ProductDetailsService;
import com.haa.invoicegenerator.util.NumberToWords;
import com.lowagie.text.DocumentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceDetailsService invoiceService;

    @Autowired
    private CustomerDetailsService customerService;

    @Autowired
    private ProductDetailsService productService;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/generate")
    public String showFormForAdd(Model theModel) {
        InvoiceDetails invoice = new InvoiceDetails();
        invoiceService.saveInvoice(invoice);

        theModel.addAttribute("invoice", invoice);

        return "invoice/invoice-form";
    }

    @PostMapping("/save")
    public String saveInvoice(@ModelAttribute("invoice") @Valid InvoiceDetails invoice, Errors errors,
            Model theModel) {

        if (null != errors && errors.getErrorCount() > 0) {

            theModel.addAttribute("invoice", invoice);

            return "invoice/invoice-form";
        }

        String inWords = NumberToWords.numberToWord(String.valueOf(invoice.getTotal()));
        if (inWords.trim().length() == 0) {
            inWords = "Zero";
        }
        invoice.setAmountInWords(inWords);

        invoiceService.saveInvoice(invoice);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable("id") String invoiceId, Model theModel) {

        Optional<InvoiceDetails> invoice = invoiceService.fetchInvoiceById(Integer.parseInt(invoiceId));
        InvoiceDetails invoiceDetails = new InvoiceDetails();
        List<Goods> goods = new ArrayList<>();
        double total;

        if (invoice.isPresent()) {
            invoiceDetails = invoice.get();
            List<String> goodsList = invoiceService.getGoodsListByInvoice(invoiceDetails.getInvoiceId());
            goods = goodsList.stream()
                    .map(i -> {
                        String[] data = i.split(",");
                        return new Goods(data[0], data[1], data[2], data[3], data[4], data[5]);
                    })
                    .collect(Collectors.toList());

            total = goods.stream()
                    .map(good -> Double.parseDouble(good.getAmount()))
                    .reduce(0.0, (Double a, Double b) -> a + b);
            invoiceDetails.setTotal(total);

        }
        theModel.addAttribute("goodsList", goods);
        theModel.addAttribute("invoice", invoiceDetails);

        return "invoice/invoice-form";
    }

    @GetMapping("/remove/{id}")
    public String deleteInvoice(@PathVariable("id") String id, Model theModel) {
        invoiceService.removeInvoice(Integer.parseInt(id));
        return "redirect:/";
    }

    @GetMapping(value = "/gstNo/{id}")
    public String getAllCustomerDetails(@PathVariable("id") String invoiceId, Model theModel) {
        List<CustomerDetails> customerList = customerService.getAllCustomers();

        theModel.addAttribute("customerList", customerList);
        theModel.addAttribute("invoiceId", invoiceId);

        return "invoice/customer-search";
    }

    @PostMapping(value = "/select/{id}/{invoiceId}")
    public String chooseCustomerDetails(@PathVariable("id") String gstNo, @PathVariable("invoiceId") String invoiceId) {

        Optional<InvoiceDetails> invoice = invoiceService.fetchInvoiceById(Integer.parseInt(invoiceId));
        Optional<CustomerDetails> customer = customerService.fetchCustomerByGSTNo(gstNo);

        if (invoice.isPresent() && customer.isPresent()) {
            invoice.get().setCustomer(customer.get());
            invoiceService.saveInvoice(invoice.get());
        }

        return "redirect:/invoice/update/" + invoiceId;
    }

    @GetMapping(value = "/addgood/{id}")
    public String addGoodDetails(@PathVariable("id") String invoiceId, Model theModel) {

        GoodDetails good = invoiceService.addGoodDetails(Integer.parseInt(invoiceId));
        theModel.addAttribute("good", good);
        theModel.addAttribute("invoiceId", invoiceId);

        return "invoice/good-form";
    }

    @GetMapping(value = "/product/{gId}")
    public String getProductDetails(@PathVariable("gId") String goodId,
            Model theModel) {
        List<ProductDetails> productList = productService.getAllProducts();

        theModel.addAttribute("productList", productList);
        theModel.addAttribute("goodId", goodId);

        return "invoice/product-search";
    }

    @PostMapping(value = "/selectProduct/{id}/{goodId}")
    public String chooseProductDetails(@PathVariable("id") String productId,
            @PathVariable("goodId") String goodId,
            Model theModel) {

        Optional<ProductDetails> product = productService.fetchProductById(Integer.parseInt(productId));
        Optional<GoodDetails> good = invoiceService.fetchGoodById(Integer.parseInt(goodId));

        GoodDetails goodDetails = new GoodDetails();

        if (product.isPresent() && good.isPresent()) {
            goodDetails = good.get();
            goodDetails.setProduct(product.get());
            goodDetails = invoiceService.saveGoods(goodDetails);
        }
        theModel.addAttribute("good", goodDetails);
        theModel.addAttribute("invoiceId", goodDetails.getInvoice().getInvoiceId());

        return "invoice/good-form";
    }

    @PostMapping(value = "/save-good/{id}")
    public String goodDetailsAdded(@ModelAttribute("good") @Valid GoodDetails goodDetails,
            @PathVariable("id") String goodId, Model theModel) {

        Optional<GoodDetails> good = invoiceService.fetchGoodById(Integer.parseInt(goodId));
        GoodDetails goodData = new GoodDetails();

        if (good.isPresent()) {
            goodData = good.get();
            goodData.setKgs(goodDetails.getKgs());
            goodData.setAmount(goodData.getKgs() * goodData.getProduct().getRate());
            goodData = invoiceService.saveGoods(goodData);
        }

        return "redirect:/invoice/update/" + goodData.getInvoice().getInvoiceId();
    }

    @GetMapping(value = "/download-pdf/{id}")
    public void downloadPDFResource(@PathVariable("id") String invoiceId, HttpServletResponse response) {
        try {
            Path file = Paths.get(pdfService.generateInvoicePdf(Integer.parseInt(invoiceId)).getAbsolutePath());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
    }

}
