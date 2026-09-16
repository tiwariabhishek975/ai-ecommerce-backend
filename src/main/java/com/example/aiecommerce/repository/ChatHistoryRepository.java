package com.example.aiecommerce.repository;
import com.example.aiecommerce.entity.ChatHistory;
import com.example.aiecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ChatHistoryRepository extends JpaRepository<ChatHistory,Long> {
    List<ChatHistory> findByUserOrderByCreatedAtDesc(User user);
}
