package com.haa.invoicegenerator.controller;

import java.util.List;

import com.haa.invoicegenerator.entity.InvoiceDetails;
import com.haa.invoicegenerator.service.InvoiceDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @Autowired
    private InvoiceDetailsService invoiceService;

    @GetMapping(value = "/")
    public String getDefectList(Model theModel) {
        List<InvoiceDetails> invoiceList = invoiceService.getAllInvoice();

        theModel.addAttribute("invoiceList", invoiceList);

        return "home";
    }

}
