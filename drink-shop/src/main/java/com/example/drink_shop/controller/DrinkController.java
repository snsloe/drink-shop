package com.example.drink_shop.controller;

//import com.example.drink_shop.model.domain.Drink;
import com.example.drink_shop.model.dto.DeletedDrinkDTO;
import com.example.drink_shop.model.dto.DrinkDTO;
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

    private static final Logger log = LoggerFactory.getLogger(DrinkController.class);
    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    public ResponseEntity<List<DrinkDTO>> getAllDrinks() {
        log.info("Called method getAllDrinks.");
        return ResponseEntity.status(HttpStatus.OK).body(drinkService.getAllDrinks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrinkDTO> getDrinkById(@PathVariable("id") Long id) {
        log.info("Called method getDrinkById.");
        return ResponseEntity.status(HttpStatus.OK).body(drinkService.getDrinkById(id));
    }

    @PostMapping
    public ResponseEntity<DrinkDTO> createDrink(@RequestBody DrinkDTO drinkToCreateDTO) {
        log.info("Called method createDrink.");
        System.out.println(drinkToCreateDTO.getDrinkType());
        return ResponseEntity.status(HttpStatus.OK).body(drinkService.createDrink(drinkToCreateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedDrinkDTO> deleteDrink(@PathVariable Long id) {
        log.info("Called method deleteDrink.");
        return ResponseEntity.status(HttpStatus.OK).body(drinkService.deleteDrink(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DrinkDTO>updateDrink(@PathVariable Long id, @RequestBody DrinkDTO drinkToUpdate) {
        log.info("Called method updateDrink.");
        return ResponseEntity.status(HttpStatus.OK).body(drinkService.updateDrink(id, drinkToUpdate));
    }

}
