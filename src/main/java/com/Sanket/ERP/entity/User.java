package com.Sanket.ERP.entity;

import com.Sanket.ERP.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User { // Class name updated

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // Field name updated

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project currentProject; // Renamed for clarity: it's an object, not an ID

    @Enumerated(EnumType.STRING)
    private Role role;
}