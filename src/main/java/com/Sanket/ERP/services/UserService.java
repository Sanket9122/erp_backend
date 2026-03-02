package com.Sanket.ERP.services;

import com.Sanket.ERP.entity.User;
import java.util.List;

public interface UserService {
    User saveUser(User user);

    User createUser(User user);

    List<User> getAllUsers();
    List<User> getAvailableUsers();
}