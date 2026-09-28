package com.humblefool.springboot.homeworks.module02.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "department_table")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String departmentCode;

    @Column(nullable = false)
    private String departmentName;

    @Column(nullable = false, unique = true, length = 120)
    private String contactEmail;

    @Column(length = 16)
    private String phoneNumber;

    @Column(nullable = false)
    private Double budget;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean active;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "integer default 2")
    private Integer primeNumber = 2;
}