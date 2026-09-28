package com.humblefool.springboot.homeworks.module02.advices;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

// basePackages restricts wrapping to our own controllers, so Swagger's /v3/api-docs
// and Actuator endpoints are returned untouched.
@RestControllerAdvice(basePackages = "com.humblefool.springboot.homeworks.module02.controller")
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NonNull MethodParameter returnType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public @Nullable Object beforeBodyWrite(@Nullable Object body,
                                            @NonNull MethodParameter returnType,
                                            @NonNull MediaType selectedContentType,
                                            @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                            @NonNull ServerHttpRequest request,
                                            @NonNull ServerHttpResponse response) {

        if (body instanceof ApiResponse) {
            return body;
        }
        return new ApiResponse<>(body);
    }
}