package com.sem.project.leave.mapper;

import com.sem.project.leave.dto.LeaveResponse;
import com.sem.project.leave.entity.Leave;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeaveMapper {
   @Mapping(
           target="employeeName",
           expression = "java(leave.getEmployee().getFirstName() + \\\" \\\" + leave.getEmployee().getLastName())"
   )
   @Mapping(
           target = "approvedBy",
           expression =
                   "java(leave.getApprovedBy() != null ? leave.getApprovedBy().getFirstName() + \" \" + leave.getApprovedBy().getLastName() : null)"
   )
  // MapStruct က အလိုအလျောက် ထုတ်ပေးမယ့် code ပုံစံအကြမ်းဖျင်း
//@Component
//public class LeaveMapperImpl implements LeaveMapper {
//    @Override
//    public LeaveResponse toResponse(Leave leave) {
//        if ( leave == null ) {
//            return null;
//        }
//
//        LeaveResponse leaveResponse = new LeaveResponse();
//
//        //  expressions တွေက ဒီလို code တွေအဖြစ် ပြောင်းသွားမယ်
//        leaveResponse.setEmployeeName( leave.getEmployee().getFirstName() + " " + leave.getEmployee().getLastName() );
//        leaveResponse.setApprovedBy( leave.getApprovedBy() != null ? leave.getApprovedBy().getFirstName() + " " + leave.getApprovedBy().getLastName() : null );
//
//        // ကျန်တဲ့ field နာမည်တူတာတွေကိုလည်း အောက်မှာ auto set လုပ်ပေးသွားမယ်...x
//        leaveResponse.setId( leave.getId() );
//        leaveResponse.setStartDate( leave.getStartDate() );
//
//        return leaveResponse;
//    }
  LeaveResponse toResponse(
          Leave leave
  );
}
