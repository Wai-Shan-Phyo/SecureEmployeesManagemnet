package com.sem.project.audit.service;

import com.sem.project.audit.enums.AuditAction;

public interface AuditService {
  void log(AuditAction action, String entityName, Long entityId, String details);
}
