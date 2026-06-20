package com.sem.project.employees.mapper;
import com.sem.project.employees.dto.EmployeeResponse;
import com.sem.project.employees.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")//componentModel = "spring":  MapStruct ကို ဒီ Mapper class အတွက် Spring Bean တစ်ခု အလိုအလျောက် ဆောက်ခိုင်း
public interface EmployeeMapper {
   @Mapping(
           target = "departmentName", //Employee object ထဲမှာရှိတဲ့ department ရဲ့ deptName ကို ယူပြီး၊ EmployeeResponse ရဲ့ departmentName field ထဲကို ထည့်ပ
           source = "department.deptName"
   )

   @Mapping(
           target = "positionName",
           source = "position.positionName"
   )
   @Mapping(
           target = "managerName",
           expression =
                   "java(employee.getManager() != null ? " +
                           "employee.getManager().getFirstName() + \" \" +" +
                           "employee.getManager().getLastName() : null)"
   ) //expression (Custom Java Code)
   EmployeeResponse toResponse(Employee employee);

}
