package com.example.orderapp.repository;

import com.example.orderapp.entity.OrderEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    Optional<OrderEntity> findByProductIDAndEmail(final Integer productID, final String email);
}