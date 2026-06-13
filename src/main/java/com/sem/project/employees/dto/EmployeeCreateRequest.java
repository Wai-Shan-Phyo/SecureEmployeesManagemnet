package com.sem.project.employees.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeCreateRequest {
    @NotNull
    private UUID keycloakUserId;

    @NotBlank
    private String employeeCode;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    @NotBlank
    private String email;


    private String phone;

    @NotNull
    private Long departmentId;

    @NotNull
    private Long positionId;

    private Long managerId;

     @NotNull
    private LocalDate hireDate;

}
