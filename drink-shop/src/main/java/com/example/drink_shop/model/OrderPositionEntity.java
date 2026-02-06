package com.example.drink_shop.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderPositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    OrderEntity order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drink_id", nullable = false)
    DrinkEntity drink;
    @Column(name = "quantity", nullable = false)
    Integer quantity;

    public OrderPositionEntity() {
    }

    public OrderPositionEntity(Long id, OrderEntity order, DrinkEntity drink, Integer quantity) {
        this.id = id;
        this.order = order;
        this.drink = drink;
        this.quantity = quantity;
    }

    public OrderPositionEntity(OrderEntity order, DrinkEntity drink, Integer quantity) {
        this.order = order;
        this.drink = drink;
        this.quantity = quantity;
    }

    public BigDecimal calculateCostPosition() {
        return this.drink.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DrinkEntity getDrink() {
        return drink;
    }

    public void setDrink(DrinkEntity drink) {
        this.drink = drink;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
