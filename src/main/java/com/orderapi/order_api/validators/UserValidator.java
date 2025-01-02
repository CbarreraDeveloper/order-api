package com.orderapi.order_api.validators;

import com.orderapi.order_api.entity.User;
import com.orderapi.order_api.exceptions.ValidateServiceException;

public class UserValidator {

    public static void signup(User user) {
        if(user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new ValidateServiceException("El nombre de usuario es requerido");
        }

        if(user.getUsername().length() > 30 ) {
            throw new ValidateServiceException("El nombre de usuario es muy largo (max 30)");
        }

        if(user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new ValidateServiceException("El password de usuario es requerido");
        }

        if(user.getPassword().length() > 30 ) {
            throw new ValidateServiceException("El password de usuario es muy largo (max 30)");
        }
    }
}
