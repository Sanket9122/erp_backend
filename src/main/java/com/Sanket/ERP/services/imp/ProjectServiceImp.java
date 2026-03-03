package com.Sanket.ERP.services.imp;

import com.Sanket.ERP.Dto.ProjectDTO;
import com.Sanket.ERP.entity.Accesories;
import com.Sanket.ERP.entity.Project;
import com.Sanket.ERP.entity.User;
import com.Sanket.ERP.entity.enums.Projectstatus;
import com.Sanket.ERP.repositories.AccessoryRepository;
import com.Sanket.ERP.repositories.ProjectRepository;
import com.Sanket.ERP.repositories.UserRepository;
import com.Sanket.ERP.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final AccessoryRepository accessoryRepository;

    @Override
    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        return mapToDTO(project);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Project> findByStatus(Projectstatus projectsatus){
        return projectRepository.findByStatus(projectsatus);
    }

    @Override
    @Transactional
    public void assignUserToProject(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Note: Your UserRepository uses Integer for ID, but Entity uses Long.
        // I'm using Long to match your User.java file.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // RULE: Check if user is already assigned to a project
        if (user.getCurrentProject() != null) {
            throw new RuntimeException("User '" + user.getName() + "' is already assigned to another project.");
        }

        user.setCurrentProject(project);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void assignAccessoryToProject(Long projectId, String serialNumber) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Accesories accessory = accessoryRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new RuntimeException("Accessory not found with serial: " + serialNumber));

        if (!accessory.isAvailable()) {
            throw new RuntimeException("Accessory is already assigned to a project.");
        }

        accessory.setAssignedProject(project);
        accessory.setAvailable(false);
        accessoryRepository.save(accessory);
    }

    // MANUAL MAPPING: Entity -> DTO
    private ProjectDTO mapToDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getProjectId());
        dto.setName(project.getName());
        dto.setStatus(project.getStatus().name());

        //setting up the team size
        dto.setTeamsize(project.getTeam().size());
        // Extracting names from User list
        dto.setTeamMemberNames(project.getTeam().stream()
                .map(User::getName)
                .collect(Collectors.toList()));

        // Extracting serials from Accessories list
        dto.setAccessorySerialNumbers(project.getAccessories().stream()
                .map(Accesories::getSerialNumber)
                .collect(Collectors.toList()));

        return dto;
    }
}