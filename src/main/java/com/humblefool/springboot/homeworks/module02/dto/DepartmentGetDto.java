package com.humblefool.springboot.homeworks.module02.dto;

import java.time.LocalDateTime;

public record DepartmentGetDto (
    Long id,
    String departmentCode,
    String contactEmail,
    String phoneNumber,
    Double budget,
    String description,
    String departmentName,
    Boolean active,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Integer primeNumber
){}
