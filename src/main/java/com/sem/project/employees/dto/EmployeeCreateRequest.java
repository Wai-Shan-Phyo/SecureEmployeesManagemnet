package com.sem.project.employees.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeCreateRequest {
    private UUID keycloakUserId;

    private String employeeCode;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Long departmentId;

    private Long positionId;

    private Long managerId;

    private LocalDate hireDate;

}
