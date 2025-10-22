package org.project.vibgyor.service;

import org.project.vibgyor.entity.Order;
import org.project.vibgyor.entity.Payment;
import org.project.vibgyor.repository.OrderRepository;
import org.project.vibgyor.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment processPayment(Payment payment) {
        payment.setCreatedAt(LocalDateTime.now());
        payment.setOrder(orderRepository.findById(payment.getOrder().getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found")));
        // Set paymentStatus as needed, e.g. "Paid", based on integration with gateway
        return paymentRepository.save(payment);
    }

    public Payment updatePaymentStatus(Integer id, String status) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setPaymentStatus(status);
        payment.setUpdatedAt(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    public void refundPayment(Integer id) {
        paymentRepository.findById(id).ifPresent(payment -> {
            payment.setPaymentStatus("Refunded");
            payment.setUpdatedAt(LocalDateTime.now());
            paymentRepository.save(payment);
        });
    }
}
