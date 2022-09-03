package design_patterns.structural.facade;

import design_patterns.structural.facade.services.InvoicingService;
import design_patterns.structural.facade.services.PaymentsService;
import design_patterns.structural.facade.services.UserRepository;

class PdfReportGenerator {

    private InvoicingService invoicingService;
    private PaymentsService paymentsService;
    private UserRepository userRepository;

    PdfReport generate() {
        //fancy logic to generate csv report

        //combine payments with invoices and also users
        return new PdfReport();
    }

    static class PdfReport {
        //report
    }
}
