package com.example.drink_shop.service;

import com.example.drink_shop.mapper.Mapper;
import com.example.drink_shop.model.dto.DeletedDrinkDTO;
import com.example.drink_shop.model.dto.DrinkDTO;
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
    private final Mapper mapper;

    public DrinkService(DrinkRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<DrinkDTO> getAllDrinks() {
        List<DrinkEntity> drinkEntityList = repository.findAll();
        List<DrinkDTO> drinkList = new ArrayList<>();
        for (DrinkEntity drinkEntity: drinkEntityList) {
            DrinkDTO drinkDTO = mapper.mapEntityToDrinkDTO(drinkEntity);
            drinkList.add(drinkDTO);
        }
        return drinkList;
    }

    public DrinkDTO getDrinkById(Long id) {
        DrinkEntity drinkEntity = repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found drink with id = %s".formatted(id)));
        return mapper.mapEntityToDrinkDTO(drinkEntity);
    }

    public DrinkDTO createDrink(DrinkDTO drinkToCreate) {
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

        DrinkEntity drinkEntity = mapper.mapDTOtoDrinkEntity(null, drinkToCreate);
        DrinkEntity savedEntity = repository.save(drinkEntity);
        return mapper.mapEntityToDrinkDTO(savedEntity);

    }

    public DeletedDrinkDTO deleteDrink(Long id) {
        DrinkEntity drinkEntity = repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found drink with id = %s".formatted(id)));
        if (repository.isDrinkNotInOrder(id)) {
            repository.delete(drinkEntity);
            DeletedDrinkDTO deletedDrinkDTO = mapper.mapEntityToDeletedDrinkDTO(drinkEntity);
            deletedDrinkDTO.setMessage("Drink %s with id = %s was successfully deleted.".formatted(drinkEntity.getName(), drinkEntity.getId()));
            return deletedDrinkDTO;
        } else {
            throw new IllegalArgumentException("Drink %s with id = %s cannot be deleted because it was ordered."
                    .formatted(drinkEntity.getName(), drinkEntity.getId()));
        }
    }

    public DrinkDTO updateDrink(Long id, DrinkDTO drinkToUpdate) {
        DrinkEntity oldDrinkEntity = repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found drink with id = %s".formatted(id)));

        if (drinkToUpdate.getId() != null) {
            throw new IllegalArgumentException("Drink id must be empty.");
        }
        if (!(drinkToUpdate.getDrinkType() instanceof DrinkType)) {
            throw new IllegalArgumentException(drinkToUpdate.getDrinkType() + " wrong DrinkType.");
        }
        if (drinkToUpdate.getPrice().compareTo(BigDecimal.ZERO) != 1) {
            throw new IllegalArgumentException("Price must be more than 0.");
        }
        if (drinkToUpdate.getWeight() <= 0) {
            throw new IllegalArgumentException("Weight must be more than 0.");
        }
        if (drinkToUpdate.getReserve() <= 0) {
            throw new IllegalArgumentException("Reserve must be more than 0.");
        }
        if (!(drinkToUpdate.getManufacturer() instanceof Manufacturer)) {
            throw new IllegalArgumentException(drinkToUpdate.getManufacturer() + " wrong Manufacturer.");
        }
        if (!(drinkToUpdate.getCountry() instanceof Country)) {
            throw new IllegalArgumentException(drinkToUpdate.getCountry() + " wrong Country.");
        }
        if (!(drinkToUpdate.getPack() instanceof Pack)) {
            throw new IllegalArgumentException(drinkToUpdate.getPack() + " wrong Pack.");
        }

        DrinkEntity newDrinkEntity = mapper.mapDTOtoDrinkEntity(oldDrinkEntity.getId(), drinkToUpdate);

        DrinkEntity updatedEntity = repository.save(newDrinkEntity);
        return mapper.mapEntityToDrinkDTO(updatedEntity);
    }

}
