package com.sem.project.leave.dto;

import com.sem.project.leave.enums.LeaveStatus;
import com.sem.project.leave.enums.LeaveType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class LeaveResponse {
    private Long id;

    private String employeeName;

    private LeaveType leaveType;

    private LocalDate startDate;

    private LocalDate endDate;

    private String reason;

    private LeaveStatus status;

    private String approvedBy;

    private Instant approvedAt;
}
