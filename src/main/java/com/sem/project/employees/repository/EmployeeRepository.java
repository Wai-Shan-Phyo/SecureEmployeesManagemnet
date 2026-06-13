package com.sem.project.employees.repository;

import com.sem.project.employees.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee , Long >{
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByEmployeeCode(String employeeCode);
    Optional<Employee>  findByKeycloakUserId(UUID keycloakUserId);
    boolean existsByEmail(String email);
    boolean existsByEmployeeCode(String employeeCode);
}
