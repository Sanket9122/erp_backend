package com.Sanket.ERP.repositories;

import com.Sanket.ERP.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository

//inerfaces are basically a definition of what a object defined in the code can do and traditonally the logics are not allowed in the interface whether it is concrete methods or abstract methods

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findBycurrentProjectIsNull();
    Optional<User> findByEmail(String email);

}
