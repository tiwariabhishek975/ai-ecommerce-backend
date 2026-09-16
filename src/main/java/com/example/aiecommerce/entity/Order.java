package com.example.aiecommerce.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="user_id",nullable=false) private User user;
    @Column(name="total_amount",nullable=false,precision=19,scale=2) private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private OrderStatus status=OrderStatus.CREATED;
    @Column(name="created_at",nullable=false) private LocalDateTime createdAt;
    @OneToMany(mappedBy="order",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<OrderItem> items=new ArrayList<>();
    @PrePersist void prePersist(){if(createdAt==null)createdAt=LocalDateTime.now();}
    public Order(){}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public User getUser(){return user;} public void setUser(User v){user=v;}
    public BigDecimal getTotalAmount(){return totalAmount;} public void setTotalAmount(BigDecimal v){totalAmount=v;}
    public OrderStatus getStatus(){return status;} public void setStatus(OrderStatus v){status=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public List<OrderItem> getItems(){return items;} public void setItems(List<OrderItem> v){items=v;}
}
