package design_patterns.structural.facade.fixed;

import design_patterns.structural.facade.services.InvoicingService;
import design_patterns.structural.facade.services.PaymentsService;
import design_patterns.structural.facade.services.UserRepository;

import java.util.List;

class ReportDataAggregator {

    private InvoicingService invoicingService;
    private PaymentsService paymentsService;
    private UserRepository userRepository;

    List<AggregatedData> getAggregatedData() {
        //combine all data related to
        return List.of();
    }
}
