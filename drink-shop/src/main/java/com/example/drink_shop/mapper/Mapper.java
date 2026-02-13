package com.example.drink_shop.mapper;

import com.example.drink_shop.model.dto.DeletedDrinkDTO;
import com.example.drink_shop.model.dto.DrinkDTO;
import com.example.drink_shop.model.dto.OrderResponseDTO;
import com.example.drink_shop.model.dto.UserDTO;
import com.example.drink_shop.model.entity.DrinkEntity;
import com.example.drink_shop.model.entity.OrderEntity;
import com.example.drink_shop.model.entity.OrderPositionEntity;
import com.example.drink_shop.model.entity.UserEntity;
import com.example.drink_shop.repository.DrinkRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {
    private DrinkRepository drinkRepository;

    public DrinkDTO mapEntityToDrinkDTO(DrinkEntity drinkEntity) {
        return new DrinkDTO(drinkEntity.getId(), drinkEntity.getDrinkType(), drinkEntity.getName(),
                drinkEntity.getPrice(), drinkEntity.getWeight(), drinkEntity.getManufacturer(), drinkEntity.getCountry(),
                drinkEntity.getPack(), drinkEntity.getReserve());
    }

    public DeletedDrinkDTO mapEntityToDeletedDrinkDTO(DrinkEntity drinkEntity) {
        return new DeletedDrinkDTO(drinkEntity.getId(), drinkEntity.getDrinkType(), drinkEntity.getName(),
                drinkEntity.getPrice(), drinkEntity.getWeight(), drinkEntity.getManufacturer(), drinkEntity.getCountry(),
                drinkEntity.getPack(), drinkEntity.getReserve());
    }

    public DrinkEntity mapDTOtoDrinkEntity(Object id, DrinkDTO drinkDTO) {
        return new DrinkEntity((Long) id, drinkDTO.getDrinkType(), drinkDTO.getName(), drinkDTO.getPrice(), drinkDTO.getWeight(),
                drinkDTO.getManufacturer(), drinkDTO.getCountry(), drinkDTO.getPack(), drinkDTO.getReserve());

    }

    public OrderResponseDTO mapEntityToOrderResponse(OrderEntity orderEntity) {
        return new OrderResponseDTO(orderEntity.getId(), convertOrderPositionEntityToList(orderEntity.getCartDrinks()),
                orderEntity.getUserId().getId(), orderEntity.getOrderDateTime(), orderEntity.getAddress(), orderEntity.getStatus(), orderEntity.getTotalCost());
    }

    public void convertListToOrderPositionEntity(OrderEntity orderEntity, OrderResponseDTO order) {
        List<OrderResponseDTO.cartDrinkItem> cartDrinks = order.getCartDrink();
        for (OrderResponseDTO.cartDrinkItem position: cartDrinks) {
            orderEntity.createPosition(drinkRepository.findById(position.getDrinkId()).orElseThrow(), position.getQuantity());
        }
    }

    public List<OrderResponseDTO.cartDrinkItem> convertOrderPositionEntityToList(List<OrderPositionEntity> positionEntities) {
        List<OrderResponseDTO.cartDrinkItem> cartDrinks = new ArrayList<>();
        for (OrderPositionEntity positionEntity: positionEntities) {
            OrderResponseDTO.cartDrinkItem position = new OrderResponseDTO.cartDrinkItem(positionEntity.getDrink().getId(), positionEntity.getQuantity());
            cartDrinks.add(position);
        }
        return cartDrinks;
    }

    public UserDTO mapUserEntityToUserDTO(UserEntity userEntity) {
        return new UserDTO(userEntity.getId(), userEntity.getName(), userEntity.getPhoneNumber(), userEntity.getPassword(), userEntity.getRole(), userEntity.getAddress());
    }

    public UserEntity mapUserDTOtoEntity(UserDTO userDTO) {
        return new UserEntity(null, userDTO.getName(), userDTO.getPhoneNumber(), userDTO.getPassword(), userDTO.getRole(), userDTO.getAddress());
    }

}
