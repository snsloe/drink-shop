package com.example.drink_shop.repository;

import com.example.drink_shop.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

//    @Modifying
//    @Query(value = "update orders set status = :status where id = :id", nativeQuery = true)
//    public void setStatus(@Param("id") Long id, @Param("status") Status status);
}
