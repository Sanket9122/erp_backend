package com.Sanket.ERP.repositories;


import com.Sanket.ERP.entity.Accesories;
import com.Sanket.ERP.entity.Project;
import com.Sanket.ERP.entity.enums.Projectstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

//this repository are performing the queries of finding out the details like availability of the accesory
//and finding it by using the serialnumber
@Repository
public interface AccessoryRepository extends JpaRepository<Accesories, Long> {
    List<Accesories> findByIsAvailableTrue();
    List<Accesories> findByAssignedProject(Project project);
    Optional<Accesories> findBySerialNumber(String serialNumber);
    long countByIsAvailableTrue();
    long countByAssignedProject(Project project);
}
