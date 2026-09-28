package com.humblefool.springboot.homeworks.module02.dto;

import com.humblefool.springboot.homeworks.module02.annotations.PrimeNumberValidation;
import jakarta.validation.constraints.*;

public record DepartmentPatchDto(

        @Size(min=2,max=20,message = "Department code must be between 2 and 20 characters")
        String departmentCode,

        @Email(message = "Contact email must be a valid email address")
        String contactEmail,

        @Size(min=3, max=20)
        @Pattern(regexp = ".*\\S.*")
        String departmentName,

        @Pattern(regexp="^\\+?[0-9]{10,15}$", message = "Phone number must be between 10 and 15 digits and can start with +")
        String phoneNumber,

        @PositiveOrZero(message = "Budget cannot be negative")
        Double budget,

        @Size(max=500,message="Description cannot exceed 500 characters")
        String description,

        Boolean active,

        @PrimeNumberValidation
        Integer primeNumber
){}
