package com.haa.invoicegenerator.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.haa.invoicegenerator.entity.CustomerDetails;
import com.haa.invoicegenerator.service.CustomerDetailsService;

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
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerDetailsService customerService;

    @GetMapping(value = "/dashboard")
    public String getAllCustomerDetails(Model model) {
        List<CustomerDetails> customerList = customerService.getAllCustomers();

        model.addAttribute("customerList", customerList);

        return "customer/customer-details";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        CustomerDetails customer = new CustomerDetails();

        theModel.addAttribute("customer", customer);

        List<String> sList = new ArrayList<>();
        theModel.addAttribute("stateList", stateList(sList));

        return "customer/customer-form";
    }

    @PostMapping("/save")
    public String saveDefect(@ModelAttribute("customer") @Valid CustomerDetails customer, Errors errors,
            Model theModel) {

        if (null != errors && errors.getErrorCount() > 0) {

            theModel.addAttribute("customer", customer);

            List<String> sList = new ArrayList<>();
            theModel.addAttribute("stateList", stateList(sList));

            return "customer/customer-form";
        }

        customerService.saveCustomer(customer);
        return "redirect:/customer/dashboard";
    }

    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable("id") String gstNo, Model theModel) {

        Optional<CustomerDetails> customer = customerService.fetchCustomerByGSTNo(gstNo);

        if (customer.isPresent())
            theModel.addAttribute("customer", customer.get());

        List<String> sList = new ArrayList<>();
        theModel.addAttribute("stateList", stateList(sList));

        return "customer/customer-form";
    }

    @GetMapping("/remove/{id}")
    public String deleteCustomer(@PathVariable("id") String gstNo, Model theModel) {
        customerService.removeCustomer(gstNo);
        return "redirect:/customer/dashboard";
    }

    private List<String> stateList(List<String> sList) {
        sList.add("Andhra Pradesh");
        sList.add("Telangana");

        return sList;
    }

}
