package com.sem.project.audit.repository;

import com.sem.project.audit.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog , Long> {
    List<AuditLog>  findByAction(String action);
}
