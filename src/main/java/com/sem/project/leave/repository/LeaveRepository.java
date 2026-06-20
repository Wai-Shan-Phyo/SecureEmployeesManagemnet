package com.sem.project.leave.repository;

import com.sem.project.leave.entity.Leave;
import com.sem.project.leave.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeaveRepository extends JpaRepository<Leave , Long> {
    List<Leave> findByEmployeeId(Long  employeeId);
    List<Leave> findByStatus(LeaveStatus status);
    List<Leave> findByEmployeeManagerId(Long employeeId);

}
