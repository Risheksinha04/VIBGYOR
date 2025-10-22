package org.project.vibgyor.service;


import org.project.vibgyor.entity.Shipping;
import org.project.vibgyor.repository.OrderRepository;
import org.project.vibgyor.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShippingService {
    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Shipping> getAllShipping() {
        return shippingRepository.findAll();
    }

    public Shipping createShipping(Shipping shipping) {
        shipping.setCreatedAt(LocalDateTime.now());
        shipping.setOrder(orderRepository.findById(shipping.getOrder().getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found")));
        return shippingRepository.save(shipping);
    }

    public Shipping updateShipping(Integer id, Shipping updatedShipping) {
        Optional<Shipping> optionalShipping = shippingRepository.findById(id);
        if (optionalShipping.isPresent()) {
            Shipping shipping = optionalShipping.get();
            shipping.setCourierService(updatedShipping.getCourierService());
            shipping.setTrackingNumber(updatedShipping.getTrackingNumber());
            shipping.setShippingStatus(updatedShipping.getShippingStatus());
            shipping.setShippingCost(updatedShipping.getShippingCost());
            shipping.setUpdatedAt(LocalDateTime.now());
            return shippingRepository.save(shipping);
        }
        throw new RuntimeException("Shipping entry not found");
    }

    public List<Shipping> getShippingByOrder(Integer orderId) {
        return shippingRepository.findByOrder_OrderId(orderId);
    }
}
