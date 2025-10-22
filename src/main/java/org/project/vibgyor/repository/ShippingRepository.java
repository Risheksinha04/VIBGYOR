package org.project.vibgyor.repository;

import org.project.vibgyor.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingRepository extends JpaRepository<Shipping, Integer> {
    List<Shipping> findByShippingStatus(String shippingStatus);
    List<Shipping> findByOrder_OrderId(Integer orderId);

}
