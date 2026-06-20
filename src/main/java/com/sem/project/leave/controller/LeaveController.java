package com.sem.project.leave.controller;

import com.sem.project.leave.dto.LeaveCreateRequest;
import com.sem.project.leave.dto.LeaveResponse;
import com.sem.project.leave.service.LeaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/leave")
@RequiredArgsConstructor
public class LeaveController {
   private final LeaveService leaveService;

    @PostMapping
    public LeaveResponse requestLeave(@Valid @RequestBody LeaveCreateRequest request){
      return  leaveService.requestLeave(request);
   }
   @GetMapping("/myLeave")
    public List<LeaveResponse> myLeaves(){
        return leaveService.myLeaves();
   }
    @GetMapping("/pending")
    public List<LeaveResponse> pendingLeaves() {

        return leaveService.pendingLeaves();
    }
   @PostMapping("/{id}/approve")
    public LeaveResponse approveLeave(@PathVariable Long id){
        return leaveService.approvedLeave(id);
   }
    @PostMapping("/{id}/reject")
    public LeaveResponse rejectLeave(
            @PathVariable Long id) {

        return leaveService.rejectLeaves(id);
    }




}
