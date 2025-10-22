package org.project.vibgyor.controller;


import org.project.vibgyor.entity.Shipping;
import org.project.vibgyor.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipping")
public class ShippingController {
    @Autowired
    private ShippingService shippingService;

    @GetMapping
    public List<Shipping> getAllShipping() {
        return shippingService.getAllShipping();
    }

    @PostMapping
    public Shipping createShipping(@RequestBody Shipping shipping) {
        return shippingService.createShipping(shipping);
    }

    @PutMapping("/{id}")
    public Shipping updateShipping(@PathVariable Integer id, @RequestBody Shipping shipping) {
        return shippingService.updateShipping(id, shipping);
    }

    @GetMapping("/order/{orderId}")
    public List<Shipping> getShippingByOrder(@PathVariable Integer orderId) {
        return shippingService.getShippingByOrder(orderId);
    }
}
