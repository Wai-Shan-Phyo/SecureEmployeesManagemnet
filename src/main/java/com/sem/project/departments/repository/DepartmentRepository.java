package com.sem.project.departments.repository;

import com.sem.project.departments.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  DepartmentRepository extends JpaRepository<Department, Long>
{

    Optional<Department> findByDeptName(String deptName);
    boolean existsByDeptName(String deptName);
}
