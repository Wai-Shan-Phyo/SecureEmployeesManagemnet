package com.sem.project.audit.service.impl;

import com.sem.project.audit.entity.AuditLog;
import com.sem.project.audit.repository.AuditLogRepository;
import com.sem.project.audit.service.AuditService;
import com.sem.project.common.security.CurrentUserService;
import com.sem.project.employees.entity.Employee;
import com.sem.project.audit.enums.AuditAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {
     private final AuditLogRepository auditLogRepository;
     private final CurrentUserService currentUserService;

    @Override
    public void log(AuditAction action, String entityName, Long entityId, String details) {
        Employee currentUser = currentUserService.getCurrentEmployee();
        AuditLog auditLog = AuditLog.builder()
                .employee(currentUser)
                .action(String.valueOf(AuditAction.VISIT_EMPLOYEE))
                .entityName(entityName)
                .entityId(entityId)
                .details(details)
                .build();
        auditLogRepository.save(auditLog);
    }
}
