package com.orderapi.order_api.converter;

import com.orderapi.order_api.dtos.SignupRequestDTO;
import com.orderapi.order_api.dtos.UserDTO;
import com.orderapi.order_api.entity.User;

public class UserConverter extends AbstractConverter<User, UserDTO>{

    @Override
    public UserDTO fromEntity(User entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .build();
    }

    @Override
    public User fromDTO(UserDTO dto) {
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .build();
    }

    public User signup(SignupRequestDTO dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

}
