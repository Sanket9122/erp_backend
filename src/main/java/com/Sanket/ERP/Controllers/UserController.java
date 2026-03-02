package com.Sanket.ERP.Controllers;

import com.Sanket.ERP.Dto.UserDTO;
import com.Sanket.ERP.entity.User;
import com.Sanket.ERP.repositories.UserRepository;
import com.Sanket.ERP.services.imp.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImp userService;
    private final UserRepository UserRepository;

    //create users
    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    //fetch the available users
    @GetMapping("/available")
    public List<User> getAvailable() {
        return userService.getAvailableUsers();
    }

    //.orElseThrow is a functionality where we can throw the exceptions if it occurs in the execution of the code

    //fetch the users by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable Long userId) {
        User user = UserRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with id"+userId +"not found"));
        return ResponseEntity.ok(userService.mapToUserDTO(user));
    }
}