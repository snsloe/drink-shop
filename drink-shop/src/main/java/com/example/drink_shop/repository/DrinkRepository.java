package com.example.drink_shop.repository;

import com.example.drink_shop.model.entity.DrinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DrinkRepository extends JpaRepository<DrinkEntity, Long> {
// select * from drinks
//left join order_items on drinks.id=order_items.drink_id
//where order_items.drink_id is null
    @Query(value = "select count(order_items) = 0 from drinks left join order_items on drinks.id=order_items.drink_id where drinks.id=:id and order_items.drink_id is null", nativeQuery = true)
    public boolean isDrinkNotInOrder(@Param("id") Long id);
}
