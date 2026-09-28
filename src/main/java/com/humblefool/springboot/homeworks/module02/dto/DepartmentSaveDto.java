package com.humblefool.springboot.homeworks.module02.dto;


import com.humblefool.springboot.homeworks.module02.annotations.PrimeNumberValidation;
import jakarta.validation.constraints.*;

public record DepartmentSaveDto(

        @NotBlank(message = "Department code is required")
        @Size(min=2,max=20,message = "Department code must be between 2 and 20 characters")
        String departmentCode,

        @NotBlank(message = "Department contact email is required")
        @Email(message = "Contact email must be a valid email address")
        String contactEmail,

        @NotBlank(message= "Department name is required")
        String departmentName,

        @Pattern(regexp="^\\+?[0-9]{10,15}$", message = "Phone number must be between 10 and 15 digits and can start with +")
        String phoneNumber,

        @NotNull(message = "Budget is required")
        @PositiveOrZero(message = "Budget cannot be negative")
        Double budget,

        @Size(max=500,message="Description cannot exceed 500 characters")
        String description,

        @NotNull(message = "Active status is required")
        Boolean active,

        @PrimeNumberValidation
        Integer primeNumber
){}
