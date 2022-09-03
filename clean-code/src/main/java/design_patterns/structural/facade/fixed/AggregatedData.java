package design_patterns.structural.facade.fixed;

import java.util.List;
import java.util.Map;

public record AggregatedData(String user,
                             Map<String, List<String>> invoicesWithPaymentIds) {
}
