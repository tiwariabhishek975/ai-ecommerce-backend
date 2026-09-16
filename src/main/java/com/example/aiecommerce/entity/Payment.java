package com.example.aiecommerce.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="payments")
public class Payment {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @JsonIgnore
    @OneToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="order_id",nullable=false,unique=true) private Order order;
    @Column(nullable=false,precision=19,scale=2) private BigDecimal amount;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private PaymentStatus status;
    @Column(name="transaction_id",unique=true,length=100) private String transactionId;
    @Column(name="created_at",nullable=false) private LocalDateTime createdAt;
    @PrePersist void prePersist(){if(createdAt==null)createdAt=LocalDateTime.now();}
    public Payment(){}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Order getOrder(){return order;} public void setOrder(Order v){order=v;}
    public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal v){amount=v;}
    public PaymentStatus getStatus(){return status;} public void setStatus(PaymentStatus v){status=v;}
    public String getTransactionId(){return transactionId;} public void setTransactionId(String v){transactionId=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
