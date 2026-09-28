package com.humblefool.springboot.homeworks.module02.services;

import com.humblefool.springboot.homeworks.module02.dto.DepartmentGetDto;
import com.humblefool.springboot.homeworks.module02.dto.DepartmentPatchDto;
import com.humblefool.springboot.homeworks.module02.dto.DepartmentSaveDto;
import com.humblefool.springboot.homeworks.module02.entities.DepartmentEntity;
import com.humblefool.springboot.homeworks.module02.exceptions.ResourceNotFoundException;
import com.humblefool.springboot.homeworks.module02.mapper.DepartmentMapper;
import com.humblefool.springboot.homeworks.module02.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    private DepartmentEntity findDepartment(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department with id " + departmentId + " not found"));
    }

    @Transactional
    public DepartmentGetDto createDepartment(DepartmentSaveDto departmentSaveDto) {
        DepartmentEntity entity = departmentMapper.toDepartmentEntity(departmentSaveDto);
        DepartmentEntity saved = departmentRepository.save(entity);
        log.info("Created department with id {}", saved.getId());
        return departmentMapper.toDepartmentGetDto(saved);
    }

    @Transactional(readOnly = true)
    public List<DepartmentGetDto> getAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentMapper.toDepartmentGetDto(departmentEntities);
    }

    @Transactional(readOnly = true)
    public DepartmentGetDto getDepartmentById(Long departmentId) {
        return departmentMapper.toDepartmentGetDto(findDepartment(departmentId));
    }

    @Transactional
    public DepartmentGetDto updateDepartment(Long departmentId, DepartmentSaveDto departmentSaveDto) {
        DepartmentEntity departmentEntity = findDepartment(departmentId);
        departmentMapper.updateDepartmentFromSaveDto(departmentSaveDto, departmentEntity);
        DepartmentEntity updated = departmentRepository.save(departmentEntity);
        log.info("Updated (PUT) department {}", departmentId);
        return departmentMapper.toDepartmentGetDto(updated);
    }

    @Transactional
    public DepartmentGetDto patchDepartmentById(Long departmentId, DepartmentPatchDto departmentPatchDto) {
        DepartmentEntity departmentEntity = findDepartment(departmentId);
        departmentMapper.patchDepartmentFromPutDto(departmentPatchDto, departmentEntity);
        DepartmentEntity updated = departmentRepository.save(departmentEntity);
        log.info("Patched department {}", departmentId);
        return departmentMapper.toDepartmentGetDto(updated);
    }

    @Transactional
    public void deleteDepartmentById(Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department with id " + departmentId + " not found");
        }
        departmentRepository.deleteById(departmentId);
        log.info("Deleted department {}", departmentId);
    }
}