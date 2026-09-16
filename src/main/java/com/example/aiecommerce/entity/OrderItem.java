package com.example.aiecommerce.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;

@Entity
@Table(name="order_items")
public class OrderItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="order_id",nullable=false) private Order order;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="product_id",nullable=false) private Product product;
    @Column(nullable=false) private Integer quantity;
    @Column(nullable=false,precision=19,scale=2) private BigDecimal price;
    public OrderItem(){}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Order getOrder(){return order;} public void setOrder(Order v){order=v;}
    public Product getProduct(){return product;} public void setProduct(Product v){product=v;}
    public Integer getQuantity(){return quantity;} public void setQuantity(Integer v){quantity=v;}
    public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal v){price=v;}
}
