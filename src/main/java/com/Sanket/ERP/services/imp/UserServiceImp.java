package com.Sanket.ERP.services.imp;

import com.Sanket.ERP.Dto.UserDTO;
import com.Sanket.ERP.entity.User;
import com.Sanket.ERP.repositories.UserRepository;
import com.Sanket.ERP.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public UserDTO mapToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());

        // Check if user is assigned to a project
        if (user.getCurrentProject() != null) {
            dto.setCurrentProjectName(user.getCurrentProject().getName());
            dto.setCurrentProjectId(user.getCurrentProject().getProjectId());
        } else {
            dto.setCurrentProjectName("Bench / No Project");
        }
        return dto;
    }

    @Override
    public List<User> getAvailableUsers() {
        // Matches the method name in your UserRepository
        return userRepository.findBycurrentProjectIsNull();
    }
}