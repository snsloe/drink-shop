package com.example.drink_shop.service;

import com.example.drink_shop.mapper.Mapper;
import com.example.drink_shop.model.dto.OrderResponseDTO;
import com.example.drink_shop.model.dto.OrderRequestDTO;
import com.example.drink_shop.model.entity.DrinkEntity;
import com.example.drink_shop.model.entity.OrderEntity;
import com.example.drink_shop.model.enumeration.Status;
import com.example.drink_shop.repository.DrinkRepository;
import com.example.drink_shop.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DrinkRepository drinkRepository;
    private final Mapper mapper;

    public OrderService(OrderRepository repository, DrinkRepository drinkRepository, Mapper mapper) {
        this.orderRepository = repository;
        this.drinkRepository = drinkRepository;
        this.mapper = mapper;
    }

    public List<OrderResponseDTO> getAllOrders() {
        List<OrderEntity> allOrderEntity = orderRepository.findAll();
        List<OrderResponseDTO> allOrders = new ArrayList<>();
        for (OrderEntity orderEntity: allOrderEntity) {
            OrderResponseDTO order = mapper.mapEntityToOrderResponse(orderEntity);
            allOrders.add(order);
        }
        return allOrders;
    }

    public OrderResponseDTO getOrderBuId(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found order with id = %s".formatted(id)));
        return mapper.mapEntityToOrderResponse(orderEntity);
    }

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderToCreate) {
        if (orderToCreate.getId() != null) {
            throw new IllegalArgumentException("Id should be empty. It is filled in automatically.");
        }
        if (orderToCreate.getOrderDateTime() != null) {
            throw new IllegalArgumentException("Date and Time should be empty. It is filled in automatically.");
        }
        if (orderToCreate.getStatus() != null) {
            throw new IllegalArgumentException("Status should be empty. It is filled in automatically.");
        }

        OrderEntity orderEntityToCreate = new OrderEntity(null, orderToCreate.getUserId(), orderToCreate.getAddress());
        for (Map.Entry<Long, Integer> positionDTO : orderToCreate.getCartDrinks().entrySet()) {
            DrinkEntity drinkEntity = drinkRepository.findById(positionDTO.getKey()).orElseThrow(() ->
                    new EntityNotFoundException("Not found order with id = %s".formatted(positionDTO.getKey())));
            Integer quantity = positionDTO.getValue();
            if (quantity > drinkEntity.getReserve()) {
                throw new IllegalArgumentException("Drink %s with id = %s cannot be added to order quantity of %s. Reserve is %s."
                        .formatted(drinkEntity.getName(), drinkEntity.getId(), quantity, drinkEntity.getReserve()));
            }
            drinkEntity.setReserve(drinkEntity.getReserve() - quantity);
            drinkRepository.save(drinkEntity);
            orderEntityToCreate.createPosition(drinkEntity, quantity);
        }
        OrderEntity savedOrderEntity = orderRepository.save(orderEntityToCreate);
        return mapper.mapEntityToOrderResponse(savedOrderEntity);
    }

    @Transactional
    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO orderToUpdate) {
        OrderEntity oldOrderEntity = orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found order with id = %s".formatted(id)));

        if (oldOrderEntity.getStatus() != Status.CREATED) {
            throw new IllegalStateException("Order with status: %s must not be updated."
                    .formatted(oldOrderEntity.getStatus()));
        }
        OrderEntity orderEntityToUpdate = new OrderEntity(orderToUpdate.getUserId(), orderToUpdate.getAddress());
        for (Map.Entry<Long, Integer> positionDTO : orderToUpdate.getCartDrinks().entrySet()) {
            DrinkEntity drinkEntity = drinkRepository.findById(positionDTO.getKey()).orElseThrow(() ->
                    new EntityNotFoundException("Not found order with id = %s".formatted(positionDTO.getKey())));

            Integer quantity = positionDTO.getValue();
            if (quantity > drinkEntity.getReserve()) {
                throw new IllegalArgumentException("Drink %s with id = %s cannot be added to order quantity of %s. Reserve is %s."
                        .formatted(drinkEntity.getName(), drinkEntity.getId(), quantity, drinkEntity.getReserve()));
            }
            drinkEntity.setReserve(drinkEntity.getReserve() - quantity);
            orderEntityToUpdate.createPosition(drinkEntity, quantity);
        }
        OrderEntity updatedOrderEntity = orderRepository.save(orderEntityToUpdate);
        return mapper.mapEntityToOrderResponse(updatedOrderEntity);
    }

    @Transactional
    public OrderResponseDTO cancelOrder(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found order with id = %s".formatted(id)));

        if (orderEntity.getStatus() == Status.CREATED) {
            orderEntity.setStatus(Status.CANCELED);
            //orderRepository.setStatus(id, Status.CANCELED);
            OrderResponseDTO canceledOrderDTO = mapper.mapEntityToOrderResponse(orderEntity);
            canceledOrderDTO.setMessage("Order with id = %s was successfully canceled.".formatted(orderEntity.getId()));
            return canceledOrderDTO;
        } else if (orderEntity.getStatus() == Status.CANCELED) {
            throw new IllegalStateException("Order with id = %s was already canceled.".formatted(orderEntity.getId()));
        } else {
            throw new IllegalStateException("Order with id = %s cannot be canceled because its status: %s."
                    .formatted(orderEntity.getId(), orderEntity.getStatus()));
        }
    }

    @Transactional
    public OrderResponseDTO changeStatusToPaid(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found drink with id = %s".formatted(id)));

        if (orderEntity.getStatus() == Status.PAID || orderEntity.getStatus() == Status.COMPLETED) {
            throw new IllegalStateException("Order with id = %s is already paid.".formatted(id));
        } else if (orderEntity.getStatus() == Status.CANCELED) {
            throw new IllegalStateException("Order with id = %s is already canceled.".formatted(id));
        } else if (orderEntity.getStatus() == Status.DONE) {
            throw new IllegalStateException("Order with id = %s is already done.".formatted(id));
        }
        orderEntity.setStatus(Status.PAID);
        OrderResponseDTO paidOrderDTO = mapper.mapEntityToOrderResponse(orderEntity);
        paidOrderDTO.setMessage("Order with id = %s was successfully paid.".formatted(id));
        //orderRepository.setStatus(id, Status.CANCELED);
        return paidOrderDTO;
    }

    @Transactional
    public OrderResponseDTO changeStatusToCompleted(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found drink with id = %s".formatted(id)));

        if (orderEntity.getStatus() == Status.COMPLETED || orderEntity.getStatus() == Status.DONE) {
            throw new IllegalStateException("Order with id = %s is already completed.".formatted(id));
        } else if (orderEntity.getStatus() == Status.CANCELED) {
            throw new IllegalStateException("Order with id = %s is already canceled.".formatted(id));
        } else if (orderEntity.getStatus() == Status.CREATED) {
            throw new IllegalStateException("Order with id = %s has not been paid yet.".formatted(id));
        }
        orderEntity.setStatus(Status.COMPLETED);
        OrderResponseDTO completedOrderDTO = mapper.mapEntityToOrderResponse(orderEntity);
        completedOrderDTO.setMessage("Order with id = %s was successfully completed.".formatted(id));
        //orderRepository.setStatus(id, Status.CANCELED);
        return completedOrderDTO;
    }

    @Transactional
    public OrderResponseDTO changeStatusToDone(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found drink with id = %s".formatted(id)));

        if (orderEntity.getStatus() == Status.DONE) {
            throw new IllegalStateException("Order with id = %s is already done.".formatted(id));
        } else if (orderEntity.getStatus() == Status.CANCELED) {
            throw new IllegalStateException("Order with id = %s is already canceled.".formatted(id));
        } else if (orderEntity.getStatus() == Status.CREATED || orderEntity.getStatus() == Status.PAID) {
            throw new IllegalStateException("Order with id = %s has not been completed yet.".formatted(id));
        }
        orderEntity.setStatus(Status.DONE);
        OrderResponseDTO doneOrderDTO = mapper.mapEntityToOrderResponse(orderEntity);
        doneOrderDTO.setMessage("Order with id = %s was successfully done.".formatted(id));
        //orderRepository.setStatus(id, Status.CANCELED);
        return doneOrderDTO;
    }
}
