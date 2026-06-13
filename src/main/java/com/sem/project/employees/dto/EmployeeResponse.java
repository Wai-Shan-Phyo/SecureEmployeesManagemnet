package com.sem.project.employees.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {
    private Long id;

    private String employeeCode;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String departmentName;

    private String positionName;

    private String managerName;
}
