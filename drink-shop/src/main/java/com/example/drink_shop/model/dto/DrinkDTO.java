package com.example.drink_shop.model.dto;

import com.example.drink_shop.model.enumeration.Country;
import com.example.drink_shop.model.enumeration.DrinkType;
import com.example.drink_shop.model.enumeration.Manufacturer;
import com.example.drink_shop.model.enumeration.Pack;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.math.BigDecimal;

public class DrinkDTO {
    @Null(message = "Id should be empty. It is filled in automatically")
    private Long id;


    @NotNull
    private DrinkType drinkType;
    @NotNull
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Double weight;
    @NotNull
    private Manufacturer manufacturer;
    @NotNull
    private Country country;
    @NotNull
    private Pack pack;
    @NotNull
    private Long reserve;

    public DrinkDTO() {
    }

    public DrinkDTO(DrinkType drinkType, String name, BigDecimal price, Double weight, Manufacturer manufacturer, Country country, Pack pack, Long reserve) {
        this.drinkType = drinkType;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.manufacturer = manufacturer;
        this.country = country;
        this.pack = pack;
        this.reserve = reserve;
    }

    public DrinkDTO(Long id, DrinkType drinkType, String name, BigDecimal price, Double weight, Manufacturer manufacturer, Country country, Pack pack, Long reserve) {
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

    public void setId(Long id) {
        this.id = id;
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
