package com.example.drink_shop.controller;

import com.example.drink_shop.model.dto.OrderResponseDTO;
import com.example.drink_shop.model.dto.OrderRequestDTO;
import com.example.drink_shop.service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        log.info("Called method getAllOrders.");
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        log.info("Called method getOrderById.");
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(id));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@PathVariable Long id) {
        log.info("Called method cancel order.");
        return ResponseEntity.status(HttpStatus.OK).body(orderService.cancelOrder(id));
    }

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Valid OrderRequestDTO orderToCreate) {
        log.info("Called method createOrder");
        return ResponseEntity.status(HttpStatus.OK).body(orderService.createOrder(orderToCreate));
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Long id, @RequestBody OrderRequestDTO orderToUpdate) {
        log.info("Called method updateOrder.");
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrder(id, orderToUpdate));
    }

    @PatchMapping("/{id}/pay")
    public ResponseEntity<OrderResponseDTO> changeStatusToPaid(@PathVariable Long id) {
        log.info("Called method changeStatusToPaid.");
        return ResponseEntity.status(HttpStatus.OK).body(orderService.changeStatusToPaid(id));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<OrderResponseDTO> changeStatusToCompleted(@PathVariable Long id) {
        log.info("Called method changeStatusToCompleted.");
        return ResponseEntity.status(HttpStatus.OK).body(orderService.changeStatusToCompleted(id));
    }

    @PatchMapping("/{id}/deliver")
    public ResponseEntity<OrderResponseDTO> changeStatusToDone(@PathVariable Long id) {
        log.info("Called method changeStatusToDone.");
        return ResponseEntity.status(HttpStatus.OK).body(orderService.changeStatusToDone(id));
    }
}
