package com.example.drink_shop.model.entity;

import com.example.drink_shop.model.enumeration.Status;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderPositionEntity> cartDrinks;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDateTime;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "total_cost", nullable = false)
    private BigDecimal totalCost;

    public OrderEntity() {
    }

    public OrderEntity(Long id, Long userId, String address) {
        this.id = id;
        this.cartDrinks = new ArrayList<>();
        this.userId = userId;
        this.orderDateTime = LocalDateTime.now();
        this.address = address;
        this.status = Status.CREATED;
        this.totalCost = BigDecimal.ZERO;
    }

    public OrderEntity(Long userId, String address) {
        this.cartDrinks = new ArrayList<>();
        this.userId = userId;
        this.orderDateTime = LocalDateTime.now();
        this.address = address;
        this.status = Status.CREATED;
        this.totalCost = BigDecimal.ZERO;
    }

    public void createPosition(DrinkEntity drinkEntity, Integer quantity) {
        OrderPositionEntity positionEntity = new OrderPositionEntity(this, drinkEntity, quantity);
        this.cartDrinks.add(positionEntity);
        this.totalCost = this.totalCost.add(positionEntity.calculateCostPosition());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderPositionEntity> getCartDrinks() {
        return cartDrinks;
    }

    public void setCartDrinks(List<OrderPositionEntity> cartDrinks) {
        this.cartDrinks = cartDrinks;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
