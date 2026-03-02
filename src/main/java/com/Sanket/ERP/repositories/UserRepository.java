package com.Sanket.ERP.repositories;

import com.Sanket.ERP.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findBycurrentProjectIsNull();
    Optional<User> findByEmail(String email);

}
