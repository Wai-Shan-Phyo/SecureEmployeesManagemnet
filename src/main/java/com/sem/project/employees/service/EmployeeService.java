package com.sem.project.employees.service;

import com.sem.project.employees.dto.EmployeeCreateRequest;
import com.sem.project.employees.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeCreateRequest request);
    EmployeeResponse getEmployeeById(Long id);
    List<EmployeeResponse> getAllEmployees();
    void deleteEmployee(Long id);
}
