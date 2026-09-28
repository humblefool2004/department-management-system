package com.humblefool.springboot.homeworks.module02.mapper;

import com.humblefool.springboot.homeworks.module02.dto.DepartmentGetDto;
import com.humblefool.springboot.homeworks.module02.dto.DepartmentPatchDto;
import com.humblefool.springboot.homeworks.module02.dto.DepartmentSaveDto;
import com.humblefool.springboot.homeworks.module02.entities.DepartmentEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentGetDto toDepartmentGetDto(DepartmentEntity departmentEntity);

    List<DepartmentGetDto> toDepartmentGetDto(List<DepartmentEntity> departmentEntities);

    // PUT: if primeNumber is omitted, fall back to the default (2) instead of writing null
    @Mapping(target = "primeNumber", source = "primeNumber", defaultValue = "2")
    void updateDepartmentFromSaveDto(DepartmentSaveDto departmentSaveDto, @MappingTarget DepartmentEntity departmentEntity);

    // PATCH: null fields in the request are ignored, so existing values are kept
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchDepartmentFromPutDto(DepartmentPatchDto departmentPatchDto, @MappingTarget DepartmentEntity departmentEntity);

    // POST: same default so a missing primeNumber does not overwrite the entity default with null
    @Mapping(target = "primeNumber", source = "primeNumber", defaultValue = "2")
    DepartmentEntity toDepartmentEntity(DepartmentSaveDto departmentSaveDto);
}