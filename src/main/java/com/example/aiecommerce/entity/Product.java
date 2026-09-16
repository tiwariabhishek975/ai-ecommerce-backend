package com.example.aiecommerce.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="products")
public class Product {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,length=200) private String name;
    @Column(columnDefinition="TEXT") private String description;
    @Column(nullable=false,precision=19,scale=2) private BigDecimal price;
    @Column(nullable=false,length=100) private String category;
    @Column(nullable=false) private Integer stock;
    @Column(length=500) private String image;
    @Column(name="created_at",nullable=false) private LocalDateTime createdAt;
    @PrePersist void prePersist(){if(createdAt==null)createdAt=LocalDateTime.now();}
    public Product(){}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal v){price=v;}
    public String getCategory(){return category;} public void setCategory(String v){category=v;}
    public Integer getStock(){return stock;} public void setStock(Integer v){stock=v;}
    public String getImage(){return image;} public void setImage(String v){image=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
