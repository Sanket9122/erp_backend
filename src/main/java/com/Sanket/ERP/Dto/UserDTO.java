package com.Sanket.ERP.Dto;
import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String name;
    private String email;
    private String role;
    private String currentProjectName;
    private Long currentProjectId;
}