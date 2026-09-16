package com.example.aiecommerce.repository;
import com.example.aiecommerce.entity.Order;
import com.example.aiecommerce.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUserOrderByCreatedAtDesc(User user);
}
