package com.sem.project.leave.service;

import com.sem.project.leave.dto.LeaveCreateRequest;
import com.sem.project.leave.dto.LeaveResponse;

import java.util.List;

public interface LeaveService {
    LeaveResponse requestLeave(LeaveCreateRequest leaveCreateRequest);
    List<LeaveResponse> myLeaves();
    List<LeaveResponse> pendingLeaves();
    LeaveResponse rejectLeaves(Long leaveId);
    LeaveResponse approvedLeave(Long leaveId);
}
