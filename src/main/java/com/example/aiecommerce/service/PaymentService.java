package com.example.aiecommerce.service;

import com.example.aiecommerce.entity.*;
import com.example.aiecommerce.exception.ResourceNotFoundException;
import com.example.aiecommerce.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository payments; private final OrderRepository orders;
    public PaymentService(PaymentRepository payments,OrderRepository orders){this.payments=payments;this.orders=orders;}
    @Transactional
    public Payment create(Long orderId,BigDecimal amount){
        Order o=orders.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order not found"));
        Payment p=new Payment();p.setOrder(o);p.setAmount(amount);p.setStatus(PaymentStatus.SUCCESS);p.setTransactionId("TXN-"+UUID.randomUUID());
        return payments.save(p);
    }
    public Payment findByOrder(Long orderId){return payments.findByOrderId(orderId).orElseThrow(()->new ResourceNotFoundException("Payment not found"));}
}
