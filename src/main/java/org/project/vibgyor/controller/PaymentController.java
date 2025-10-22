package org.project.vibgyor.controller;

import org.project.vibgyor.entity.Payment;
import org.project.vibgyor.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @PostMapping
    public Payment processPayment(@RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }

    @PutMapping("/{id}/status")
    public Payment updatePaymentStatus(@PathVariable Integer id, @RequestParam String status) {
        return paymentService.updatePaymentStatus(id, status);
    }

    @PostMapping("/{id}/refund")
    public void refundPayment(@PathVariable Integer id) {
        paymentService.refundPayment(id);
    }
}
