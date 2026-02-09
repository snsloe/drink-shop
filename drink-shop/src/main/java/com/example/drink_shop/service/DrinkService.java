package com.example.drink_shop.service;

import com.example.drink_shop.model.domain.Drink;
import com.example.drink_shop.model.entity.DrinkEntity;
import com.example.drink_shop.repository.DrinkRepository;
import com.example.drink_shop.model.enumeration.Country;
import com.example.drink_shop.model.enumeration.DrinkType;
import com.example.drink_shop.model.enumeration.Manufacturer;
import com.example.drink_shop.model.enumeration.Pack;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DrinkService {

    private final DrinkRepository repository;

    public DrinkService(DrinkRepository repository) {
        this.repository = repository;
    }

    public List<Drink> getAllDrinks() {
        List<DrinkEntity> drinkEntityList = repository.findAll();
        List<Drink> drinkList = new ArrayList<Drink>();
        for (DrinkEntity drinkEntity: drinkEntityList) {
            Drink drink = mapEntityToDrink(drinkEntity);
            drinkList.add(drink);
        }
        return drinkList;
    }

    public Drink getDrinkById(Long id) {
        DrinkEntity drinkEntity = repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found drink with id = %s".formatted(id)));
        return mapEntityToDrink(drinkEntity);
    }

    public Drink createDrink(Drink drinkToCreate) {
        if (drinkToCreate.getId() != null) {
            throw new IllegalArgumentException("Drink id must be empty.");
        }
        if (!(drinkToCreate.getDrinkType() instanceof DrinkType)) {
            throw new IllegalArgumentException(drinkToCreate.getDrinkType() + " wrong DrinkType.");
        }
        if (drinkToCreate.getPrice().compareTo(BigDecimal.ZERO) != 1) {
            throw new IllegalArgumentException("Price must be more than 0.");
        }
        if (drinkToCreate.getWeight() <= 0) {
            throw new IllegalArgumentException("Weight must be more than 0.");
        }
        if (drinkToCreate.getReserve() <= 0) {
            throw new IllegalArgumentException("Reserve must be more than 0.");
        }
        if (!(drinkToCreate.getManufacturer() instanceof Manufacturer)) {
            throw new IllegalArgumentException(drinkToCreate.getManufacturer() + " wrong Manufacturer.");
        }
        if (!(drinkToCreate.getCountry() instanceof Country)) {
            throw new IllegalArgumentException(drinkToCreate.getCountry() + " wrong Country.");
        }
        if (!(drinkToCreate.getPack() instanceof Pack)) {
            throw new IllegalArgumentException(drinkToCreate.getPack() + " wrong Pack.");
        }

        DrinkEntity drinkEntity = new DrinkEntity(null, drinkToCreate.getDrinkType(), drinkToCreate.getName(), drinkToCreate.getPrice(), drinkToCreate.getWeight(), drinkToCreate.getManufacturer(), drinkToCreate.getCountry(), drinkToCreate.getPack(), drinkToCreate.getReserve());
        DrinkEntity savedEntity = repository.save(drinkEntity);
        return mapEntityToDrink(savedEntity);

    }

    public String deleteDrink(Long id) {
        DrinkEntity drinkEntity = repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found drink with id = %s".formatted(id)));
        if (repository.isDrinkNotInOrder(id)) {
            repository.delete(drinkEntity);
            return "Drink %s with id = %s was successfully deleted.".formatted(drinkEntity.getName(), drinkEntity.getId());
        } else {
            throw new IllegalArgumentException("Drink %s with id = %s cannot be deleted because it was ordered."
                    .formatted(drinkEntity.getName(), drinkEntity.getId()));
        }
    }

    public Drink updateDrink(Long id, Drink drinkToUpdate) {
        DrinkEntity oldDrinkEntity = repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found drink with id = %s".formatted(id)));

        DrinkEntity newDrinkEntity = new DrinkEntity(oldDrinkEntity.getId(), drinkToUpdate.getDrinkType(),
                drinkToUpdate.getName(), drinkToUpdate.getPrice(), drinkToUpdate.getWeight(), drinkToUpdate.getManufacturer(),
                drinkToUpdate.getCountry(), drinkToUpdate.getPack(), drinkToUpdate.getReserve());

        DrinkEntity updatedEntity = repository.save(newDrinkEntity);
        return mapEntityToDrink(updatedEntity);
    }

    private Drink mapEntityToDrink(DrinkEntity drinkEntity) {
        return new Drink(drinkEntity.getId(), drinkEntity.getDrinkType(), drinkEntity.getName(),
                drinkEntity.getPrice(), drinkEntity.getWeight(), drinkEntity.getCountry(),
                drinkEntity.getManufacturer(), drinkEntity.getPack(), drinkEntity.getReserve());
    }
}
