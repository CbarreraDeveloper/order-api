package com.orderapi.order_api.controllers;

import com.orderapi.order_api.converter.UserConverter;
import com.orderapi.order_api.dtos.SignupRequestDTO;
import com.orderapi.order_api.dtos.UserDTO;
import com.orderapi.order_api.entity.User;
import com.orderapi.order_api.services.UserService;
import com.orderapi.order_api.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @PostMapping(value="/signup")
    public ResponseEntity<WrapperResponse<UserDTO>> signup(@RequestBody SignupRequestDTO request){
        User user = userService.createUser(userConverter.signup(request));
        return new WrapperResponse<>(true, "success", userConverter.fromEntity(user))
                .createResponse();
    }
}
