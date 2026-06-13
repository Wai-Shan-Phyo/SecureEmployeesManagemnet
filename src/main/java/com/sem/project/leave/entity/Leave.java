package com.sem.project.leave.entity;

import com.sem.project.common.entity.BaseEntity;
import com.sem.project.employees.entity.Employee;
import com.sem.project.leave.enums.LeaveStatus;
import com.sem.project.leave.enums.LeaveType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "leaves")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Leave extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id" , nullable = false)
    private Employee employee;  //leave owner

    @Enumerated(EnumType.STRING)
    @Column(name = "leave_type", nullable = false)
    private LeaveType leaveType;


    @Column(name = "start_date" ,nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date",nullable = false)
    private LocalDate endDate;

    @Column(columnDefinition = "TEXT")
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaveStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by")
    private Employee approveBy; //Manager who approve by

    @Column(name = "approved_at")
    private OffsetDateTime approvedAt;
}
