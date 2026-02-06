package com.example.drink_shop.model;

import com.example.drink_shop.model.enumeration.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

// пример:
//{
//  "userId": 102,
//  "address": "ул. Садовая, д. 14, кв. 88",
//  "cartDrinks": {
//    "3": 5,
//    "12": 2,
//    "20": 3
//  }
//}

public class OrderDTO {

    @Null(message = "Id should be empty. It is filled in automatically")
    private Long id;
    @Null(message = "Date and time are filled in automatically.")
    private LocalDateTime orderDateTime;
    @Null(message = "Status should be empty. It is filled in automatically")
    private Status status;
    @Null(message = "Total cost should be empty. It is filled in automatically")
    private BigDecimal totalCost;



    @NotNull
    private Long userId;
    @NotNull
    private String address;
    @NotNull
    private Map<Long, Integer> cartDrinks;

    public OrderDTO(Long userId, String address, Map<Long, Integer> cartDrinks) {
        this.userId = userId;
        this.address = address;
        this.cartDrinks = cartDrinks;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<Long, Integer> getCartDrinks() {
        return cartDrinks;
    }

    public void setCartDrinks(Map<Long, Integer> cartDrinks) {
        this.cartDrinks = cartDrinks;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public Status getStatus() {
        return status;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }
}
