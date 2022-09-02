package design_patterns.structural.facade;

import lombok.extern.java.Log;

import java.util.List;

@Log
public class PaymentsService {

    public List<Payment> getPayments() {
        return List.of();
    }

    static class Payment {
        //payment related data
    }
}
