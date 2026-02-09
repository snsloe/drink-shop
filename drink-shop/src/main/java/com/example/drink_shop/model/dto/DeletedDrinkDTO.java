package com.example.drink_shop.model.dto;

import com.example.drink_shop.model.enumeration.Country;
import com.example.drink_shop.model.enumeration.DrinkType;
import com.example.drink_shop.model.enumeration.Manufacturer;
import com.example.drink_shop.model.enumeration.Pack;

import java.math.BigDecimal;

public class DeletedDrinkDTO {
    private String message;
    private Long id;
    private DrinkType drinkType;
    private String name;
    private BigDecimal price;
    private Double weight;
    private Manufacturer manufacturer;
    private Country country;
    private Pack pack;
    private Long reserve;

    public DeletedDrinkDTO() {
    }

    public DeletedDrinkDTO(Long id, DrinkType drinkType, String name, BigDecimal price, Double weight, Manufacturer manufacturer, Country country, Pack pack, Long reserve) {
        this.id = id;
        this.drinkType = drinkType;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.manufacturer = manufacturer;
        this.country = country;
        this.pack = pack;
        this.reserve = reserve;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
