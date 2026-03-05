package com.Sanket.ERP.Controllers;

import com.Sanket.ERP.Dto.ProjectDTO;
import com.Sanket.ERP.entity.Project;
import com.Sanket.ERP.entity.User;
import com.Sanket.ERP.repositories.UserRepository;
import com.Sanket.ERP.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Controller for managing user-project related operations
 * 
 * This controller provides endpoints for:
 * - Getting the current user's assigned project
 * - Getting member count for specific projects
 */
@RestController
@RequestMapping("/api/user-projects")
@RequiredArgsConstructor
public class UserProjectController {

    private final ProjectService projectService;
    private final UserRepository userRepository;

    // API 1: Get project of authenticated user
    @GetMapping("/my-project")
    public ResponseEntity<ProjectDTO> getUserProject(Authentication authentication) {
        // Get current authenticated user
        String userEmail = authentication.getName();

        // Fetch user by email
        Optional<User> userOpt = userRepository.findByEmail(userEmail);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();
        if (user.getCurrentProject() == null) {
            return ResponseEntity.notFound().build();
        }

        // Get project details
        ProjectDTO project = projectService.getProjectById(user.getCurrentProject().getProjectId());
        return ResponseEntity.ok(project);
    }
}

//    // API 2: Get number of members in a specific project
//    @GetMapping("/{projectId}/member-count")
//    public ResponseEntity<Map<String, Integer>> getProjectMemberCount(@PathVariable Long projectId) {
//        // Get project details
//        ProjectDTO project = projectService.getProjectById(projectId);
//
//        // Count team members
//        int memberCount = project.getTeamsize();
//
//        Map<String, Integer> response = new HashMap<>();
//        response.put("projectId", projectId.intValue());
//        response.put("memberCount", memberCount);
//
//        return ResponseEntity.ok(response);
//    }
//}
