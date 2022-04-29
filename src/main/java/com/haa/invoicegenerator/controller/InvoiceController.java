package com.haa.invoicegenerator.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.haa.invoicegenerator.entity.CustomerDetails;
import com.haa.invoicegenerator.entity.GoodDetails;
import com.haa.invoicegenerator.entity.InvoiceDetails;
import com.haa.invoicegenerator.service.CustomerDetailsService;
import com.haa.invoicegenerator.service.InvoiceDetailsService;

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

    @GetMapping("/generate")
    public String showFormForAdd(Model theModel) {
        InvoiceDetails invoice = new InvoiceDetails();
        invoiceService.saveInvoice(invoice);
        return "redirect:/";
    }

    @PostMapping("/save")
    public String saveInvoice(@ModelAttribute("product") @Valid InvoiceDetails invoice, Errors errors,
            Model theModel) {

        if (null != errors && errors.getErrorCount() > 0) {

            theModel.addAttribute("invoice", invoice);

            return "invoice/invoice-form";
        }

        invoiceService.saveInvoice(invoice);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable("id") String invoiceId, Model theModel) {

        Optional<InvoiceDetails> invoice = invoiceService.fetchInvoiceById(Integer.parseInt(invoiceId));

        if (invoice.isPresent()) {
            theModel.addAttribute("invoice", invoice.get());

            List<GoodDetails> goodsList = invoiceService.getGoodsListByInvoice(invoice.get().getInvoiceId());
            theModel.addAttribute("goodsList", goodsList);
        }

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
    public String postMethodName(@PathVariable("id") String gstNo, @PathVariable("invoiceId") String invoiceId,
            Model theModel) {

        Optional<InvoiceDetails> invoice = invoiceService.fetchInvoiceById(Integer.parseInt(invoiceId));
        Optional<CustomerDetails> customer = customerService.fetchCustomerByGSTNo(gstNo);

        if (invoice.isPresent() && customer.isPresent()) {
            invoice.get().setCustomer(customer.get());
            invoiceService.saveInvoice(invoice.get());

            theModel.addAttribute("invoice", invoice.get());

            List<GoodDetails> goodList = invoiceService.getGoodsListByInvoice(invoice.get().getInvoiceId());
            theModel.addAttribute("goodList", goodList);
        }

        return "redirect:/invoice/update/" + invoiceId;
    }

}
