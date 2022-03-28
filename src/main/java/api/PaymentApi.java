package api;

import exceptions.DataNotFoundException;
import model.Payment;
import model.RabbitMessagePayment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import services.PaymentService;

@RequestMapping("/")
public class PaymentApi {

    PaymentService paymentService;

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(path = "/newPayment", consumes = "application/json")
    public ResponseEntity<String> insertNewPayment(@RequestBody Payment payment) {
        try {
            paymentService.createNewPayment(payment);
            return ResponseEntity.ok("Payment done.");
        } catch (DataNotFoundException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

    }
}
