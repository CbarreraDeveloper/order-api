package com.orderapi.order_api.repository;

import com.orderapi.order_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
