package com.sem.project.employees.controller;

import com.sem.project.common.response.ApiResponse;
import com.sem.project.common.security.CurrentUserService;
import com.sem.project.employees.dto.EmployeeCreateRequest;
import com.sem.project.employees.dto.EmployeeResponse;
import com.sem.project.employees.entity.Employee;
import com.sem.project.employees.mapper.EmployeeMapper;
import com.sem.project.employees.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final CurrentUserService currentUserService;
    private final EmployeeMapper employeeMapper;
    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee( @Valid @RequestBody EmployeeCreateRequest createRequest){
        EmployeeResponse response = employeeService.createEmployee(createRequest);
        return  ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<EmployeeResponse>builder()
                        .success(true)
                        .message("Employee created successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployeeById(@PathVariable Long id){
         EmployeeResponse response = employeeService.getEmployeeById(id);
         return  ResponseEntity.ok(
                 ApiResponse.<EmployeeResponse>builder()
                         .success(true)
                         .message("Employee retrieved successfully")
                         .data(response)
                         .build()
         );
    }

    @GetMapping("/getEmployees")
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getAllEmployees(){
       List<EmployeeResponse> response = employeeService.getAllEmployees();
        return ResponseEntity.ok(
                ApiResponse.<List<EmployeeResponse>>builder()
                        .success(true)
                        .message("Employees retrieved successfully")
                        .data(response)
                        .build()
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>>
    deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Employee deleted successfully")
                        .build()
        );
    }

    @GetMapping("/me")
    public EmployeeResponse me(Authentication authentication){
        Employee employee =  currentUserService.getCurrentEmployee(authentication);
        return employeeMapper.toResponse(employee);
    }


}
