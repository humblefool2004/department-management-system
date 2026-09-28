package com.humblefool.springboot.homeworks.module02.controller;

import com.humblefool.springboot.homeworks.module02.dto.DepartmentGetDto;
import com.humblefool.springboot.homeworks.module02.dto.DepartmentPatchDto;
import com.humblefool.springboot.homeworks.module02.dto.DepartmentSaveDto;
import com.humblefool.springboot.homeworks.module02.services.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentGetDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentGetDto> getDepartmentById(@PathVariable Long departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));
    }

    // Full replace: 200 OK (nothing new is created)
    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentGetDto> updateDepartment(@PathVariable Long departmentId,
                                                             @RequestBody @Valid DepartmentSaveDto departmentSaveDto) {
        return ResponseEntity.ok(departmentService.updateDepartment(departmentId, departmentSaveDto));
    }

    // Partial update: only the fields sent are changed
    @PatchMapping("/{departmentId}")
    public ResponseEntity<DepartmentGetDto> patchDepartment(@PathVariable Long departmentId,
                                                            @RequestBody @Valid DepartmentPatchDto departmentPatchDto) {
        return ResponseEntity.ok(departmentService.patchDepartmentById(departmentId, departmentPatchDto));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<DepartmentGetDto> createDepartment(@RequestBody @Valid DepartmentSaveDto departmentSaveDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.createDepartment(departmentSaveDto));
    }
}