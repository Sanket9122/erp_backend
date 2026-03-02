package com.Sanket.ERP.services;
import com.Sanket.ERP.Dto.ProjectDTO;

import java.util.List;


//this interface is performing the queries of finding out the details like availability of the accesory
//and finding it by using the serialnumber
//and also assigning the user to the project and assigning the accessory to the project

public interface ProjectService {
    ProjectDTO getProjectById(Long id);

    List<ProjectDTO> getAllProjects();

    void assignUserToProject(Long projectId, Long userId);
    void assignAccessoryToProject(Long projectId, String serialNumber);
}