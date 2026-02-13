package com.example.drink_shop.service;

import com.example.drink_shop.mapper.Mapper;
import com.example.drink_shop.model.dto.UserDTO;
import com.example.drink_shop.model.entity.UserEntity;
import com.example.drink_shop.model.enumeration.Role;
import com.example.drink_shop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService  {
    private final UserRepository userRepository;
    private final Mapper mapper;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public UserDTO getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found user with id = %s.".formatted(id)));
        return mapper.mapUserEntityToUserDTO(userEntity);

    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserDTO> userList = new ArrayList<>();
        for (UserEntity entity: userEntityList) {
            UserDTO user = mapper.mapUserEntityToUserDTO(entity);
            userList.add(user);
        }
        return userList;
    }

    public UserDTO createUser(UserDTO userToCreate) {
        if (userToCreate.getId() != null) {
            throw new IllegalArgumentException("Id should be empty. It is filled automatically.");
        }
        if (userToCreate.getPassword().length() < 8) {
            throw new IllegalArgumentException("The password must be longer than 8 characters long.");
        }
        if (!(userToCreate.getRole() instanceof Role)) {
            throw new IllegalArgumentException("Wrong role.");
        }
        UserEntity savedEntity = userRepository.save(mapper.mapUserDTOtoEntity(userToCreate));
        return mapper.mapUserEntityToUserDTO(savedEntity);
    }

    public UserDTO updateUser(Long id, UserDTO userToUpdate) {
        UserEntity oldUserEntity = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found user with id = %s.".formatted(id)));
        if (userToUpdate.getId() != null) {
            throw new IllegalArgumentException("Id should be empty. It is filled automatically.");
        }
        if (userToUpdate.getPassword().length() < 8) {
            throw new IllegalArgumentException("The password must be longer than 8 characters long.");
        }
        if (!(userToUpdate.getRole() instanceof Role)) {
            throw new IllegalArgumentException("Wrong role.");
        }
        UserEntity entityToUpdate = new UserEntity(oldUserEntity.getId(), userToUpdate.getName(), userToUpdate.getPhoneNumber(), userToUpdate.getPassword(), userToUpdate.getRole(), userToUpdate.getAddress());
        UserEntity savedEntity = userRepository.save(entityToUpdate);
        return mapper.mapUserEntityToUserDTO(savedEntity);
    }


    public UserDTO deleteUser(Long id) {
        UserEntity entityToDelete = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found user with id = %s.".formatted(id)));
        UserDTO deletedUser = mapper.mapUserEntityToUserDTO(entityToDelete);
        userRepository.delete(entityToDelete);
        log.info("User with id = %s was successfully deleted.".formatted(id));
        return deletedUser;
    }
}
