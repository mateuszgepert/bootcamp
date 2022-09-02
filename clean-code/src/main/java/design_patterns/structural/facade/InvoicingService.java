package design_patterns.structural.facade;

import lombok.extern.java.Log;

import java.util.List;

@Log
public class InvoicingService {

    public List<Invoice> generateInvoices() {
        log.info("Invoices Generated");
        return List.of();
    }

    static class Invoice {
        //invoices list
    }

}
