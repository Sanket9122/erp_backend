package com.Sanket.ERP.repositories;


import com.Sanket.ERP.entity.Project;
import com.Sanket.ERP.entity.enums.Projectstatus;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findByStatus(Projectstatus status);
}
