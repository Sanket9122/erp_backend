package com.Sanket.ERP.entity;

import com.Sanket.ERP.entity.enums.Projectstatus;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Projectstatus status = Projectstatus.Not_Started;

    // List of team members (One Project -> Many Users)
    @OneToMany(mappedBy = "currentProject")
    private List<User> team = new ArrayList<>();

    // List of accessories (One Project -> Many Accessories)
    @OneToMany(mappedBy = "assignedProject")
    private List<Accesories> accessories = new ArrayList<>();
}