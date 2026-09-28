package com.humblefool.springboot.homeworks.module02.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@Builder
public class ApiError {
    private String message;
    private HttpStatus status;
    private Map<String, String> subErrors;
}
