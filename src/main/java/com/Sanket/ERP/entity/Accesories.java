package com.Sanket.ERP.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accessories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accesories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accesoryId;

    private String name;

    @Column(unique = true, nullable = false)
    private String serialNumber;

    private boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project assignedProject;
}