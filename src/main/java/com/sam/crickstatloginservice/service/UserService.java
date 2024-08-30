package com.sam.crickstatloginservice.service;

import com.sam.crickstatloginservice.dto.UserDTO;
import com.sam.crickstatloginservice.entity.UserEntity;
import com.sam.crickstatloginservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserDTO convertToUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setUserid(userEntity.getUserId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setRole(userEntity.getRole());
        return userDTO;
    }

    public UserEntity convertToUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userDTO.getUserid());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setRole(userDTO.getRole());
        return userEntity;
    }

    public UserDTO signup(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username already in use");
        }

        UserEntity userEntity = convertToUserEntity(userDTO);
        userEntity = userRepository.save(userEntity);

        return convertToUserDTO(userEntity);
    }

    public UserDTO login(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null && userEntity.getPassword().equals(password)) {
            return convertToUserDTO(userEntity);
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }
}
