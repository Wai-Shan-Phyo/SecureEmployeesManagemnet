package com.sem.project.leave.service.impl;

import com.sem.project.audit.entity.AuditLog;
import com.sem.project.audit.enums.AuditAction;
import com.sem.project.audit.service.AuditService;
import com.sem.project.common.exception.ResourceNotFoundException;
import com.sem.project.common.security.CurrentUserService;
import com.sem.project.employees.entity.Employee;
import com.sem.project.leave.dto.LeaveCreateRequest;
import com.sem.project.leave.dto.LeaveResponse;
import com.sem.project.leave.entity.Leave;
import com.sem.project.leave.enums.LeaveStatus;
import com.sem.project.leave.mapper.LeaveMapper;
import com.sem.project.leave.repository.LeaveRepository;
import com.sem.project.leave.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {
    private final LeaveRepository leaveRepository;
    private final LeaveMapper leaveMapper;
    private final CurrentUserService currentUserService;
    private final AuditService auditService;


    @Override
    public LeaveResponse requestLeave(LeaveCreateRequest leaveCreateRequest) {
        Employee employee = currentUserService.getCurrentEmployee();
        if (leaveCreateRequest.getStartDate()
                .isAfter(leaveCreateRequest.getEndDate())) {

            throw new IllegalArgumentException(
                    "Start date cannot be after end date");
        }
        long days =
                ChronoUnit.DAYS.between(
                        leaveCreateRequest.getStartDate(),
                        leaveCreateRequest.getEndDate()
                ) + 1;
        Leave leave = Leave.builder()
                .employee(employee)
                .leaveType(leaveCreateRequest.getLeaveType())
                .startDate(leaveCreateRequest.getStartDate())
                .endDate(leaveCreateRequest.getEndDate())
                .reason(leaveCreateRequest.getReason())
                .status(LeaveStatus.PENDING)
                .build();
        Leave saved = leaveRepository.save(leave);
        auditService.log(
                AuditAction.REQUEST_LEAVE,
                "Leave",
                saved.getId(),
                "Leave requested"
        );
        return leaveMapper.toResponse(saved);

    }

    @Override
    public List<LeaveResponse> myLeaves() {
        Employee employee =
                currentUserService
                        .getCurrentEmployee();

        return leaveRepository
                .findByEmployeeId(employee.getId())
                .stream()
                .map(leaveMapper::toResponse)
                .toList();
    }

    @Override
    public List<LeaveResponse> pendingLeaves() {
        Employee manager =
                currentUserService.getCurrentEmployee();

        return leaveRepository
                .findByEmployeeManagerId(
                        manager.getId())
                .stream()
                .filter(leave ->
                        leave.getStatus()
                                == LeaveStatus.PENDING)
                .map(leaveMapper::toResponse)
                .toList();
    }

    @Override
    public LeaveResponse rejectLeaves(Long leaveId) {
        Employee manager = currentUserService.getCurrentEmployee();

        Leave leave = leaveRepository.findById(leaveId).orElseThrow(
                () -> new ResourceNotFoundException("Leave Not Found")
        );
        Employee leaveOwner =
                leave.getEmployee();

        Employee actualManager =
                leaveOwner.getManager();

        if (actualManager == null) {

            throw new IllegalStateException(
                    "Employee has no assigned manager");
        }
        if (!actualManager.getId()
                .equals(manager.getId())) {

            throw new AccessDeniedException(
                    "You cannot approve this leave");
        }

        if (leave.getStatus()
                != LeaveStatus.PENDING) {

            throw new IllegalStateException(
                    "Leave already processed");
        }
        leave.setStatus(LeaveStatus.REJECT);
        leave.setApprovedBy(manager);
        leave.setApprovedAt(OffsetDateTime.now());
        Leave saved = leaveRepository.save(leave);
        auditService.log(
                AuditAction.APPROVE_LEAVE,
                "Leave",
                saved.getId(),
                "Leave Approved By" + manager.getEmployeeCode()
        );
        return leaveMapper.toResponse(saved);
    }

    @Override
    public LeaveResponse approvedLeave(Long leaveId) {
        Employee manager = currentUserService.getCurrentEmployee();

        Leave leave = leaveRepository.findById(leaveId).orElseThrow(
                () -> new ResourceNotFoundException("Leave Not Found")
        );
        Employee leaveOwner =
                leave.getEmployee();

        Employee actualManager =
                leaveOwner.getManager();

        if (actualManager == null) {

            throw new IllegalStateException(
                    "Employee has no assigned manager");
        }
        if (!actualManager.getId()
                .equals(manager.getId())) {

            throw new AccessDeniedException(
                    "You cannot approve this leave");
        }
//        if (!leave.getEmployee()
//                .getManager()
//                .getId()
//                .equals(manager.getId())) {
//
//            throw new AccessDeniedException(
//                    "You cannot approve this leave");
//        }
        if (leave.getStatus()
                != LeaveStatus.PENDING) {

            throw new IllegalStateException(
                    "Leave already processed");
        }
        leave.setStatus(LeaveStatus.APPROVE);
        leave.setApprovedBy(manager);
        leave.setApprovedAt(OffsetDateTime.now());
        Leave saved = leaveRepository.save(leave);
        auditService.log(
                AuditAction.APPROVE_LEAVE,
                "Leave",
                saved.getId(),
                "Leave Approved By" + manager.getEmployeeCode()
        );
        return leaveMapper.toResponse(saved);
    }
}
