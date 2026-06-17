package com.sem.project.employees.service.impl;

import com.sem.project.common.exception.ResourceNotFoundException;
import com.sem.project.departments.entity.Department;
import com.sem.project.departments.repository.DepartmentRepository;
import com.sem.project.employees.dto.EmployeeCreateRequest;
import com.sem.project.employees.dto.EmployeeResponse;
import com.sem.project.employees.entity.Employee;
import com.sem.project.employees.mapper.EmployeeMapper;
import com.sem.project.employees.repository.EmployeeRepository;
import com.sem.project.employees.service.EmployeeService;
import com.sem.project.position.entity.Position;
import com.sem.project.position.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

   @Override
   public EmployeeResponse createEmployee(EmployeeCreateRequest request){
         Department department = departmentRepository.findById(request.getDepartmentId())
                 .orElseThrow(()->new ResourceNotFoundException("Department Not found"));

         Position position = positionRepository.findById(request.getPositionId())
               .orElseThrow(()->new ResourceNotFoundException("Position not found"));

         Employee manager = null;
         if(request.getManagerId()!=null){
             manager = employeeRepository.findById(request.getManagerId())
                     .orElseThrow(()-> new ResourceNotFoundException("Manager not found"));
         }

         Employee employee = Employee.builder()
                 .keycloakUserId(request.getKeycloakUserId())
                 .employeeCode(request.getEmployeeCode())
                 .firstName(request.getFirstName())
                 .lastName(request.getLastName())
                 .email(request.getEmail())
                 .phone(request.getPhone())
                 .department(department)
                 .position(position)
                 .manager(manager)
                 .hireDate(request.getHireDate())
                 .active(true)
                 .build();
       Employee saved =
               employeeRepository.save(employee);
       return employeeMapper
               .toResponse(saved);
   }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return employeeMapper.toResponse(employee);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(Long id) {
     if(!employeeRepository.existsById(id)){
         throw  new ResourceNotFoundException("employee not found");
     }
     employeeRepository.deleteById(id);
    }
}
