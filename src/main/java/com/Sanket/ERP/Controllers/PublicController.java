package com.Sanket.ERP.Controllers;

import com.Sanket.ERP.Dto.ProjectDTO;
import com.Sanket.ERP.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    private final ProjectService projectService;

    @GetMapping("/projects/{projectId}/member-count")
    public ResponseEntity<Map<String, Integer>> getPublicMemberCount(@PathVariable Long projectId) {
        ProjectDTO project = projectService.getProjectById(projectId);
        int memberCount = project.getTeamsize();
        
        Map<String, Integer> response = new HashMap<>();
        response.put("projectId", projectId.intValue());
        response.put("memberCount", memberCount);
        
        return ResponseEntity.ok(response);
    }
}
