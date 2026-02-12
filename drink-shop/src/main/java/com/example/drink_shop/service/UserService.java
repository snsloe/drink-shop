package com.example.drink_shop.service;

import com.example.drink_shop.model.dto.UserDTO;
import com.example.drink_shop.model.entity.UserEntity;
import com.example.drink_shop.model.enumeration.Role;
import com.example.drink_shop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService  {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found user with id = %s.".formatted(id)));
        return mapUserEntityToUserDTO(userEntity);

    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserDTO> userList = new ArrayList<>();
        for (UserEntity entity: userEntityList) {
            UserDTO user = mapUserEntityToUserDTO(entity);
            userList.add(user);
        }
        return userList;
    }

    private UserDTO mapUserEntityToUserDTO(UserEntity userEntity) {
        return new UserDTO(userEntity.getId(), userEntity.getName(), userEntity.getPhoneNumber(), userEntity.getPassword(), userEntity.getRole(), userEntity.getAddress());
    }

    private UserEntity mapUserDTOtoEntity(UserDTO userDTO) {
        return new UserEntity(null, userDTO.getName(), userDTO.getPhoneNumber(), userDTO.getPassword(), userDTO.getRole(), userDTO.getAddress());
    }

    public UserDTO createUser(UserDTO userDTO) {
        if (userDTO.getId() != null) {
            throw new IllegalArgumentException("Id should be empty. It is filled automatically.");
        }
        if (userDTO.getPassword().length() < 8) {
            throw new IllegalArgumentException("The password must be longer than 8 characters long.");
        }
        if (!(userDTO.getRole() instanceof Role)) {
            throw new IllegalArgumentException("Wrong role.");
        }
        UserEntity savedEntity = userRepository.save(mapUserDTOtoEntity(userDTO));
        return mapUserEntityToUserDTO(savedEntity);
    }
}
