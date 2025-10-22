package org.project.vibgyor.repository;

import org.project.vibgyor.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findByPaymentStatus(String paymentStatus);

}
