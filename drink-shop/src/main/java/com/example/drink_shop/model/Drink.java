package com.example.drink_shop.model;

import com.example.drink_shop.model.enumeration.Country;
import com.example.drink_shop.model.enumeration.DrinkType;
import com.example.drink_shop.model.enumeration.Manufacturer;
import com.example.drink_shop.model.enumeration.Pack;

import java.math.BigDecimal;

public class Drink {
    private final Long id;
    private DrinkType drinkType;
    private String name;
    private BigDecimal price;
    private Double weight;
    private final Manufacturer manufacturer;
    private final Country country;
    private Pack pack;
    private Long reserve;

    public Drink(Long id, DrinkType drinkType, String name, BigDecimal price, Double weight, Country country, Manufacturer manufacturer, Pack pack, Long reserve) {
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

    public Long getId() {
        return id;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(DrinkType drinkType) {
        this.drinkType = drinkType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Country getCountry() {
        return country;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public Long getReserve() {
        return reserve;
    }

    public void setReserve(Long reserve) {
        this.reserve = reserve;
    }
}
