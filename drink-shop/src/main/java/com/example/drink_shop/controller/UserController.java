package com.example.drink_shop.controller;

import com.example.drink_shop.model.dto.UserDTO;
import com.example.drink_shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        log.info("Called method getUserById.");
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.info("Called method getAllById.");
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userToCreate) {
        log.info("Called method createUser");
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(userToCreate));
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userToUpdate) {
        log.info("Called method updateUser");
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, userToUpdate));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        log.info("Called method deleteUser");
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }
}
