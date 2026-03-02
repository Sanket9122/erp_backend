package com.Sanket.ERP.Dto;
import lombok.Data;
import java.util.List;

@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private String status;
    private List<String> teamMemberNames;
    private int teamsize;
    private List<String> accessorySerialNumbers;
}