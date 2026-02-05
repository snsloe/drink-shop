package com.example.drink_shop.model;

import com.example.drink_shop.model.enumeration.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {

    //private DrinkService drinkService;



    private Long id;
    // Map<Long, Byte> cartDrink; // <drinkId, quantity>
    private List<cartDrinkItem> cartDrinks;
    private final Long userId;
    private LocalDateTime orderDateTime;
    private String address;
    private Status status;
    private BigDecimal totalCost;

    public static class cartDrinkItem {
        private Long drinkId;
        private Byte quantity;

        public cartDrinkItem(Long drinkId, Byte quantity) {
            this.drinkId = drinkId;
            this.quantity = quantity;
        }

        public cartDrinkItem() {}

        public Long getDrinkId() {
            return drinkId;
        }

        public void setDrinkId(Long drinkId) {
            this.drinkId = drinkId;
        }

        public Byte getQuantity() {
            return quantity;
        }

        public void setQuantity(Byte quantity) {
            this.quantity = quantity;
        }
    }

//    public Order(Long id, List<cartDrinkItem> cartDrinks, Long userId, LocalDateTime orderDateTime, String address, Status status, BigDecimal totalCost) {
//        this.id = id;
//        this.cartDrinks = cartDrinks;
//        this.userId = userId;
//        this.orderDateTime = orderDateTime;
//        this.address = address;
//        this.status = status;
////        calculateTotalCost();
//    }

    public Order(Long id, List<cartDrinkItem> cartDrinks, Long userId, LocalDateTime orderDateTime, String address, Status status, BigDecimal totalCost) {
        this.id = id;
        this.cartDrinks = cartDrinks;
        this.userId = userId;
        this.orderDateTime = orderDateTime;
        this.address = address;
        this.status = status;
        this.totalCost = totalCost;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalCost() {
//        calculateTotalCost();
        return totalCost;
    }

//    public void setTotalCost(BigDecimal totalCost) {
//        this.totalCost = totalCost;
//    }

    public List<cartDrinkItem> getCartDrink() {
        return cartDrinks;
    }

    public void setCartDrink(List<cartDrinkItem> cartDrink) {
        this.cartDrinks = cartDrink;
    }

    public Long getUserId() {
        return userId;
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

//    private void calculateTotalCost() {
//        BigDecimal totalCost = BigDecimal.valueOf(0);
//        for (cartDrinkItem position: cartDrinks) {
//            Drink drink = drinkService.getDrinkById(position.drinkId);
//            BigDecimal price = drink.getPrice();
//            Byte quantity = position.quantity;
//            totalCost = totalCost.add(price.multiply(BigDecimal.valueOf(quantity)));
//        }
//        this.totalCost = totalCost;
//    }


}
