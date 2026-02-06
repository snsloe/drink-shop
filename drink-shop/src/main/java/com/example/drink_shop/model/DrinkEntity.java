package com.example.drink_shop.model;

import com.example.drink_shop.model.enumeration.Country;
import com.example.drink_shop.model.enumeration.DrinkType;
import com.example.drink_shop.model.enumeration.Manufacturer;
import com.example.drink_shop.model.enumeration.Pack;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "drinks")
public class DrinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "drink_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DrinkType drinkType;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "weight", nullable = false)
    private Double weight;
    @Column(name = "manufacturer", nullable = false)
    @Enumerated(EnumType.STRING)
    private Manufacturer manufacturer;
    @Column(name = "country", nullable = false)
    @Enumerated(EnumType.STRING)
    private Country country;
    @Column(name = "pack", nullable = false)
    @Enumerated(EnumType.STRING)
    private Pack pack;
    @Column(name = "reserve", nullable = false)
    private Long reserve;

    public DrinkEntity() {
    }

    public DrinkEntity(Long id, DrinkType drinkType, String name, BigDecimal price, Double weight, Manufacturer manufacturer, Country country, Pack pack, Long reserve) {
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

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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
