package com.example.aiecommerce.service;

import com.example.aiecommerce.dto.*;
import com.example.aiecommerce.entity.*;
import com.example.aiecommerce.exception.ResourceNotFoundException;
import com.example.aiecommerce.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orders; private final UserRepository users; private final ProductRepository products;
    public OrderService(OrderRepository orders,UserRepository users,ProductRepository products){this.orders=orders;this.users=users;this.products=products;}

    @Transactional
    public Order create(String email,CreateOrderRequest r){
        User user=users.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found"));
        Order order=new Order(); order.setUser(user); order.setStatus(OrderStatus.CREATED);
        BigDecimal total=BigDecimal.ZERO;
        for(OrderItemRequest item:r.items()){
            Product p=products.findById(item.productId()).orElseThrow(()->new ResourceNotFoundException("Product not found: "+item.productId()));
            if(p.getStock()<item.quantity()) throw new IllegalArgumentException("Insufficient stock for product "+p.getId());
            p.setStock(p.getStock()-item.quantity());
            OrderItem oi=new OrderItem(); oi.setOrder(order); oi.setProduct(p); oi.setQuantity(item.quantity()); oi.setPrice(p.getPrice());
            order.getItems().add(oi);
            total=total.add(p.getPrice().multiply(BigDecimal.valueOf(item.quantity())));
        }
        order.setTotalAmount(total);
        return orders.save(order);
    }
    public List<Order> myOrders(String email){User u=users.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found"));return orders.findByUserOrderByCreatedAtDesc(u);}
    public Order find(Long id){return orders.findById(id).orElseThrow(()->new ResourceNotFoundException("Order not found: "+id));}
    @Transactional public Order updateStatus(Long id,String status){
        Order o=find(id); o.setStatus(OrderStatus.valueOf(status.toUpperCase())); return orders.save(o);
    }
}
