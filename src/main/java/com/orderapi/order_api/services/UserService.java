package com.orderapi.order_api.services;

import com.orderapi.order_api.entity.User;
import com.orderapi.order_api.exceptions.GeneralServiceException;
import com.orderapi.order_api.exceptions.NoDataFoundException;
import com.orderapi.order_api.exceptions.ValidateServiceException;
import com.orderapi.order_api.repository.UserRepository;
import com.orderapi.order_api.validators.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User createUser(User user) {
        try {

            UserValidator.signup(user);

            User existUser = userRepo.findByUsername(user.getUsername())
                    .orElse(null);

            if(existUser != null) throw new ValidateServiceException("El nombre de usuario ya existe");

            return userRepo.save(user);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

}