package com.Sanket.ERP.Controllers;

import com.Sanket.ERP.Dto.ProjectDTO;
import com.Sanket.ERP.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // 1. Get a single project with its team and accessories
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    // 2. Get all projects for the dashboard
    @GetMapping("/available")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    // 3. Assign a User to a Project (Enforces 1 User = 1 Project)
    @PutMapping("/{projectId}/assign-user/{userId}")
    public ResponseEntity<String> assignUser(
            @PathVariable Long projectId,
            @PathVariable Long userId) {
        projectService.assignUserToProject(projectId, userId);
        return ResponseEntity.ok("User assigned successfully to project.");
    }

    // 4. Assign an Accessory by Serial Number
    @PutMapping("/{projectId}/assign-accessory/{serialNumber}")
    public ResponseEntity<String> assignAccessory(
            @PathVariable Long projectId,
            @PathVariable String serialNumber) {
        projectService.assignAccessoryToProject(projectId, serialNumber);
        return ResponseEntity.ok("Accessory assigned successfully to project.");
    }
}