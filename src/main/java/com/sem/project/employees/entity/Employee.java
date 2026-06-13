package com.sem.project.employees.entity;

import com.sem.project.common.entity.BaseEntity;
import com.sem.project.departments.entity.Department;
import com.sem.project.position.entity.Position;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "keycloak_user_id" , nullable = false, unique = true)
    private UUID keycloakUserId;
    @Column(name = "employee_code", nullable = false , unique = true)
    private String employeeCode;
    @Column(name = "first_name" ,nullable = false)
    private String firstName;
    @Column(name = "last_name" , nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id" , nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id" ,nullable = false)
    private Position position;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manager_id" )
    private Employee manager;  // Manager Self reference

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;
    @Column(nullable = false)
    private Boolean active;
}
