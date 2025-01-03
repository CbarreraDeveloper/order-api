package com.orderapi.order_api.converter;

import com.orderapi.order_api.dtos.SignupRequestDTO;
import com.orderapi.order_api.dtos.UserDTO;
import com.orderapi.order_api.entity.User;

public class UserConverter extends AbstractConverter<User, UserDTO>{

    @Override
    public UserDTO fromEntity(User entity) {
        if(entity == null) return null;
        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .build();
    }

    @Override
    public User fromDTO(UserDTO dto) {
        if(dto == null) return null;
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .build();
    }

    public User signup(SignupRequestDTO dto) {
        if(dto == null) return null;
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

}
