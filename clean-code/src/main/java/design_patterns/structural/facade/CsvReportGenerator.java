package design_patterns.structural.facade;

import design_patterns.structural.facade.services.InvoicingService;
import design_patterns.structural.facade.services.PaymentsService;
import design_patterns.structural.facade.services.UserRepository;
import lombok.NoArgsConstructor;

@NoArgsConstructor
class CsvReportGenerator {

    private InvoicingService invoicingService;
    private PaymentsService paymentsService;
    private UserRepository userRepository;

    CsvReport generate() {
        //fancy logic to generate csv report

        //combine payments with invoices and also users
        return new CsvReport();
    }

    static class CsvReport {
        //report
    }
}
