package com.sem.project.common.security;

import com.sem.project.common.exception.ResourceNotFoundException;
import com.sem.project.employees.entity.Employee;
import com.sem.project.employees.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CurrentUserService {
    private final EmployeeRepository employeeRepository;
    public Employee getCurrentEmployee(){
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        Jwt jwt = (Jwt)  authentication.getPrincipal();
        String keycloakId = jwt.getSubject();
        UUID userId =
                UUID.fromString(keycloakId);
        return employeeRepository.findByKeycloakUserId(userId).orElseThrow(()-> new ResourceNotFoundException("ID does not exist"));
     }
}
