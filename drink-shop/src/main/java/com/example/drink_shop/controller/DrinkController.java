package com.example.drink_shop.controller;

import com.example.drink_shop.model.Drink;
import com.example.drink_shop.service.DrinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drink")
public class DrinkController {

    public final DrinkService drinkService;

    private static final Logger log = LoggerFactory.getLogger(DrinkController.class);

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    public ResponseEntity<List<Drink>> getAllDrinks() {
        log.info("Called method getAllDrinks.");
        return ResponseEntity.status(HttpStatus.OK).body(drinkService.getAllDrinks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drink> getDrinkById(@PathVariable("id") Long id) {
        log.info("Called method getDrinkById.");
        return ResponseEntity.status(HttpStatus.OK).body(drinkService.getDrinkById(id));
    }

    @PostMapping
    public ResponseEntity<Drink> createDrink(@RequestBody Drink drinkToCreate) {
        log.info("Called method createDrink.");
        System.out.println(drinkToCreate.getDrinkType());
        return ResponseEntity.status(HttpStatus.OK).body(drinkService.createDrink(drinkToCreate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDrink(@PathVariable Long id) {
        log.info("Called method deleteDrink.");
        return ResponseEntity.status(HttpStatus.OK).body(drinkService.deleteDrink(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Drink>updateDrink(@PathVariable Long id, @RequestBody Drink drinkToUpdate) {
        log.info("Called method updateDrink.");
        return ResponseEntity.status(HttpStatus.OK).body(drinkService.updateDrink(id, drinkToUpdate));
    }

}
