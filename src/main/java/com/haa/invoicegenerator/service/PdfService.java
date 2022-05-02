package com.haa.invoicegenerator.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.haa.invoicegenerator.entity.CustomerDetails;
import com.haa.invoicegenerator.entity.InvoiceDetails;
import com.haa.invoicegenerator.pojo.Goods;
import com.lowagie.text.DocumentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Service
public class PdfService {
    private static final String PDF_RESOURCES = "/static/";

    @Autowired
    private InvoiceDetailsService invoiceService;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public File generateInvoicePdf(Integer invoiceId) throws IOException, DocumentException {
        Context context = getInvoiceContext(invoiceId);
        String html = loadAndFillTemplate(context);
        return renderPdf(html);
    }

    private Context getInvoiceContext(Integer invoiceId) {
        Optional<InvoiceDetails> invoiceDetails = invoiceService.fetchInvoiceById(invoiceId);
        InvoiceDetails invoice = new InvoiceDetails();
        List<Goods> goods = new ArrayList<>();
        CustomerDetails customerDetails = new CustomerDetails();

        if (invoiceDetails.isPresent()) {
            invoice = invoiceDetails.get();
            List<String> goodsList = invoiceService.getGoodsListByInvoice(invoice.getInvoiceId());
            goods = goodsList.stream()
                    .map(i -> {
                        String[] data = i.split(",");
                        return new Goods(data[0], data[1], data[2], data[3], data[4], data[5]);
                    })
                    .collect(Collectors.toList());
            customerDetails = invoice.getCustomer();
        }

        Context context = new Context();
        context.setVariable("invoice", invoice);
        context.setVariable("goodsList", goods);
        context.setVariable("customer", customerDetails);

        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return templateEngine.process("invoice/pdf-invoice", context);
    }

    private File renderPdf(String html) throws IOException, DocumentException {
        File file = File.createTempFile("invoice", ".pdf");
        try (OutputStream outputStream = new FileOutputStream(file)) {
            ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
            renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
            renderer.layout();
            renderer.createPDF(outputStream);
        }
        file.deleteOnExit();
        return file;
    }

}
