package com.sam.crickstatloginservice.controller;

import com.sam.crickstatloginservice.dto.UserDTO;
import com.sam.crickstatloginservice.entity.UserEntity;
import com.sam.crickstatloginservice.repository.UserRepository;
import com.sam.crickstatloginservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private UserEntity userEntity;
//    @PostMapping("/register")
//    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO ) {
//        try {
//            UserDTO registeredUser = userService.signup(userDTO);
//            return new ResponseEntity<>(registeredUser, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//    }


    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> registerUser(@RequestBody UserDTO userDTO) {
        try {
            if (userRepository.existsByEmail(userDTO.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseMessage("Email already in use"));
            }
            if (userRepository.existsByUsername(userDTO.getUsername())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseMessage("Username already in use"));
            }

            UserEntity userEntity = userService.convertToUserEntity(userDTO);
            userEntity = userRepository.save(userEntity);

            return ResponseEntity.ok(new ResponseMessage("User registered successfully"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage("An unexpected error occurred"));
        }
    }

    // Define a simple class to encapsulate the response message
    static class ResponseMessage {
        private String message;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }







    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody Map<String, String> loginRequest) {
        try {
            String username = loginRequest.get("username");
            String password = loginRequest.get("password");

            UserDTO loggedInUser = userService.login(username, password);
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
