package com.orderapi.order_api.services;

import com.orderapi.order_api.converter.UserConverter;
import com.orderapi.order_api.dtos.LoginRequestDTO;
import com.orderapi.order_api.dtos.LoginResponseDTO;
import com.orderapi.order_api.entity.User;
import com.orderapi.order_api.exceptions.GeneralServiceException;
import com.orderapi.order_api.exceptions.NoDataFoundException;
import com.orderapi.order_api.exceptions.ValidateServiceException;
import com.orderapi.order_api.repository.UserRepository;
import com.orderapi.order_api.validators.UserValidator;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class UserService {

    @Value("${jwt.password}")
    private String jwtSecret;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRepository userRepo;

    public User createUser(User user) {
        try {

            UserValidator.signup(user);

            User existUser = userRepo.findByUsername(user.getUsername())
                    .orElse(null);

            if (existUser != null) throw new ValidateServiceException("El nombre de usuario ya existe");

            return userRepo.save(user);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        try {
            User user = userRepo.findByUsername(request.getUsername())
                    .orElseThrow(() -> new ValidateServiceException("Usuario o password incorrectos"));

            if (!user.getPassword().equals(request.getPassword()))
                throw new ValidateServiceException("Usuario o password incorrectos");

            String token = createToken(user);
            return new LoginResponseDTO(userConverter.fromEntity(user), token);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public String createToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + (1000 * 60 * 60));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (UnsupportedJwtException e) {
            log.error("JWT in a particular format/configuration that does not match the format expected");
        } catch (MalformedJwtException e) {
            log.error(" JWT was not correctly constructed and should be rejected");
        } catch (SignatureException e) {
            log.error("Signature or verifying an existing signature of a JWT failed");
        } catch (ExpiredJwtException e) {
            log.error("JWT was accepted after it expired and must be rejected");
        }
        return false;
    }

    public String getUsernameFromToken(String jwt) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ValidateServiceException("Invalid Token");
        }

    }

}
