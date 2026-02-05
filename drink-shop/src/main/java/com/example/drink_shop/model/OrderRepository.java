package com.example.drink_shop.model;

import com.example.drink_shop.model.enumeration.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

//    @Modifying
//    @Query(value = "update orders set status = :status where id = :id", nativeQuery = true)
//    public void setStatus(@Param("id") Long id, @Param("status") Status status);
}
